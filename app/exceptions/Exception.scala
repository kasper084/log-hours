package exceptions

import play.api.data.FormError

object Exception {

  case class WrongJsonException(errors: Seq[FormError]) extends RuntimeException(s"Wrong json.\n$errors")

  case object WrongCredentials extends RuntimeException

  case object UnauthorizedException extends RuntimeException

  case class ForbiddenException(message: String) extends RuntimeException("Forbidden. " + message)

  case class DbInternalException(e: Throwable) extends RuntimeException("DB exception", e)

  case object DbResultException extends RuntimeException("No raws was affected")

  case class NotFoundException(message: String) extends RuntimeException(message)

  object NotFoundException {
    def apply(model: String, cond: String): NotFoundException = new NotFoundException(s"$model with $cond not found!")
  }

  case object UserAlreadyExistsException extends RuntimeException

  case class ParamNotPassedException(param: String) extends RuntimeException
}
