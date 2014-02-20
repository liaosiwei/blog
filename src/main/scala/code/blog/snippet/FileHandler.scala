package code.blog.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.{S, FileParamHolder}
import net.liftweb.common.{Loggable, Full, Empty, Box}
import code.blog.model.{Post, User}
import scala.xml.Text

class FileHandler extends Loggable {

  def render = User.currentUser match {
    case Full(user) => {
      var upload : Box[FileParamHolder] = Empty
      def processForm() = upload match {
        case Full(FileParamHolder(_, mimeType, fileName, file)) => {
          if ((mimeType == "text/plain") && (!file.isEmpty)) {
            try {
              Post.create.owner(user).name(fileName).
                contents(new String(file, "UTF8")).
                dateOf(new java.util.Date()).save()
              S.notice("upload done.")
            } catch {
              case e: Exception =>
                logger.error("couldn't write to database.")
            }
          }
          else S.notice("is upload file text and not empty?")
        }
        case _ => S.error("No file?")
      }
      "#file" #> fileUpload(f => upload = Full(f)) &
        "type=submit" #> onSubmitUnit(processForm)
    }
    case _ => Text("something is wrong")
  }
}