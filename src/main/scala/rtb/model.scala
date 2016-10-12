package rtb

object model {
  final case class BidRequest(
    bidId: String
  )
  //,
    //creativeSizing: Dimensions,
    //device: String,
    //priceFloor: Double,
    //origin: Origin,
    //country: String
  //)

  sealed trait Origin
  final case class App(bundle: String) extends Origin
  final case class Web(url: String) extends Origin

  sealed trait Dimensions
  case object FullScreen extends Dimensions
  case object Mobile350x200 extends Dimensions
  case object Mobile728x90 extends Dimensions
  final case class NonStandard(width: Int, height: Int) extends Dimensions


  sealed trait BidResponse
  final case class Bid(bidId: String, amount: Double, adMarkup: String, adMacro: String) extends BidResponse
  final case class NoBid(bidId: String) extends BidResponse
}
