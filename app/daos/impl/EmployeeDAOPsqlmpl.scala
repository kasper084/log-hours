package daos.impl

import daos.EmployeeDAO
import daos.convectors.EmployeeDAOConvector.EmployeeRowToModel
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

  override def getById(id: Long): Task[Option[Employee]] = ???

  override def create(model: Employee): Task[Employee] = ???

  override def update(model: Employee): Task[Employee] = ???

  override def delete(Id: Long): Task[Unit] = ???
}
