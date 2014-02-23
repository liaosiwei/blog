package code.blog.snippet

import net.liftweb.util.Helpers._
import code.blog.model.{User, Post}
import net.liftweb.sitemap.Menu
import net.liftweb.mapper.By
import Utils.{slashYear, slashDate}

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-21
 * Time: 上午10:05
 */

object BlogArchiveParam {
  lazy val menu = Menu.param[User]("BlogArchive", "博客存档", id => User.find(id),
    (user: User) => user.id.get.toString) / "archive"
}

class BlogArchive(user: User) {
  def render = {
    "#sidenav *" #> <div data-lift="embed?what=templates-hidden/menu"></div> &
    "#archive *" #> Post.findAll(By(Post.owner, user.id.get)).groupBy(x => slashYear.format(x.dateOf.get)).map(post => {
      "#year *" #> post._1 &
      "li *" #> post._2.map(p => {
        "#timestap *" #> slashDate.format(p.dateOf.get) &
        ".blogname [href]" #> ("/blog?id=" + p.id.get) &
        ".blogname *" #> p.name.get.split(".txt")(0)
      })
    })
  }
}