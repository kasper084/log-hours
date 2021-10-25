package models

import models.dtos.ProjectTaskDTO
import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class ProjectTask(id: Long,
                       project_id: Option[Long],
                       description: String,
                       employee_id: Option[Long],
                       hours: Double,
                       createdAt: Instant,
                       updatedAt: Instant) {

  def toDTO: ProjectTaskDTO = ProjectTaskDTO(
    id,
    project_id,
    description,
    employee_id,
    hours,
    createdAt,
    updatedAt
  )

  def updateModifiedField(): ProjectTask = this.copy(updatedAt = Instant.now())

}

object ProjectTask {
  implicit val format: OFormat[ProjectTask] = Json.format[ProjectTask]

  def tupled: ((Long, Option[Long], String, Option[Long], Double, Instant, Instant)) =>
    ProjectTask = (this.apply _).tupled

}
