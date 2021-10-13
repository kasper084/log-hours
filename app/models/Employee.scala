package models

import models.dtos.EmployeeDTO
import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class Employee(id: Long,
                    name: String,
                    organisation_name: String,
                    specialisation: String,
                    hour_cost: Double,
                    createdAt: Instant,
                    updatedAt: Instant) {

  def toDTO: EmployeeDTO = EmployeeDTO(
    id,
    name,
    organisation_name,
    specialisation,
    hour_cost,
    createdAt,
    updatedAt
  )

  def updateModifiedField(): Employee = this.copy(updatedAt = Instant.now())

}

object Employee {
  implicit val format: OFormat[Employee] = Json.format[Employee]

  def tupled: ((Long, String, String, String, Double, Instant, Instant)) =>
    Employee = (this.apply _).tupled
}
