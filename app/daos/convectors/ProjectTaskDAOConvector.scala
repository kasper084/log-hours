package daos.convectors

import demo.Tables._
import models.ProjectTask

import java.sql.Timestamp

object ProjectTaskDAOConvector {

  implicit class ProjectTaskRowToModel(projectTasksRow: ProjectTasksRow) {

    def toModel: ProjectTask =
      ProjectTask(
        id = projectTasksRow.id,
        project_id = projectTasksRow.projectId,
        description = projectTasksRow.description,
        employee_id = projectTasksRow.employeeId,
        hours = projectTasksRow.hours,
        createdAt = projectTasksRow.createdAt.toInstant,
        updatedAt = projectTasksRow.updatedAt.toInstant
      )

  }


  implicit class ProjectTaskModelToRow(projectTask: ProjectTask) {

    def toRow: ProjectTasksRow =
      ProjectTasksRow(
        id = projectTask.id,
        projectId = projectTask.project_id,
        description = projectTask.description,
        employeeId = projectTask.employee_id,
        hours = projectTask.hours,
        createdAt = Timestamp.from(projectTask.createdAt),
        updatedAt = Timestamp.from(projectTask.updatedAt)
      )

  }
}
