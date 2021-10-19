import com.google.inject.AbstractModule
import daos.EmployeeDAO
import daos.impl.EmployeeDAOPsqlmpl

import java.time.Clock

class Module extends AbstractModule {

  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    bind(classOf[EmployeeDAO]).to(classOf[EmployeeDAOPsqlmpl])


  }

}
