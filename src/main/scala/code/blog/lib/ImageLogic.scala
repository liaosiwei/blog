package code.blog.lib

import net.liftweb.common.{Box, Full}
import net.liftweb.http.{GetRequest,LiftRules, Req, LiftResponse, InMemoryResponse}
import net.liftweb.mapper._
/**
 * User: sweeliao@gmail.com
 * Date: 14-1-17
 * Time: 下午9:04
 */
// object ImageLogic {
//   lazy val imageData: Array[Byte] = {
//     val baos = new java.io.ByteArrayOutputStream
//     val is = new java.io.FileInputStream("/Users/rmm/Pictures/readin icon100.jpg")
//     val buf = new Array[Byte](1024)
//     var bytesRead = 0
//     while ({ bytesRead = is.read(buf); bytesRead } >= 0) {
//       baos.write(buf, 0, bytesRead)
//     }
//     baos.toByteArray
//   }

//   object TestImage {
//     def unapply(in: String): Option[Image] =
//       Image.find(By(Image.lookup, in.trim))
//   }

//   def matcher: LiftRules.DispatchPF = {
//     case req @ Req("image" :: TestImage(img) :: Nil, _, GetRequest) =>
//       () => serveImage(img, req)
//   }

//   def serveImage(img: Image, req: Req) : Box[LiftResponse] = {
//     if (req.testIfModifiedSince(img.saveTime.is+1)) {
//       //if not modified, optimized to tell browser to use last good version
//       Full(InMemoryResponse(
//         new Array[Byte](0),
//         List("Last-Modified" -> toInternetDate(img.saveTime.is)),
//         Nil,
//         304))
//     } else {
//       //serve the image
//       Full(InMemoryResponse(
//         imageData,
//         List("Last-Modified" -> toInternetDate(img.saveTime.is),
//           "Content-Type" -> "image/jpeg",//img.mimeType.is,
//           "Content-Length" -> imageData.length.toString),
//         Nil /*cookies*/,
//         200)
//       )
//     }
//   }//end serveImage

// }//end object ImageLogic
