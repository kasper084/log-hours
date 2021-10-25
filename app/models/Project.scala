package models

import models.dtos.ProjectDTO
import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class Project(id: Long,
                   organisation_id: Option[Long],
                   description: String,
                   createdAt: Instant,
                   updatedAt: Instant) {

  def toDTO: ProjectDTO = ProjectDTO(
    id,
    organisation_id,
    description,
    createdAt,
    updatedAt
  )

  def updateModifiedField(): Project = this.copy(updatedAt = Instant.now())

}

object Project {
  implicit val format: OFormat[Project] = Json.format[Project]

  def tupled: ((Long, Option[Long], String, Instant, Instant)) => Project = (this.apply _).tupled

}