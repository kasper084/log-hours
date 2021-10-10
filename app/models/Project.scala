package models

import java.time.Instant

case class Project(id: Long,
                   organisationName: String,
                   description: String,
                   createdAt: Instant,
                   updatedAt: Instant) {

  def updateModifiedField(): Project = this.copy(updatedAt = Instant.now())

}

object Project {

}