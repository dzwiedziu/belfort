package io.niedzwiedz.belfort

import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.ning.NingWSClient


object HelloWorld extends App {
  val wsClient = NingWSClient()
  val userHandle = sys.env("KOKOS_USER_HANDLE")
  val userPassword = sys.env("KOKOS_USER_PASSWORD")
  val postBody = Map("handle" -> Seq(userHandle), "passwd" -> Seq(userPassword))
  val x = wsClient.url("")
  wsClient.url("https://kokos.pl/uzytkownik/autoryzacja").withFollowRedirects(false).post(postBody).onSuccess {

        case response => {
          val sessionID = response.cookie("PHPSESSID") match {
            case Some(x) => x.value
          }
          sessionID match {
            case Some(x) => {
              println(x)
              wsClient.url("https://kokos.pl/aukcje?id=174001").withHeaders("Cookie" -> s"PHPSESSID=$x;").get().map(rs => println(rs.body.toString()))

            }
          }

        }
    }

}
