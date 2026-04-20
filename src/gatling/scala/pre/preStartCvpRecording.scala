package pre

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

//GET csrf token and session id from cvp and reuse in below

class preStartCvpRecording extends Simulation {

  val httpProtocol = http
  val dataFeeder = csv("src/gatling/scala/resources/startliverecording-stg.csv").queue
  val rtmpsfeeder = csv("src/gatling/scala/resources/rtmps.csv").queue
  val cvphost = "cvp portal url here"
  
  val hardcodedcokkie = "visited=yes; csrftoken=E9IbNErvF24RyOhwDNJvlCg5xKPq7SXW; sessionid=d1pzizyqdwrs638at1anr1dsazg9pnjs"

  val loginheaders = Map(
      "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
      "Accept-Language" -> "en-GB,en;q=0.9",
      "Connection" -> "keep-alive",
      "Sec-Fetch-Dest" -> "document",
      "Sec-Fetch-Mode" -> "navigate",
      "Sec-Fetch-Site" -> "none",
      "Sec-Fetch-User" -> "?1",
      "Upgrade-Insecure-Requests" -> "1",
      "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
      "sec-ch-ua" -> "\"Not A(Brand\";v=\"8\", \"Chromium\";v=\"132\", \"Google Chrome\";v=\"132\"",
      "sec-ch-ua-mobile" -> "?0",
      "sec-ch-ua-platform" -> "\"macOS\""
 )
  val authheader = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
    "Accept-Language" -> "en-GB,en;q=0.9",
    "Cache-Control" -> "max-age=0",
    "Connection" -> "keep-alive",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Cookie" -> "csrftoken=mnd7cC4lpNE5kTdOcWAkWfSDSbNHxaQY",
    "Origin" -> cvphost,
    "Referer" -> cvphost.concat("/accounts/login"),
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-origin",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
    "sec-ch-ua" -> "\"Not A(Brand\";v=\"8\", \"Chromium\";v=\"132\", \"Google Chrome\";v=\"132\"",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "\"macOS\""
  )

  val csrfheaders = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7'",
    "Accept-Language" -> "en-GB,en;q=0.9",
    "Connection" -> "keep-alive",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-User" -> "?1",
    "Sec-Fetch-Site" -> "same-origin",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
    "X-Requested-With" -> "XMLHttpRequest",
    "sec-ch-ua" -> "\"Not A(Brand\";v=\"8\", \"Chromium\";v=\"132\", \"Google Chrome\";v=\"132\"",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "\"macOS\"",
    "Cookie" -> hardcodedcokkie
  )

  val save_settings = Map(
    "Accept" -> "application/json, text/javascript, */*; q=0.01'",
    "Accept-Language" -> "en-GB,en;q=0.9",
    "Accept-Encoding" -> "zip, deflate, br, zstd",
    "Connection" -> "keep-alive",
    "Content-Type" -> "application/json; charset=UTF-8",
    "Host" -> "portal.staging.cvp.call.vc",
    "Referer" -> cvphost.concat("/cloudroom/",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
    "X-Requested-With" -> "XMLHttpRequest",
    "sec-ch-ua" -> "\"Not A(Brand\";v=\"8\", \"Chromium\";v=\"132\", \"Google Chrome\";v=\"132\"",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "macOS",
    "Cookie" -> hardcodedcokkie
  )
  val userBatches = List.fill(10)(1)
  val pauseTime = 1

  val scn = scenario("getRoomtoken")

  //authenticate 
    .feed(dataFeeder)
    .feed(rtmpsfeeder)
    .exec(
      http("loginpage")
        .get(cvphost+"/cloudroom")
        .headers(loginheaders)
        .check(status.is(200))
        .check(regex("<input type=\\\"hidden\\\" name=\\\"csrfmiddlewaretoken\\\" value=\\\"([a-zA-Z0-9]+)\\\">").saveAs("initialcsrftoken"))
    )

    .exec(session => {
      println("on load csrf token is....... " + session("initialcsrftoken").as[String])
      session
    })

     .exec(
      http("Login Request")
        .post(cvphost+"/accounts/auth/") // Target endpoint
        .headers(authheader) // Apply headers from the Map
        .formParam("csrfmiddlewaretoken", "${initialcsrftoken}")
        .formParam("username", "satya.chundur@hmcts.net")
        .formParam("password", "<password>")
        .check(status.is(302))
    )
     .pause(pauseTime)
    //get csrf token for specific room
    .exec(
      http("get csrf token")
        .get(cvphost+"/cloudroom/${roomid}/")
        .headers(csrfheaders)
        .check(status.is(200))
        .check(bodyString.saveAs("cloudroomresponse"))
        .check(regex("<input type=\\\"hidden\\\" name=\\\"csrfmiddlewaretoken\\\" value=\\\"([a-zA-Z0-9]+)\\\">").saveAs("csrfToken"))
    )
    .exec(session => {
      //println("cloudroom resonse is.............." +session("cloudroomresponse").as[String])
      println("csrf token is: " + session("csrfToken").as[String])
      session
    })
      .pause(pauseTime)


    //get save settings 

     .exec(
      http("get Save settings")
        .get(cvphost+"/cloudroom/${roomid}/save_settings/")
        .headers(save_settings)
        .header("Cookie", hardcodedcokkie)
        .check(status.is(200))
        .check(bodyString.saveAs("getsavesettingsresponse"))
    )
    .exec(session => {
      println("get save settings reponse is......: " + session("getsavesettingsresponse").as[String])
      session
    })


  //save settings by adding rtpms link
    .exec(
      http("post Save settings")
        .post(cvphost+"/cloudroom/${roomid}/save_settings/")
        .headers(save_settings + ("X-CSRFToken" -> "${csrfToken}"))
        .body(StringBody("""
          {"allow_guests":true,"recording_audio_only":false,"streaming_audio_only":false,"pin":"${pin}","guestpin":"${guestpin}","recording_uri":"${rtmpsuri}","streaming_uri":""}
        """))
        .asJson
        .check(status.is(200))
        .check(bodyString.saveAs("savesettingsresponse"))
    )
    .exec(session => {
      println("save settings reponse is......: " + session("savesettingsresponse").as[String])
      session
    })
    .pause(pauseTime)

   //enter case id and post 
    .exec(
      http("enter case details in cvp")
        .post(cvphost+"/feature/recording-with-case-id/room/${roomid}/set-case-id/")
        .headers(save_settings + ("X-CSRFToken" -> "${csrfToken}"))
        .body(StringBody("\"zzz0-000-perftest01\"")).asJson
        .check(status.is(200))
        .check(bodyString.saveAs("caseidresponse"))
    )
    .exec(session => {
      println("Response Body: " + session("caseidresponse").as[String])
      session
    })
      .pause(pauseTime)
    

   //dial to start recording
    .exec(
      http("start recording")
        .post(cvphost+"/cloudroom/${roomid}/dial/")
        .headers(save_settings + ("X-CSRFToken" -> "${csrfToken}"))
        .body(StringBody("""
        {"destination":"${rtmpsuri}/zzz0-000-perftest01","protocol":"rtmp","role":"guest","streaming":false,"recording":true,"presentation_url":"","audio_only":false}"""))
        .asJson
        .check(status.is(200))
        .check(bodyString.saveAs("dailrespones"))
        .check(bodyString.not("error"))
    )
    .exec(session => {
      
      println("dial response Body for " + session("room").as[String] + " is " + session("dailrespones").as[String])
      val responseBody = session("dailrespones").asOption[String]
      if (responseBody.exists(_.contains("error"))) {
        println(s"Failed Request:Headers: ${session("gatling.http.cache.headers").asOption[Map[String, String]]}")
      }
      session
  })  
  .pause(pauseTime)
  //disconnect the call
  //this is not needed as we will running cron job to finish the recording after certain time

  // .exec(
  //     http("stop recording")
  //       .post("pexip_monitor/api/v1/rooms/${room}/disconnect_all")
  //       .headers(save_settings + ("X-CSRFToken" -> "${csrfToken}"))
  //       .check(status.is(200))
  //       .check(bodyString.saveAs("disconnectrespones"))
  //   )
  //  .exec(session => {
  //     println("disconnect reponse Body: " + session("disconnectrespones").as[String])
  //     session
  //   })


   setUp(
    scn.inject(
      userBatches.flatMap { batch =>
      Seq( // Increase by 20 users
        atOnceUsers(batch),  // Inject 20 users
        nothingFor(7.second) // Pause for 1 seconds
      )
    })
  ).protocols(httpProtocol)
}