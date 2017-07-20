import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Recursion {
  val blueBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)
  val redBox = Image.rectangle(20, 20).fillColor(Color.indianRed)
  val circle = Image.circle(20).lineColor(Color.black).lineWidth(1)
  val chessboardUnit = (Image.rectangle(15, 15).fillColor(Color.red) beside
    Image.rectangle(15, 15).fillColor(Color.black)) above
    (Image.rectangle(15, 15).fillColor(Color.black) beside
      Image.rectangle(15, 15).fillColor(Color.red))
  val sierpinskiUnit = Image.triangle(15, 15).lineWidth(1).lineColor(Color.red) above
    (Image.triangle(15, 15).lineWidth(1).lineColor(Color.red) beside
      Image.triangle(15, 15).lineWidth(1).lineColor(Color.red))
  val rectangle = Image.rectangle(20, 20)

  def blueBoxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => blueBox above blueBoxes(n - 1)
    }
  }

  def redBoxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => redBox beside redBoxes(n - 1)
    }
  }

  def cross(count: Int): Image = {
    count match {
      case 0 => circle
      case n => circle above (circle beside cross(n - 1) beside circle) above circle
    }
  }

  def chessboard(count: Int): Image = {
    count match {
      case 0 => chessboardUnit
      case n => (chessboard(n - 1) beside chessboard(n - 1)) above (chessboard(n - 1) beside chessboard(n - 1))
    }
  }

  def sierpinski(count: Int): Image = {
    count match {
      case 0 => sierpinskiUnit
      case n => sierpinski(n - 1) above (sierpinski(n - 1) beside sierpinski(n - 1))
    }
  }

  def generateBlueBox(size: Int): Image = {
    Image.rectangle(size * 10, size * 10).fillColor(Color.blue).lineColor(Color.purple).lineWidth(2)
  }

  def growingBoxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => growingBoxes(n - 1) beside generateBlueBox(n)
    }
  }

  def generateColoredBox(color: Color, spinVal: Int): Image = {
    rectangle.fillColor(color.spin((spinVal * 10).degrees))
      .lineWidth(1)
      .lineColor(color.spin((10 + spinVal * 15).degrees))
  }

  def gradientBoxes(color: Color, count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => gradientBoxes(color, n - 1) beside generateColoredBox(color, n)
    }
  }

  def generateCircle(diameter: Int, color: Color): Image = {
    Image.circle(diameter).lineColor(color).lineWidth(3)
  }

  def concentricCircles(count: Int, size: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => Image.circle(size) on concentricCircles(n - 1, size + 5)
    }
  }

  def fadeCircles(count: Int, size: Int, color: Color): Image = {
    count match {
      case 0 => Image.empty
      case n => generateCircle(size, color) on
                fadeCircles(n - 1, size + 7, color.fadeOutBy(0.1.normalized))
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
//    sierpinski(5).draw
//    growingBoxes(1).draw
//    growingBoxes(2).draw
//    growingBoxes(3).draw
//    gradientBoxes(Color.blue, 1).draw
//    gradientBoxes(Color.blue, 2).draw
//    gradientBoxes(Color.blue, 5).draw
//    concentricCircles(3, 20).draw
//    fadeCircles(10,20,Color.red).draw
  }
}
