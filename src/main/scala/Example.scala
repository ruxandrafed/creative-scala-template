import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image1 = (triangle(40, 40)
    lineWidth 6.0
    lineColor Color.darkSlateBlue
    fillColor (Color.darkSlateBlue lighten 0.3.normalized saturate 0.2.normalized spin 10.degrees)) above
    ((triangle(40, 40)
      lineWidth 6.0
      lineColor (Color.darkSlateBlue spin (-30.degrees))
      fillColor (Color.darkSlateBlue lighten 0.3.normalized saturate 0.2.normalized spin (-20.degrees))) beside
      (triangle(40, 40)
        lineWidth 6.0
        lineColor (Color.darkSlateBlue spin (30.degrees))
        fillColor (Color.darkSlateBlue lighten 0.3.normalized saturate 0.2.normalized spin (40.degrees))))

  val image2 = (circle(10) fillColor Color.red) on
    (circle(20) fillColor Color.white) on
    (circle(30) fillColor Color.red) above
    (rectangle(5,10) lineColor Color.black lineWidth 1) above
    (rectangle(20,5) fillColor Color.darkRed) above
    (rectangle(60,20) fillColor Color.green)

  def main(args: Array[String]): Unit = {
    image1.draw
    image2.draw
  }
}
