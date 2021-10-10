package models

import models.dtos.ProjectTaskDTO
import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class ProjectTask(id: Long,
                       projectId: Long,
                       description: String,
                       employeeId: Long,
                       hours: Double,
                       createdAt: Instant,
                       updatedAt: Instant) {

  def toDTO: ProjectTaskDTO = ProjectTaskDTO(
    id,
    projectId,
    description,
    employeeId,
    hours,
    createdAt,
    updatedAt
  )

  def updateModifiedField(): ProjectTask = this.copy(updatedAt = Instant.now())

}

object ProjectTask {
  implicit val format: OFormat[ProjectTask] = Json.format[ProjectTask]

  def tupled: ((Long, Long, String, Long, Double, Instant, Instant)) =>
    ProjectTask = (this.apply _).tupled

}
