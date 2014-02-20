package code.test.snippet

import net.liftweb.http.{SHtml, S, RequestVar}
import net.liftweb.common.Full
import net.liftweb.util.Helpers
import Helpers._

/**
 * User: sweeliao@gmail.com
 * Date: 13-12-6
 * Time: 下午8:39
 */
object ReqVar {
  // define RequestVar holders for name, age, and whence
  private object name extends RequestVar("")
  private object age extends RequestVar("0")
  private object whence extends RequestVar(S.referer openOr "/")
  def render = {
    // capture the whence... which forces evaluation of
    // the whence RequestVar unless it's already been set
    val w = whence.is
    // we don't need an explicit function because RequestVar
    // extends Settable{type=String}, so Lift knows how to
    // get/set the RequestVar for text element creation
    "name=name" #> SHtml.textElem(name) &
      // add a hidden field that sets whence so we
      // know where to go
      "name=age" #> (SHtml.textElem(age) ++
        SHtml.hidden(() => whence.set(w))) &
      "type=submit" #> SHtml.onSubmitUnit(process)
  }
  // process the same way as
  // in Stateful
  private def process() =
    asInt(age.is) match {
      case Full(a) if a < 13 => S.error("age", "Too young!")
      case Full(a) => {
        S.notice("Name: "+name)
        S.notice("Age: "+a)
        S.redirectTo(whence)
      }

      case _ => S.error("age", "Age doesn't parse as a number")
    }
}
