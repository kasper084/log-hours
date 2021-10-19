package models

import models.dtos.OrganisationDTO
import play.api.libs.json.{Json, OFormat}

import java.time.Instant

case class Organisation(id: Long,
                        name: String,
                        address: String,
                        info: String,
                        createdAt: Instant,
                        updatedAt: Instant) {

  def toDTO: OrganisationDTO = {
    OrganisationDTO(
      id,
      name,
      address,
      info,
      createdAt,
      updatedAt
    )
  }

  def updateModifiedField(): Organisation = this.copy(updatedAt = Instant.now())

}

object Organisation {
  implicit val format: OFormat[Organisation] = Json.format[Organisation]

  def tupled: ((Long, String, String, String, Instant, Instant)) => Organisation = (Organisation.apply _).tupled
}
