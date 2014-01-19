package code.blog
package model

import net.liftweb.mapper._
import net.liftweb.common._

/**
 * The singleton that has methods for accessing the database
 */
object User extends User with MetaMegaProtoUser[User] {
  override def dbTableName = "users" // define the DB table name
  override def screenWrap = Full(<lift:surround with="default" at="content-right">
      <div id="formBox">
        <lift:bind/>
      </div>
    </lift:surround>)
  // define the order fields will appear in forms and output
  override def fieldOrder = List(id, firstName, lastName, email,
  password, textArea)

  // comment this line out to require email validations
  override def skipEmailValidation = true

  override def signupFields: List[FieldPointerType] = List(firstName,
    lastName,
    email,
    password)

  // to override the redirected page after user login in successfully
  override def homePage = "/"
}

/**
 * An O-R mapped "User" class that includes first name, last name, password and we add a "Personal Essay" to it
 */
class User extends MegaProtoUser[User] {
  def getSingleton = User // what's the "meta" server

  // define an additional field for a personal essay
  object textArea extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "个人说明"
  }

  object userPic extends MappedBinary(this)

  def posts: List[Post] = Post.findAll(By(Post.owner, id))
}

