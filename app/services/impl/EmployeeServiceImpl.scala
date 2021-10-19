package services.impl

import daos.EmployeeDAO
import exceptions.Exception.NotFoundException
import models.Employee
import models.dtos.EmployeeDTO
import monix.eval.Task
import services.EmployeeService

import javax.inject.Inject

class EmployeeServiceImpl @Inject()(employeeDAO: EmployeeDAO) extends EmployeeService {

  override def getAll: Task[Seq[Employee]] = employeeDAO.getAll

  override def getByID(id: Long): Task[EmployeeDTO] = employeeDAO.getById(id).map {
    case Some(employee) => employee.toDTO
    case _ => throw NotFoundException("employee", s"id=$id")
  }
}
