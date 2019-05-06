package picats
import cats.data._
import cats.implicits._

object RNG {
    type RNG[A] = State[Long, A]
    def nextLong: RNG[Long] =
        State.modify[Long](
            seed => (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
        ) >> State.get

    def nextInt: RNG[Int] = nextLong.map(l => (l >>> 16).toInt)

    def nextNatural: RNG[Int] = nextInt.map { i =>
        if (i > 0) i
        else if (i == Int.MinValue) 0
        else i + Int.MaxValue
    }

    def nextDouble: RNG[Double] = nextNatural.map(_.toDouble / Int.MaxValue)

    def runRng[A](seed: Long)(rng: RNG[A]): A = rng.runA(seed).value

    def unsafeRunRng[A]: RNG[A] ⇒ A = runRng(System.currentTimeMillis)
}