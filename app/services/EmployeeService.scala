package services

import models.Employee
import models.dtos.EmployeeDTO
import monix.eval.Task

trait EmployeeService {

  def getAll: Task[Seq[Employee]]

  def getByID(id: Long):Task[EmployeeDTO]

}
