package code.blog.model

import net.liftweb.mapper._
import java.text.DateFormat
import scala.xml.Text
import net.liftweb.common.{Failure, Empty, Full}

/**
 * User: sweeliao@gmail.com
 * Date: 14-1-16
 * Time: 下午7:53
 */
class Post extends LongKeyedMapper[Post] with IdPK {
  def getSingleton = Post

  object owner extends MappedLongForeignKey(this, User)
  object name extends MappedString(this, 140)
  object contents extends MappedText(this)
  object dateOf extends MappedDateTime(this) {
    final val dateFormat =
      DateFormat.getDateInstance(DateFormat.SHORT)
    override def asHtml = Text(dateFormat.format(get))
  }
}

object Post extends Post with LongKeyedMetaMapper[Post] {
  def findByName(owner: User, name: String): List[Post] =
    Post.findAll(By(Post.owner, owner.id.get), By(Post.name, name))
}
