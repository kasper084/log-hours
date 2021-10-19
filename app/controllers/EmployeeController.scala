package controllers

import controllers.utils.ControllerUtils
import monix.execution.Scheduler
import play.api.mvc.ControllerComponents
import services.EmployeeService

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext


@Singleton
class EmployeeController @Inject()(cc: ControllerComponents,
                                  )(implicit ex: ExecutionContext,
                                    employeeService: EmployeeService,
                                    sch: Scheduler)
  extends ControllerUtils(cc) {

}
