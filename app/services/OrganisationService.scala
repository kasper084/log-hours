package services

import models.Organisation
import models.dtos.OrganisationDTO
import monix.eval.Task

trait OrganisationService {

  def getAll: Task[Seq[Organisation]]

  def getByID(id: Long):Task[OrganisationDTO]

}
