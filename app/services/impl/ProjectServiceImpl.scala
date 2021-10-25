package services.impl

import daos.ProjectDAO
import exceptions.Exception.NotFoundException
import models.Project
import models.dtos.ProjectDTO
import monix.eval.Task
import services.ProjectService

import javax.inject.Inject

class ProjectServiceImpl @Inject()(projectDAO: ProjectDAO) extends ProjectService {

  override def getAll: Task[Seq[Project]] = projectDAO.getAll

  override def getByID(id: Long): Task[ProjectDTO] = projectDAO.getById(id).map {
    case Some(project) => project.toDTO
    case _ => throw NotFoundException("employee", s"id=$id")
  }
}
