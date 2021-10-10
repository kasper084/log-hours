package models.dtos

import play.api.libs.json.{Json, OWrites}

import java.time.Instant

case class ProjectTaskDTO(id: Long,
                          projectId: Long,
                          description: String,
                          employeeId: Long,
                          hours: Double,
                          createdAt: Instant,
                          updatedAt: Instant)

object ProjectTaskDTO {
  implicit val writes: OWrites[ProjectTaskDTO] = Json.writes[ProjectTaskDTO]
}
