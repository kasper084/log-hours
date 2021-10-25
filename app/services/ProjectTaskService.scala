package services

import models.ProjectTask
import models.dtos.ProjectTaskDTO
import monix.eval.Task

trait ProjectTaskService {

  def getAll: Task[Seq[ProjectTask]]

  def getByID(id: Long):Task[ProjectTaskDTO]

}
