package code.blog.snippet

import java.io.BufferedOutputStream
import java.io.File.separator
import java.io.InputStream
import java.nio.file.Files.createDirectories
import java.nio.file.Files.newOutputStream
import java.nio.file.Files.notExists
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.APPEND
import java.nio.file.StandardOpenOption.CREATE

object Utils {

  def storeFile(filename: String, inputStream: InputStream): String = {
    
    def getPath(filename: String): Path = {
      val file_root = Paths.get("/upload/files/")
      if (notExists(file_root)) createDirectories(file_root)
      val path = Paths.get(file_root.toString + separator + filename)
      path
    }

    val path = getPath(filename)
    val out = new BufferedOutputStream(newOutputStream(path, CREATE, APPEND))
    val ba = new Array[Byte](8192)

    def doStore() {
      inputStream.read(ba) match {
        case x if x < 0 =>
        case 0 => doStore()
        case x => out.write(ba, 0, x); doStore()
      }
    }
    
    doStore()
    inputStream.close
    out.close
    "succeed"
  }
}