package rtb

import com.twitter.finagle.Http
import io.circe._
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

object Rtb {
  import rtb.endpoint._

  implicit val encodeException: Encoder[Exception] = Encoder.instance {
    case e => Json.obj(
      "message" -> Json.fromString(s"""Exception was thrown: ${e.getMessage}""")
    )
  }

  val service =
    Static.static :+: Bid.bid

  val server = {
    Http.serve(":8080", service.toServiceAs[Application.Json])
  }

}
