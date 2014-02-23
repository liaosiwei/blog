package code.blog.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.S

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-21
 * Time: 上午10:43
 */
object CustomMenu {

  val menulist = List("/userprofile/" -> "自我介绍", "/archive/" -> "博客存档")

  def render = {
    val sep = S.uri.lastIndexOf('/')
    val idString = S.uri.substring(sep+1)
    val mlist = menulist.map(m => (m._1 + idString, m._2))
    "#link *" #> mlist.map(m => {
      if (m._1 == S.uri)
        <span>{m._2}</span>
      else
        <a href={m._1}>{m._2}</a>
    })
  }
}
