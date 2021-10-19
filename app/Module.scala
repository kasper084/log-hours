import com.google.inject.AbstractModule
import daos._
import daos.impl._
import services._
import services.impl._

import java.time.Clock

class Module extends AbstractModule {

  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    bind(classOf[EmployeeService]).to(classOf[EmployeeServiceImpl])
    bind(classOf[OrganisationService]).to(classOf[OrganisationServiceImpl])

    bind(classOf[EmployeeDAO]).to(classOf[EmployeeDAOPsqlmpl])
    bind(classOf[OrganisationDAO]).to(classOf[OrganisationDAOPsqlImpl])


  }

}
