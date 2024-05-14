package pre
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.util.UUID
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime


class preCreateCaseBookingSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://uk-001.azure-apim.net")
    .acceptHeader("application/json")
    .header("x-ms-pa-client-telemetry-options", "paclient-telemetry {\"operationName\":\"/providers/microsoft.powerapps/apis/shared_prealltest-5f5bc67ff53d0edac1-5f4a54647a9e975fe4.putCase\"}")
    .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
    .header("ServiceNamespace", "PREAPI")
    .contentTypeHeader("application/json")
  val authcode = "Bearer <put your jwt token here> "

  val dataFeeder = csv("src/gatling/scala/resources/createcase.csv").circular
      // Define the date format
  val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
  

    // Calculate tomorrow's date
  val tomorrowDate = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).format(dateFormat)
  val tomorrowDateFormatted = tomorrowDate.format(dateFormat)


  val scn = scenario("CreateCase")
    .feed(dataFeeder)
    .exec(session => session.set("randomId", UUID.randomUUID().toString()))
    .exec(session => session.set("bookingrandomId", UUID.randomUUID().toString()))
    .exec(session => session.set("def_participant", UUID.randomUUID().toString()))
    .exec(session => session.set("wit_participant", UUID.randomUUID().toString()))

    .exec(http("PutCase")
      .put("/apim/prealltest-5f5bc67ff53d0edac1-5f4a54647a9e975fe4/9ac67b68a6b5426eabacb8f5f11ec555/cases/${randomId}")
      .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
      .header("Authorization", "${authcode}")
      .body(StringBody("""{
          "court_id": "${court_id}",
          "id": "${randomId}",
          "participants": [
            {
              "first_name": "${first_name}",
              "id": "${wit_participant}",
              "last_name": "wit",
              "participant_type": "WITNESS"
            },
            {
              "first_name": "${first_name}",
              "id": "${def_participant}",
              "last_name": "def",
              "participant_type": "DEFENDANT"
            }
          ],
          "reference": "${reference}"
        }""")).asJson
      .check(status.is(201)) 
      .check(bodyString.saveAs("responseBody")))
      .exec(session => {
      println("Response body: " + session("responseBody").as[String])
      session
    })
    //booking creation
    .exec(http("Createbooking")
      .put("/apim/prealltest-5f5bc67ff53d0edac1-5f4a54647a9e975fe4/9ac67b68a6b5426eabacb8f5f11ec555/bookings/${bookingrandomId}")
      .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
      .header("Authorization", "${authcode}")
      .body(StringBody("""{
          "case_id": "${randomId}",
          "id": "${bookingrandomId}",
          "participants": [
            {
              "first_name": "${first_name}",
              "id": "${wit_participant}",
              "last_name": "wit",
              "participant_type": "WITNESS"
            },
            {
              "first_name": "${first_name}",
              "id": "${def_participant}",
              "last_name": "def",
              "participant_type": "DEFENDANT"
            }
          ],
          "scheduled_for": "2024-04-12T23:00:00.000Z"
        }""")).asJson
      .check(status.is(201)) // Check if status is 200 OK
      .check(bodyString.saveAs("bookingresponseBody")))
      .exec(session => {
      println("Booking Response body: " + session("bookingresponseBody").as[String])
      session
    })

  setUp(
    scn.inject(atOnceUsers(30))
  ).protocols(httpProtocol)
}

