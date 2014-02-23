package code.blog.snippet

import net.liftweb.sitemap.{*, Loc, Menu}
import code.blog.model.User
import scala.xml.{NodeSeq, Text}
import net.liftweb.http.S
import net.liftweb.common.Full
import net.liftweb.util.Helpers._


/**
 * User: sweeliao@gmail.com
 * Date: 14-2-22
 * Time: 下午7:53
 */


object UserProfileParam {
  lazy val menu = Menu.param[User]("UserProfile", "自我介绍", id => User.find(id),
    (user: User) => user.id.get.toString) / "userprofile"
}

class UserProfile(user: User) {

  def render = {
    val profile = user.textArea.get.toString
    "#sidenav *" #> <div data-lift="embed?what=templates-hidden/menu"></div> &
    "#name *" #> user.shortName &
    "#intro *" #> (if (profile.isEmpty) "博主很懒，没留下太多。。。" else profile)
  }
}
