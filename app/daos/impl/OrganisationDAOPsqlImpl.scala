package daos.impl

import daos.OrganisationDAO
import daos.convectors.OrganisationDAOConvector.{OrganisationModelToRow, OrganisationRowToModel}
import demo.Tables._
import models.Organisation
import monix.eval.Task
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class OrganisationDAOPsqlImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                       (implicit ex: ExecutionContext) extends OrganisationDAO
  with HasDatabaseConfigProvider[JdbcProfile] {

  import daos.helpers.Helpers._
  import profile.api._

  private val organisationQuery: Query[Organisations, OrganisationsRow, Seq] = TableQuery[Organisations]

  protected def queryReturningOrganisations = organisationQuery returning organisationQuery

  override def getAll: Task[Seq[Organisation]] = db
    .run(organisationQuery.result)
    .map(_.map(_.toModel))
    .wrapEx

  override def getById(id: Long): Task[Option[Organisation]] = db
    .run(organisationQuery
      .filter(_.id === id)
      .result.headOption)
    .map(_.map(_.toModel))
    .wrapEx

  override def create(organisation: Organisation): Task[Organisation] = db
    .run(queryReturningOrganisations += organisation.toRow)
    .wrapEx
    .map(_.toModel)

  override def update(organisation: Organisation): Task[Organisation] = {
    if (organisation.id == 0)
      throw new RuntimeException()
    val userId = organisation.id
    val updateAction = organisationQuery.filter(_.id === userId)
      .update(organisation.updateModifiedField().toRow)
      .map { rowsUpdated =>
        organisation.updateModifiedField()
        if (rowsUpdated == 1)
          organisation.updateModifiedField()
        else throw new RuntimeException()
      }
    db.run(updateAction).wrapEx
  }

  override def delete(ID: Long): Task[Unit] = db.run(
    organisationQuery.filter(_.id === ID).delete
  ).wrapEx.map(_ => ())
}
