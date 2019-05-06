package picats
import cats._
import RNG._

object PI {
    case class Step(count: Int, inCircle: Int)

    def calculatePi(iterations: Int): RNG[Double] = {
      def step(s: Step): RNG[Either[Step, Double]] =
        for {
          x ← nextDouble
          y ← nextDouble
          isInCircle = (x * x + y * y) < 1.0
          newInCircle = s.inCircle + (if (isInCircle) 1 else 0)
        } yield {
          if (s.count >= iterations)
            Right(s.inCircle.toDouble / s.count.toDouble * 4.0)
          else
            Left(Step(s.count + 1, newInCircle))
        }

      Monad[RNG].tailRecM(Step(0, 0))(step(_))
    }

    def unsafeCalculatePi(iterations: Int) =
      unsafeRunRng(calculatePi(iterations))
}