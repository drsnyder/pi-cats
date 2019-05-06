package picats
import PI.unsafeCalculatePi


object Main extends App {
  def getTolerance: Double = args match {
    case Array(num, _*) => num.toDouble
    case _ => 0.0001
  }

  val tolerance = getTolerance
  val estimate = unsafeCalculatePi(tolerance)
  println(s"Estimated $estimate for Pi.")
}
