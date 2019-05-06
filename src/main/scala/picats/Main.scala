package picats
import PI.unsafeCalculatePi


object Main extends App {
  def getIterations: Int = args match {
    case Array(num, _*) => num.toInt
    case _ => 1000
  }

  val iterations = getIterations
  val estimate = unsafeCalculatePi(iterations)
  println(s"Estimated $estimate for Pi after $iterations iterations.")
}
