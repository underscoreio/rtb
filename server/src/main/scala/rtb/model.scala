package doodlebot

import cats.data.ValidatedNel
import cats.std.list._
import cats.syntax.cartesian._
import java.util.UUID

object model {
  import doodlebot.validation._
  import doodlebot.syntax.validation._
  import doodlebot.validation.Predicate._

  // Messages to the client
  final case class Authenticated(name: String, session: String)
  final case class Log(offset: Int, messages: List[Message])
  final case class Message(author: String, message: String)
  final case class FormErrors(errors: InputError) extends Exception

  // Messages from the client
  final case class Login(name: Name, password: Password)

  // Wrappers
  final case class Name(get: String) extends AnyVal
  object Name {
    def validate(name: String): ValidatedNel[String,Name] = {
      name.validate(lengthAtLeast(6) and onlyLettersOrDigits).map(n => Name(n))
    }
  }
  final case class Email(get: String) extends AnyVal
  object Email {
    def validate(email: String): ValidatedNel[String,Email] = {
      email.validate(containsAllChars("@.")).map(e => Email(e))
    }
  }
  final case class Password(get: String) extends AnyVal
  object Password {
    def validate(password: String): ValidatedNel[String,Password] = {
      password.validate(lengthAtLeast(8)).map(p => Password(p))
    }
  }
  final case class Session(get: UUID = UUID.randomUUID()) extends AnyVal

  // State
  final case class User(name: Name, email: Email, password: Password)
  object User {
    def validate(name: String, email: String, password: String): ValidatedNel[String,User] = {
      (Name.validate(name) |@| Email.validate(email) |@| Password.validate(password)).map { User.apply _ }
    }
  }
}
