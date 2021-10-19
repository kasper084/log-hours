package daos.impl

import daos.EmployeeDAO
import daos.convectors.EmployeeDAOConvector.{EmployeeModelToRow, EmployeeRowToModel}
import demo.Tables._
import models.Employee
import monix.eval.Task
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class EmployeeDAOPsqlmpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                  (implicit ex: ExecutionContext) extends EmployeeDAO
  with HasDatabaseConfigProvider[JdbcProfile] {

  import daos.helpers.Helpers._
  import profile.api._

  private val employeeQuery: Query[Employees, EmployeesRow, Seq] = TableQuery[Employees]

  protected def queryReturningEmployees = employeeQuery returning employeeQuery

  override def getAll: Task[Seq[Employee]] = db
    .run(employeeQuery.result)
    .map(_.map(_.toModel))
    .wrapEx

  override def getById(id: Long): Task[Option[Employee]] = db
    .run(employeeQuery
      .filter(_.id === id)
      .result.headOption)
    .map(_.map(_.toModel))
    .wrapEx

  override def create(employee: Employee): Task[Employee] = db
    .run(queryReturningEmployees += employee.toRow)
    .wrapEx
    .map(_.toModel)

  override def update(employee: Employee): Task[Employee] = {
    if (employee.id == 0)
      throw new RuntimeException()
    val userId = employee.id
    val updateAction = employeeQuery.filter(_.id === userId)
      .update(employee.updateModifiedField().toRow)
      .map { rowsUpdated =>
        employee.updateModifiedField()
        if (rowsUpdated == 1)
          employee.updateModifiedField()
        else throw new RuntimeException()
      }
    db.run(updateAction).wrapEx
  }

  override def delete(id: Long): Task[Unit] = db.run(
    employeeQuery.filter(_.id === id).delete
  ).wrapEx.map(_ => ())

}
