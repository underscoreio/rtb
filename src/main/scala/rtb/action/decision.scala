package rtb
package action

import rtb.model._

object Decision {
  def apply(request: BidRequest): BidResponse =
    NoBid(request.bidId)
}
