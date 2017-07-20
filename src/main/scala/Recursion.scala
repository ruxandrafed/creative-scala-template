import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Recursion {
  val aBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)

  val oneBox = aBox beside Image.empty
  val twoBoxes = aBox beside aBox

  def boxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => aBox beside boxes(n-1)
    }
  }

  def main(args: Array[String]): Unit = {
    boxes(4).draw
  }
}
