package services.impl

import daos.OrganisationDAO
import exceptions.Exception.NotFoundException
import models.Organisation
import models.dtos.OrganisationDTO
import monix.eval.Task
import services.OrganisationService

import javax.inject.Inject

class OrganisationServiceImpl @Inject()(organisationDAO: OrganisationDAO) extends OrganisationService {

  override def getAll: Task[Seq[Organisation]] = organisationDAO.getAll

  override def getByID(id: Long): Task[OrganisationDTO] = organisationDAO.getById(id).map {
    case Some(organisation) => organisation.toDTO
    case _ => throw NotFoundException("employee", s"id=$id")
  }
}
