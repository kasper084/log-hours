package models.dtos

import play.api.libs.json.{Json, OWrites}

import java.time.Instant

case class ProjectDTO(id: Long,
                      organisation_id: Option[Long],
                      description: String,
                      createdAt: Instant,
                      updatedAt: Instant)

object ProjectDTO {
  implicit val writes: OWrites[ProjectDTO] = Json.writes[ProjectDTO]
}
