package daos.convectors

import demo.Tables.EmployeesRow
import models.Employee

object EmployeeDAOConvector {

  implicit class EmployeeRowToModel(employeesRow: EmployeesRow) {

    def toModel(): Employee = {
      Employee(
        id = employeesRow.id,
        name = employeesRow.name,
        organisation_id = employeesRow.organisationId,
        specialisation = employeesRow.specialisation,
        hour_cost = employeesRow.hourCost,
        createdAt = employeesRow.createdAt,
        updatedAt = employeesRow.updatedAt
      )
    }

  }

  implicit class EmployeeModelToRow(employee: Employee) {

    def toRow:EmployeesRow = {
      EmployeesRow(
        id = employee.id,
        name = employee.name,
        organisationId = employee.organisation_id,
        specialisation = employee.specialisation,
        hourCost = employee.hour_cost,
        createdAt = employee.createdAt,
        updatedAt = employee.updatedAt
      )
    }
  }

}
