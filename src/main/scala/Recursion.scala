import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Recursion {
  val blueBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)
  val redBox = Image.rectangle(20, 20).fillColor(Color.indianRed)
  val circle = Image.circle(20).lineColor(Color.black).lineWidth(1)
  val chessboardUnit = (Image.rectangle(15,15).fillColor(Color.red) beside
    Image.rectangle(15,15).fillColor(Color.black)) above
    (Image.rectangle(15,15).fillColor(Color.black) beside
      Image.rectangle(15,15).fillColor(Color.red))
  var sierpinskiUnit = Image.triangle(15,15).lineWidth(1).lineColor(Color.red) above
    (Image.triangle(15,15).lineWidth(1).lineColor(Color.red) beside
    Image.triangle(15,15).lineWidth(1).lineColor(Color.red))

  def blueBoxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => blueBox above blueBoxes(n-1)
    }
  }

  def redBoxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => redBox beside redBoxes(n-1)
    }
  }

  def cross(count: Int): Image = {
    count match {
      case 0 => circle
      case n => circle above (circle beside cross(n-1) beside circle) above circle
    }
  }

  def chessboard(count: Int): Image = {
    count match {
      case 0 => chessboardUnit
      case n => (chessboard(n-1) beside chessboard(n-1)) above (chessboard(n-1) beside chessboard(n-1))
    }
  }

  def sierpinski(count: Int): Image = {
    count match {
      case 0 => sierpinskiUnit
      case n => sierpinski(n-1) above (sierpinski(n-1) beside sierpinski(n-1))
    }
  }

  def main(args: Array[String]): Unit = {
//    blueBoxes(4).draw
//    redBoxes(5).draw
//    cross(1).draw
//    cross(2).draw
//    cross(3).draw
//    chessboard(0).draw
//    chessboard(1).draw
//    sierpinski(1).draw
//    sierpinski(2).draw
    sierpinski(5).draw
  }
}
