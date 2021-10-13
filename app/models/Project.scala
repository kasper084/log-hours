package models

import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class Project(id: Long,
                   organisation_name: String,
                   description: String,
                   createdAt: Instant,
                   updatedAt: Instant) {

  def updateModifiedField(): Project = this.copy(updatedAt = Instant.now())

}

object Project {
  implicit val format: OFormat[Project] = Json.format[Project]

  def tupled: ((Long, String, String, Instant, Instant)) => Project = (this.apply _).tupled

}