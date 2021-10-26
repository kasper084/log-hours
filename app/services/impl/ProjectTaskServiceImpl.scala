package services.impl

import daos.ProjectTaskDAO
import exceptions.Exception.NotFoundException
import models.ProjectTask
import models.dtos.ProjectTaskDTO
import monix.eval.Task
import services.ProjectTaskService

import javax.inject.Inject

class ProjectTaskServiceImpl @Inject()(projectTaskDAO: ProjectTaskDAO) extends ProjectTaskService {

  override def getAll: Task[Seq[ProjectTask]] = projectTaskDAO.getAll

  override def getByID(id: Long): Task[ProjectTaskDTO] = projectTaskDAO.getById(id).map {
    case Some(projectTask) => projectTask.toDTO
    case _ => throw NotFoundException("project task", s"id=$id")
  }
}
