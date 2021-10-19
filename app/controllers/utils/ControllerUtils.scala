package controllers.utils

import daos.EmployeeDAO
import exceptions.Exception._
import monix.eval.Task
import monix.execution.Scheduler
import play.api.data.Form
import play.api.mvc._

import scala.concurrent.ExecutionContext


class ControllerUtils(cc: ControllerComponents)
                     (implicit ex: ExecutionContext, sch: Scheduler)
  extends AbstractController(cc) {

  def actionWithRecover(block: CustomRequest[Any] => Task[Result]): Action[AnyContent] =
    Action.async {
      req =>
        block(CustomRequest(req, None, None)).onErrorRecover {
          //401
          case UnauthorizedException => Unauthorized
          //403
          case ForbiddenException(msg) => Forbidden(msg)
          //404
          case NotFoundException(msg) => NotFound(msg)
          //409
          case UserAlreadyExistsException => Conflict
          //412
          case ParamNotPassedException(param) => PreconditionFailed(s"Param $param not passed")
          //417
          case WrongCredentials => ExpectationFailed
          //422
          case WrongJsonException(msg) => UnprocessableEntity(msg.mkString)

          //500
          case e =>
            e.printStackTrace()
            InternalServerError
        }.runToFuture
    }

  def actionWithBody[A](block: CustomRequest[A] => Task[Result])(implicit form: Form[A]): Action[AnyContent] =
    Action.async(req => Task(CustomRequest(req, None, None).bodyAsJson).flatMap(block).runToFuture)

}
