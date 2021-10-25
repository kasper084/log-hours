package daos.impl

import daos.ProjectDAO
import daos.convectors.ProjectDAOConvector.{ProjectModelToRow, ProjectRowToModel}
import demo.Tables._
import models.Project
import monix.eval.Task
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ProjectDAOPsqlmpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                 (implicit ex: ExecutionContext) extends ProjectDAO
  with HasDatabaseConfigProvider[JdbcProfile] {

  import daos.helpers.Helpers._
  import profile.api._

  private val projectQuery: Query[Projects, ProjectsRow, Seq] = TableQuery[Projects]

  protected def queryReturningProjects = projectQuery returning projectQuery

  override def getAll: Task[Seq[Project]] = db
    .run(projectQuery.result)
    .map(_.map(_.toModel))
    .wrapEx

  override def getById(id: Long): Task[Option[Project]] = db
    .run(projectQuery
      .filter(_.id === id)
      .result.headOption)
    .map(_.map(_.toModel))
    .wrapEx

  override def create(project: Project): Task[Project] = db
    .run(queryReturningProjects += project.toRow)
    .wrapEx
    .map(_.toModel)

  override def update(project: Project): Task[Project] = {
    if (project.id == 0)
      throw new RuntimeException()
    val userId = project.id
    val updateAction = projectQuery.filter(_.id === userId)
      .update(project.updateModifiedField().toRow)
      .map { rowsUpdated =>
        project.updateModifiedField()
        if (rowsUpdated == 1)
          project.updateModifiedField()
        else throw new RuntimeException()
      }
    db.run(updateAction).wrapEx

  }

  override def delete(ID: Long): Task[Unit] = db.run(
    projectQuery.filter(_.id === ID).delete
  ).wrapEx.map(_ => ())
}
