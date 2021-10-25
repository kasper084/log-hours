package daos.convectors

import models.Project
import demo.Tables.ProjectsRow

import java.sql.Timestamp

object ProjectDAOConvector {

  implicit class ProjectRowToModel(projectsRow: ProjectsRow) {
    def toModel: Project = {
      Project(
        id = projectsRow.id,
        organisation_id = projectsRow.organisationId,
        description = projectsRow.description,
        createdAt = projectsRow.createdAt.toInstant,
        updatedAt = projectsRow.updatedAt.toInstant

      )
    }
  }

  implicit class ProjectModelToRow(project: Project) {
    def toRow: ProjectsRow = {
      ProjectsRow(
        id = project.id,
        organisationId = project.organisation_id,
        description = project.description,
        createdAt = Timestamp.from(project.createdAt),
        updatedAt = Timestamp.from(project.updatedAt)

      )
    }
  }

}
