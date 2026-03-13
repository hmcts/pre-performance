package pre
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.util.UUID
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.io.{BufferedWriter, FileWriter, File}


class preCreateCaseBookingSimulation extends Simulation {
  val environment = ""

  val httpProtocol = http
    .baseUrl("https://9c803cd6-36f2-e694-bd56-6fd7f5bec9a7.07.custom.uk.azure-apihub.net/")
    .acceptHeader("application/json")
    .header("x-ms-pa-client-telemetry-options", "paclient-telemetry {\"operationName\":\"/providers/microsoft.powerapps/apis/shared_prealltest-5f5bc67ff53d0edac1-5f4a54647a9e975fe4.putCase\"}")
    .header("X-User-Id", "")
    .header("ServiceNamespace", "PREAPI")
    .contentTypeHeader("application/json")
  //get bearer token from powerapps UI using network tab on /invoke headers
  val authcode = "Bearer "

  val dataFeeder = csv("src/gatling/scala/resources/createBooking.csv").queue
      // Define the date format
  val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
  

    // Calculate tomorrow's date
  val tomorrowDate = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).format(dateFormat)
  val tomorrowDateFormatted = tomorrowDate.format(dateFormat)

val outputCsvPath = "output.csv"

// Utility function to write data to a CSV with headers
def writeToCsvWithHeaders(caseref: String, caseid: String, bookingrandomId: String, wit_participant: String, def_participant: String  ): Unit = {
  val file = new File(outputCsvPath)
  val append = file.exists() // Check if the file already exists

  val writer = new BufferedWriter(new FileWriter(file, true)) // Enable append mode
  try {
    if (!append) {
      // Write headers if the file is being created for the first time
      writer.write("caseref,caseid,bookingrandomId,witid,def_id\n")
    }
    // Write data in CSV format
    writer.write(s"$caseref,$caseid,$bookingrandomId,$wit_participant,$def_participant\n")
  } finally {
    writer.close()
  }
}
  val scn = scenario("CreateCase")
    .feed(dataFeeder)
    //for case and booking creating from scrach enable below
    // .exec(session => session.set("randomId", UUID.randomUUID().toString()))
    // .exec(session => session.set("bookingrandomId", UUID.randomUUID().toString()))
    // .exec(session => session.set("def_participant", UUID.randomUUID().toString()))
    // .exec(session => session.set("wit_participant", UUID.randomUUID().toString()))
    //for just bookings only we need booking randomid
    .exec(session => session.set("bookingrandomId", UUID.randomUUID().toString()))

    // .exec(http("PutCase")
    //   .put("apim/preallstaging-5f75eab36b1ea9bea8-5fe03ba9a62d89fdaa/bf1057cac4dd43679095d1fd3774391e/cases/${randomId}")
    //   .header("X-User-Id", "edcc2ff4-22ba-4a05-8ec0-6c8f7a1bcbb4")
    //   .header("Authorization", authcode)
    //   .body(StringBody("""{
    //       "court_id": "e2ca657c-8f4f-4d41-b545-c434bb779f20",
    //       "id": "${randomId}",
    //       "participants": [
    //         {
    //           "first_name": "${reference}",
    //           "id": "${wit_participant}",
    //           "last_name": "wit",
    //           "participant_type": "WITNESS"
    //         },
    //         {
    //           "first_name": "${reference}",
    //           "id": "${def_participant}",
    //           "last_name": "def",
    //           "participant_type": "DEFENDANT"
    //         }
    //       ],
    //       "reference": "${reference}"
    //     }""")).asJson
    //   .check(status.is(201)) 
    //   .check(bodyString.saveAs("responseBody")))
    //   //.check(jsonPath("$.id").saveAs("id")) // Extract `caseid`
    //   // .check(jsonPath("$.participants[?(@.participant_type=='WITNESS')].id").saveAs("wit_participant")) // Extract `wit_participant`
    //   // .check(jsonPath("$.participants[?(@.participant_type=='DEFENDANT')].id").saveAs("def_participant"))) // Extract `wit_participant`
    //   .exec(session => {
    //   println("case id:.... " + session("reference").as[String])
    //   session
    // })
    //booking creation
    .exec(http("Createbooking")
      .put("apim/preallstaging-5f75eab36b1ea9bea8-5fe03ba9a62d89fdaa/bf1057cac4dd43679095d1fd3774391e/bookings/${bookingrandomId}")
      .header("X-User-Id", "")
      .header("Authorization", authcode)
      .body(StringBody("""{
          "case_id": "${caseid}",
          "id": "${bookingrandomId}",
          "participants": [
            {
              "first_name": "${reference}",
              "id": "${witid}",
              "last_name": "wit",
              "participant_type": "WITNESS"
            },
            {
              "first_name": "${reference}",
              "id": "${defid}",
              "last_name": "def",
              "participant_type": "DEFENDANT"
            }
          ],
          "scheduled_for": "2026-03-11T23:00:00.000Z"
        }""")).asJson
      .check(status.is(201)) // Check if status is 200 OK
      .check(bodyString.saveAs("bookingresponseBody")))
      .exec(session => {
      println("Booking Response body: " + session("bookingresponseBody").as[String])
      //enable below if you are creating both booking and cases at the same time
      // val caseref = session("reference").as[String]
      // val caseid = session("randomId").as[String]
      // val bookingrandomId = session("bookingrandomId").as[String]
      // val wit_participant = session("wit_participant").as[String]
      // val def_participant = session("def_participant").as[String]
      //enable below if you are creating only bookings
      val caseref = session("reference").as[String]
      val caseid = session("caseid").as[String]
      val bookingrandomId = session("bookingrandomId").as[String]
      val wit_participant = session("witid").as[String]
      val def_participant = session("defid").as[String]
      

      
      // Write to CSV
        writeToCsvWithHeaders(caseref, caseid, bookingrandomId, wit_participant,def_participant)
      session
    })
 
  setUp(
    scn.inject(atOnceUsers(160))
  ).protocols(httpProtocol)
}