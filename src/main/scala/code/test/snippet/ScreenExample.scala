package code.test.snippet

import net.liftweb.http.{S, LiftScreen}

/**
 * User: sweeliao@gmail.com
 * Date: 13-12-6
 * Time: 下午9:02
 */
object ScreenExample extends LiftScreen{
  val name = field("Name", "")
    // the age has validation rules
  val age = field("Age", 0, minVal(13, "Too Young"))

  def finish() {
    S.notice("Name: "+name)
    S.notice("Age: "+age)
  }
}