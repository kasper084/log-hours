package models

import models.dtos.EmployeeDTO
import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class Employee(id: Long,
                    name: String,
                    organisation_id: Option[Long],
                    specialisation: String,
                    hour_cost: Double,
                    createdAt: Instant,
                    updatedAt: Instant) {

  def toDTO: EmployeeDTO = EmployeeDTO(
    id,
    name,
    organisation_id,
    specialisation,
    hour_cost,
    createdAt,
    updatedAt
  )

  def updateModifiedField(): Employee = this.copy(updatedAt = Instant.now())

}

object Employee {
  implicit val format: OFormat[Employee] = Json.format[Employee]

  def tupled: ((Long, String, Option[Long], String, Double, Instant, Instant)) =>
    Employee = (this.apply _).tupled
}
