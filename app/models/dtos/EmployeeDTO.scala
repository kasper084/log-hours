package models.dtos

import play.api.libs.json.{Json, OWrites}

import java.time.Instant

case class EmployeeDTO(id: Long,
                       name: String,
                       organisation_name: String,
                       specialisation: String,
                       hour_cost: Double,
                       createdAt: Instant,
                       updatedAt: Instant)

object EmployeeDTO {
  implicit val writes: OWrites[EmployeeDTO] = Json.writes[EmployeeDTO]
}
