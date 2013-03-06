package util

import scala.util.Random
import java.security.MessageDigest

object Security {
  
  private val SALT_LENGTH = 32
  
  def generateSalt(): String = {
    val random = new Random
    val salt = new Array[Byte](SALT_LENGTH)
    random nextBytes salt
    new String(salt)
  }
  
  def hashPassword(plaintext: String, salt: String): String = {
    digestWithSalt(plaintext.getBytes("utf-8"), salt.getBytes("utf-8"))
  }
  
  def digestWithSalt(password: Array[Byte], salt: Array[Byte]): String = {
    val md = MessageDigest getInstance "SHA1"
    new String(md digest Array.concat(password, salt))
  }
}