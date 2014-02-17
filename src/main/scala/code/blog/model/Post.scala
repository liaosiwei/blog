package code.blog.model

import net.liftweb.mapper._

/**
 * User: sweeliao@gmail.com
 * Date: 14-1-16
 * Time: 下午7:53
 */
class Post extends LongKeyedMapper[Post] with IdPK {
  def getSingleton = Post

  object owner extends MappedLongForeignKey(this, User)
  object title extends MappedString(this, 140)
  object contents extends MappedText(this)
  object published extends MappedBoolean(this)
}

object Post extends Post with LongKeyedMetaMapper[Post] {
  def findByTitle(owner: User, title: String): List[Post] =
    Post.findAll(By(Post.owner, owner.id.get), By(Post.title, title))
}
