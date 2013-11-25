package code.blog.snippet

import _root_.net.liftweb._
import http._
import mapper.{Ascending, OrderBy}
import S._
import SHtml._
import common._
import util._
import code.blog.model._
import Helpers._

//import _root_.java.util.Locale
import xml.{Text, Group, NodeSeq}
import code.blog.snippet.Utils.storeFile

class FileHandler {
  
     // the request-local variable that hold the file parameter
  private object theUpload extends RequestVar[Box[FileParamHolder]](Empty)

  /**
   * Bind the appropriate XHTML to the form
   */
  def upload(xhtml: Group): NodeSeq =
    if (S.get_?) (".file_upload" #> fileUpload(ul => theUpload(Full(ul)))) apply chooseTemplate("choose", "get", xhtml)
    else
      (".file_name" #> theUpload.is.map(v => Text(v.fileName)) &
      ".mime_type" #> theUpload.is.map(v => Box.legacyNullTest(v.mimeType).map(Text.apply).openOr(Text("No mime type supplied"))) &
      ".length" #> theUpload.is.map(v => Text(v.file.length.toString)) &
      ".md5" #> theUpload.is.map(v => Text(hexEncode(md5(v.file)))) &
      ".status" #> theUpload.is.map(v => Text(storeFile(v.fileName, v.fileStream)))
      ) apply chooseTemplate("choose", "post", xhtml)
  
  def renderBlog()
}