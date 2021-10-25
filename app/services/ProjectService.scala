package services

import models.Project
import models.dtos.ProjectDTO
import monix.eval.Task

trait ProjectService {

  def getAll: Task[Seq[Project]]

  def getByID(id: Long):Task[ProjectDTO]

}
