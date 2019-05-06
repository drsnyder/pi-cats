package picats
import cats._
import RNG._

object PI {
    val MIN_ITER = 100
    case class Step(count: Int, inCircle: Int, lastEstimate: Double)

    def estimatePi(inCircle: Int, total: Int): Double =
      inCircle.toDouble / total.toDouble * 4.0

    def calculatePi(tolerance: Double): RNG[Double] = {
      def step(s: Step): RNG[Either[Step, Double]] =
        for {
          x ← nextDouble
          y ← nextDouble
          isInCircle = (x * x + y * y) < 1.0
          newInCircle = s.inCircle + (if (isInCircle) 1 else 0)
        } yield {
          val newEstimate = estimatePi(newInCircle, s.count + 1)
          if (s.count >= MIN_ITER && Math.abs(newEstimate - s.lastEstimate) <= tolerance)
            Right(newEstimate)
          else
            Left(Step(s.count + 1, newInCircle, newEstimate))
        }

      Monad[RNG].tailRecM(Step(0, 0, tolerance))(step(_))
    }

    def unsafeCalculatePi(tolerance: Double) =
      unsafeRunRng(calculatePi(tolerance))
}