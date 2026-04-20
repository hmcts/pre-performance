package pre
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.util.UUID
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class preCreateLiveEvent extends Simulation {

  // Base URL for your API
  //val baseUrl = "https://sds-api-mgmt.test.platform.hmcts.net"
  // Base URL using api connector
  val baseUrl = "https://9c803cd6-36f2-e694-bd56-6fd7f5bec9a7.07.custom.uk.azure-apihub.net"
  val dataFeeder = csv("src/gatling/scala/resources/createliveevent.csv").queue
  val authcode ="Bearer <>"

  // HTTP configuration
  val httpProtocol = http
    .baseUrl(baseUrl) // Base URL for all requests
    .header("Content-Type", "application/json") // Common headers
    .header("Authorization", authcode) // Authorization token

  //when running test using flow use below scenario
  val userBatches = List.fill(5)(32)
  val scn = scenario("createliveevent")
    .feed(dataFeeder)
    .exec(
      http("Runcreateliveeventflow")
        .post("/apim/logicflows/fc5b134157990785b476ff2a00622dc9-f91b58d11dde94a0/triggers/manual/run?api-version=2015-02-01-preview")
        .header("x-ms-pa-client-telemetry-options", """paclient-telemetry {"operationName":"/providers/microsoft.powerapps/apis/shared_logicflows.Run"}""")
        .header("ServiceNamespace", "App_API_AMS_CreateAndStartLiveEvent")
        .header("Content-Type", "application/json")
        .body(StringBody(
          """{
            "text": "${bookingrandomId}",
            "text_2": "${caseref}",
            "text_3": "Leeds Youth",
            "text_1": "satya.chundur@hmcts.net",
            "text_4": "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4",
            "text_5": "ce7a2af2-0759-ed11-9562-00224841bc3b"
          }""")).asJson
          .check(status.is(202))) // Check if the response status is 201

  setUp(
    scn.inject(
      userBatches.flatMap { batch =>
      Seq( // Increase by 20 users
        atOnceUsers(batch),  // Inject 20 users
        nothingFor(5.minutes) // Pause for 5 minutes
      )
    })
  ).protocols(httpProtocol)
}