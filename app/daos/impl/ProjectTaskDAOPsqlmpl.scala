package daos.impl

import daos.ProjectTaskDAO
import daos.convectors.ProjectTaskDAOConvector.{ProjectTaskModelToRow, ProjectTaskRowToModel}
import demo.Tables._
import models.ProjectTask
import monix.eval.Task
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ProjectTaskDAOPsqlmpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                     (implicit ex: ExecutionContext) extends ProjectTaskDAO
  with HasDatabaseConfigProvider[JdbcProfile] {

  import daos.helpers.Helpers._
  import profile.api._

  private val projectTaskQuery: Query[ProjectTasks, ProjectTasksRow, Seq] = TableQuery[ProjectTasks]

  protected def queryReturningProjectTasks = projectTaskQuery returning projectTaskQuery

  override def getAll: Task[Seq[ProjectTask]] = db
    .run(projectTaskQuery.result)
    .map(_.map(_.toModel))
    .wrapEx

  override def getById(id: Long): Task[Option[ProjectTask]] = db
    .run(projectTaskQuery
      .filter(_.id === id)
      .result.headOption)
    .map(_.map(_.toModel))
    .wrapEx

  override def create(projectTask: ProjectTask): Task[ProjectTask] = db
    .run(queryReturningProjectTasks += projectTask.toRow)
    .wrapEx
    .map(_.toModel)

  override def update(projectTask: ProjectTask): Task[ProjectTask] = {
    if (projectTask.id == 0)
      throw new RuntimeException()
    val userId = projectTask.id
    val updateAction = projectTaskQuery.filter(_.id === userId)
      .update(projectTask.updateModifiedField().toRow)
      .map { rowsUpdated =>
        projectTask.updateModifiedField()
        if (rowsUpdated == 1)
          projectTask.updateModifiedField()
        else throw new RuntimeException()
      }
    db.run(updateAction).wrapEx

  }

  override def delete(ID: Long): Task[Unit] = db.run(
    projectTaskQuery.filter(_.id === ID).delete
  ).wrapEx.map(_ => ())
}
