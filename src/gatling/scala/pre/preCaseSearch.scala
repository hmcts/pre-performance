package pre
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.util.UUID
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.io.{BufferedWriter, FileWriter, File}


class preCaseSearch extends Simulation {

  val httpProtocol = http
    .baseUrl("https://9c803cd6-36f2-e694-bd56-6fd7f5bec9a7.07.custom.uk.azure-apihub.net/")
    .acceptHeader("application/json")
    .header("x-ms-pa-client-telemetry-options", "paclient-telemetry {\"operationName\":\"/providers/microsoft.powerapps/apis/shared_preallstaging-5f75eab36b1ea9bea8-5fe03ba9a62d89fdaa.getCases\"}")
    .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
    .header("ServiceNamespace", "PREAPI")
    .contentTypeHeader("application/json")
    
   //get bearer token from powerapps UI using network tab on /invoke headers
    val authcode = "Bearer "

//   val dataFeeder = csv("src/gatling/scala/resources/createBooking.csv").queue
//       // Define the date format
//   val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
  
  val scn = scenario("SearchCase")
    .exec(http("SearchCase")
      .get(session => {
        val randomRef = scala.util.Random.alphanumeric
          .filter(_.isLetter)
          .take(3)
          .mkString
          .toLowerCase
       s"apim/preallstaging-5f75eab36b1ea9bea8-5fe03ba9a62d89fdaa/bf1057cac4dd43679095d1fd3774391e/cases?reference=$randomRef&courtId=e2ca657c-8f4f-4d41-b545-c434bb779f20&page=0&size=10" 
       })
      .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
      .header("Authorization", authcode)
      .check(status.is(200)) 
      .check(bodyString.saveAs("searchresponseBody")))
      .exec(session => {
      println("get case results.... " + session("searchresponseBody").as[String])
      session
    })

    val recordingscn = scenario("SearchRecordings")

    .exec(http("SearchRecordings")
      .get("apim/preallstaging-5f75eab36b1ea9bea8-5fe03ba9a62d89fdaa/bf1057cac4dd43679095d1fd3774391e/recordings?courtId=e2ca657c-8f4f-4d41-b545-c434bb779f20&sort=r.captureSession.startedAt%2Cr.version%2Cdesc&page=1&size=10")
      .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
      .header("Authorization", authcode)
      .check(status.is(200)) 
      .check(bodyString.saveAs("searchresponseBody")))
      .exec(session => {
      println("get case results.... " + session("searchresponseBody").as[String])
      session
    })

 
  setUp(
    scn.inject(atOnceUsers(160))
  ).protocols(httpProtocol)
}