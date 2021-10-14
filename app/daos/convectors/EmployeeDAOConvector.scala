package daos.convectors

import demo.Tables.EmployeesRow
import models.Employee

object EmployeeDAOConvector {

  implicit class EmployeeRowToModel(employeesRow: EmployeesRow) {

    def toModel(): Employee = {
      Employee(
        id = employeesRow.id,
        name = ???,
        organisation_id = ???,
        specialisation = ???,
        hour_cost = ???,
        createdAt = ???,
        updatedAt = ???
      )
    }

  }

}
