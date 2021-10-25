package daos.convectors

import demo.Tables.EmployeesRow
import models.Employee

import java.sql.Timestamp

object EmployeeDAOConvector {

  implicit class EmployeeRowToModel(employeesRow: EmployeesRow) {

    def toModel: Employee = {
      Employee(
        id = employeesRow.id,
        name = employeesRow.name,
        organisation_id = employeesRow.organisationId,
        specialisation = employeesRow.specialisation,
        hour_cost = employeesRow.hourCost,
        createdAt = employeesRow.createdAt.toInstant,
        updatedAt = employeesRow.updatedAt.toInstant
      )
    }

  }

  implicit class EmployeeModelToRow(employee: Employee) {

    def toRow: EmployeesRow = {
      EmployeesRow(
        id = employee.id,
        name = employee.name,
        organisationId = employee.organisation_id,
        specialisation = employee.specialisation,
        hourCost = employee.hour_cost,
        createdAt = Timestamp.from(employee.createdAt),
        updatedAt = Timestamp.from(employee.updatedAt)
      )
    }
  }

}
