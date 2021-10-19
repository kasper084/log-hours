package models.dtos

import play.api.libs.json.{Json, OWrites}

import java.time.Instant

case class OrganisationDTO(id: Long,
                           name: String,
                           address: String,
                           info: String,
                           createdAt: Instant,
                           updatedAt: Instant)

object OrganisationDTO {
  implicit val writes: OWrites[EmployeeDTO] = Json.writes[EmployeeDTO]
}
