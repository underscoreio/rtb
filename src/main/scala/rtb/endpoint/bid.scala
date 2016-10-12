package rtb
package endpoint

import io.finch._
import io.finch.circe._
import io.circe.generic.auto._

object Bid {
  import rtb.model._
  import rtb.action._

  val bid: Endpoint[BidResponse] = post("bid" :: body.as[BidRequest]) { bidRequest: BidRequest =>
    Ok(Decision(bidRequest))
  }
}
