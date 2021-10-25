package daos.convectors

import demo.Tables.OrganisationsRow
import models.Organisation

import java.sql.Timestamp

object OrganisationDAOConvector {

  implicit class OrganisationRowToModel(organisationsRow: OrganisationsRow) {
    def toModel: Organisation = {
      Organisation(
        id = organisationsRow.id,
        name = organisationsRow.name,
        address = organisationsRow.address,
        info = organisationsRow.info,
        createdAt = organisationsRow.createdAt.toInstant,
        updatedAt = organisationsRow.updatedAt.toInstant
      )
    }
  }

  implicit class OrganisationModelToRow(organisation: Organisation) {

    def toRow: OrganisationsRow = {
      OrganisationsRow(
        id = organisation.id,
        name = organisation.name,
        address = organisation.address,
        info = organisation.info,
        createdAt = Timestamp.from(organisation.createdAt),
        updatedAt = Timestamp.from(organisation.updatedAt)
      )
    }
  }

}
