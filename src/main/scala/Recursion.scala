import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Recursion {
  val circle = Image.circle(20).lineColor(Color.black).lineWidth(1)
  val sierpinskiUnit = Image.triangle(15, 15).lineWidth(1).lineColor(Color.red) above
    (Image.triangle(15, 15).lineWidth(1).lineColor(Color.red) beside
      Image.triangle(15, 15).lineWidth(1).lineColor(Color.red))
  val rectangle = Image.rectangle(20, 20)

  def blueBoxes(count: Int): Image = {
    val box = Image.rectangle(20, 20)
    count match {
      case 0 => Image.empty
      case n => box.fillColor(Color.royalBlue) above blueBoxes(n - 1)
    }
  }

  def redBoxes(count: Int): Image = {
    val box = Image.rectangle(20, 20)
    count match {
      case 0 => Image.empty
      case n => box.fillColor(Color.indianRed) beside redBoxes(n - 1)
    }
  }

  def boxes(count: Int, color: Color): Image = {
    val box = Image.rectangle(20, 20).fillColor(color)
    def loop(count: Int): Image = {
      count match {
        case 0 => Image.empty
        case n => box beside boxes(n - 1, color)
      }
    }

    loop(count)
  }

  def cross(count: Int): Image = {
    count match {
      case 0 => circle
      case n => circle above (circle beside cross(n - 1) beside circle) above circle
    }
  }

  def chessboard(count: Int): Image = {
    val blackBox = Image.rectangle(20, 20).fillColor(Color.black)
    val redBox = Image.rectangle(20, 20).fillColor(Color.red)
    val chessboardUnit = (redBox beside blackBox) above (blackBox beside redBox)

    def loop(count: Int): Image = {
      count match {
        case 0 => chessboardUnit
        case n => val unit = loop(n-1)
          (unit beside unit) above (unit beside unit)
      }
    }

    loop(count)
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

  def circleAtCorners(): Image = {
    val dot = Image.circle(5).lineWidth(3).lineColor(Color.crimson)
    dot.at(0, 0).
      on(dot.at(0, 100)).
      on(dot.at(100, 100)).
      on(dot.at(100, 0))
  }

  def parametricCircle(angle: Angle): Point =
    Point.cartesian(angle.cos * 200, angle.sin * 200)

//  def parametricCircle(angle: Angle): Point =
//    Point.polar(200, angle)

  def sample(start: Angle, samples: Int): Image = {
    // Angle.one is one complete turn. I.e. 360 degrees
    val step = Angle.one / samples
    val dot = triangle(10, 10)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          dot.at(parametricCircle(angle).toVec) on loop(n - 1)
      }
    }

    loop(samples)
  }

  // Parametric equation for rose with k = 7
  def rose(angle: Angle) =
    Point.polar((angle * 7).cos * 200, angle)

  def sampleRose(start: Angle, samples: Int): Image = {
    // Angle.one is one complete turn. I.e. 360 degrees
    val step = Angle.one / samples
    val dot = Image.circle(2)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          dot.at(rose(angle).toVec) on loop(n - 1)
      }
    }

    loop(samples)
  }

  def main(args: Array[String]): Unit = {
//    blueBoxes(4).draw
//    redBoxes(5).draw
//    cross(1).draw
//    cross(2).draw
//    cross(3).draw
//    chessboard(0).draw
//    chessboard(1).draw
//    chessboard(2).draw
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
//    boxes(10, Color.yellow).draw
//    circleAtCorners.draw
//    sample(45.degrees, 4).draw
//    sample(0.degrees, 72).draw
    sampleRose(0.degrees, 200).draw
  }
}
