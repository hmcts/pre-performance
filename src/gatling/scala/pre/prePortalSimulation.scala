
package  pre
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

class prePortalSimulation extends Simulation {
    


   val httpProtocol = http
    .baseUrl("https://hmctsdevextid.b2clogin.com")
    .inferHtmlResources()
  
   val headers_0 = Map(
  		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "document",
  		"sec-fetch-mode" -> "navigate",
  		"sec-fetch-site" -> "none",
  		"sec-fetch-user" -> "?1",
  		"upgrade-insecure-requests" -> "1",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_1 = Map(
  		"User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS"
  )
  
  
   val headers_9 = Map(
  		"Accept" -> "application/json, text/javascript, */*; q=0.01",
  		"Accept-Encoding" -> "gzip, deflate, br, zstd",
  		"Accept-Language" -> "en-GB,en;q=0.9",
  		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
  		"Origin" -> "https://hmctsdevextid.b2clogin.com",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "same-origin",
  		"User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36",
  		"X-CSRF-TOKEN" -> "bVpGdU5KQlVEaHdwdjhLWnlHazFTemVhOUVQTWNmb2c5V1FOTS9WOTd4c3JtS254aS9wWENBcG9ucVVVZVMvYUdJNy9ISWVBK3QwNmFhcXErdWdxaEE9PTsyMDI0LTA0LTEwVDExOjUwOjMyLjAxMzI0NDRaO3ZreDc5TjFyNG9yOGx2VE9FUmIxOWc9PTt7Ik9yY2hlc3RyYXRpb25TdGVwIjoxfQ==",
  		"X-Requested-With" -> "XMLHttpRequest",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS"
  )
      
  
   val headers_23 = Map(
  		"accept" -> "text/css,*/*;q=0.1",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "style",
  		"sec-fetch-mode" -> "no-cors",
  		"sec-fetch-site" -> "same-origin",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_24 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "script",
  		"sec-fetch-mode" -> "no-cors",
  		"sec-fetch-site" -> "same-origin",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_25 = Map(
  		"accept" -> "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "image",
  		"sec-fetch-mode" -> "no-cors",
  		"sec-fetch-site" -> "same-origin",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_26 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"origin" -> "https://pre-portal.staging.platform.hmcts.net",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "font",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_28 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "manifest",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_31 = Map(
  		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "document",
  		"sec-fetch-mode" -> "navigate",
  		"sec-fetch-site" -> "same-origin",
  		"upgrade-insecure-requests" -> "1",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_42 = Map(
  		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "document",
  		"sec-fetch-mode" -> "navigate",
  		"sec-fetch-site" -> "same-origin",
  		"sec-fetch-user" -> "?1",
  		"upgrade-insecure-requests" -> "1",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val headers_45 = Map(
  		"Origin" -> "https://pre-portal.staging.platform.hmcts.net",
  		"User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS"
  )
  
   val headers_50 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"content-type" -> "application/json",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  		//"x-dtpc" -> "3$350273702_89h2vVUPLRBNLAJBUUAGHQFKTHJMAUKRFUFQH-0e0"
  )
  
   val headers_51 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en;q=0.9",
  		"origin" -> "https://pre-portal.staging.platform.hmcts.net",
  		"sec-ch-ua" -> """Google Chrome";v="123", "Not:A-Brand";v="8", "Chromium";v="123""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "font",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "cross-site",
  		"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
  )
  
   val dynatrace = "https://bf24054dsx.bf.dynatrace.com/bf"
  
   val amsStreaming = "https://preamstest-ukso1.streaming.media.azure.net/0ad82eca-50e2-4cdd-a409-4b867140525c/video_2000000.ism/"
  
   val amsKeydelivery = "https://preamstest.keydelivery.uksouth.media.azure.net"
  
   val b2cResources = "https://presadev.blob.core.windows.net/pre-b2c-container"
  
   val b2cSignInPage = "https://hmctsdevextid.b2clogin.com/hmctsdevextid.onmicrosoft.com/B2C_1A_signup_signin"
  
   val amsPlayer = "https://amp.azure.net/libs/amp/2.3.11/skins/amp-default/assets"

   val portalassets = "https://amp.azure.net/libs/amp/2.3.11"

   val portal = "https://pre-portal.staging.platform.hmcts.net"
  
   val portaluserFeeder = csv("src/gatling/scala/resources/portalusers.csv").circular

   val generateNonce = exec(session => {
    val nonce = java.util.UUID.randomUUID.toString
    session.set("nonce", nonce)
  })


  val scn = scenario("Pre-portal-login-playvideo")
    .feed(portaluserFeeder)
    .exec(generateNonce)
    .exec(http("b2c_homepage")
      .get("/hmctsdevextid.onmicrosoft.com/oauth2/v2.0/authorize?p=b2c_1a_signup_signin&client_id=add-client-id&scope=openid%20email%20profile&response_type=id_token&redirect_uri=https%3A%2F%2Fpre-portal.staging.platform.hmcts.net%2Fcallback&response_mode=form_post&nonce=${nonce}&state=eyJyZXR1cm5UbyI6Ii9icm93c2UifQ")
      .headers(headers_0)
      .check(bodyString.saveAs("b2c_homepageresponse"))
      .check(regex(""""csrf"\s*:\s*"([^"]+)"""").saveAs("csrfToken"))// Extract CSRF token
      .check(regex(""""transId"\s*:\s*"([^"]+)"""").saveAs("transId"))
      .transformResponse((response, session) => {
        val responseBody = response.body.string
        val filePath = "response.txt" 
        Files.write(Paths.get(filePath), responseBody.getBytes(StandardCharsets.UTF_8))
        response
      }),
      http("template_downlad")
        .get("https://presadev.blob.core.windows.net/pre-b2c-container/template.html")
        .check(status.is(200)),
      http("download Main.css")
        .get(b2cResources + "/css/main.css")
        .headers(headers_1)
        .check(status.is(304)),
      http("download b2c.js")
        .get(b2cResources + "/js/b2c.js")
        .headers(headers_1)
        .check(status.is(304)),
      http("download goukcrest")
        .get(b2cResources + "/assets/images/govuk-crest.png")
        .check(status.is(200)),
      http("download_manifest.json")
        .get(b2cResources + "/assets/manifest.json")
        .headers(headers_1)
        .check(status.is(200)),
       pause(7),
      http("b2c_siginPage")
        .post(b2cSignInPage + "/SelfAsserted?tx=${transId}&p=B2C_1A_signup_signin")
        .headers(headers_9)
        .formParam("request_type", "RESPONSE")
        .formParam("signInName", "${email}")
        .formParam("password", "${password}")
        .check(status.is(200)),
      http("b2c_confirmedPage")
        .post(b2cSignInPage +"/api/CombinedSigninAndSignup/confirmed?rememberMe=false&csrf_token=${csrfToken}&tx=${transId}&p=B2C_1A_signup_signin}")
        .headers(headers_9)
        .check(bodyString.saveAs("b2c_loginresponse"))
        .check(regex(""""csrf"\s*:\s*"([^"]+)"""").saveAs("afterlogincsrfToken"))// Extract CSRF token
        .check(regex(""""transId"\s*:\s*"([^"]+)"""").saveAs("afterlogntransId"))
        .transformResponse((response, session) => {
            val responseBody = response.body.string
            val filePath = "loginresponse.txt" 
            Files.write(Paths.get(filePath), responseBody.getBytes(StandardCharsets.UTF_8))
            response
          })
        .check(status.is(200)),
      http("Aftersignin_template")
          .get(b2cResources + "/template.html")
          .headers(headers_1),            
      http("Aftersignin_css")
          .get(b2cResources + "/css/main.css")
          .headers(headers_1),
      http("Aftersigin_assets_woff")
          get(b2cResources + "/assets/fonts/light-94a07e06a1-v2.woff2"),
      http("Aftersigin_assets_woff2")
          .get(b2cResources + "/assets/fonts/bold-b542beb274-v2.woff2"),
      http("Aftersigin_assets_png")
         .get(b2cResources + "/assets/images/govuk-crest.png"),
      http("Aftersigin_assets_b2c.js")
          .get(b2cResources + "/js/b2c.js")
          .headers(headers_1),
      http("Aftersigin_assets_manifest")
          .get(b2cResources + "/assets/manifest.json")
          .headers(headers_1),
       pause(5),
      http("b2c_confirmationPage")
        .post(b2cSignInPage + "/SelfAsserted?tx=${afterlogntransId}&p=B2C_1A_signup_signin")
        .headers(headers_9)
        .formParam("request_type", "RESPONSE")
        .formParam("signInName", "${email}")
        .check(status.is(200)),
      pause(2),
      http("portal_browse")
        .get(portal +"/browse")
        .headers(headers_31)
        .check(status.is(200))
        .resources(
          http("portal_ruxitagent")
            .get(portal +"/ruxitagentjs_ICA7NVfgqrux_10287240325103108.js")
            .headers(headers_1),
          http("portal_browse_css")
            .get(portal +"/main.27ec3762360528a88283.css")
            .headers(headers_23),
          http("portal_browse_js")
            .get(portal +"/main.79460ae172dd64d704d4.js")
            .headers(headers_24),
          http("portal_browse_woff2")
            .get(portal + "/assets/fonts/light-94a07e06a1-v2.woff2")
            .headers(headers_26),
          http("portal_browse_govukcrest")
            .get(portal +"/assets/images/govuk-crest.png")
            .headers(headers_25),
          http("portal_browse_woff2")
            .get(portal +"/assets/fonts/bold-b542beb274-v2.woff2")
            .headers(headers_26),
          http("portal_browse_favicon")
            .get(portal +"/assets/images/favicon.svg")
            .headers(headers_25),
          http("portal_browse_manifest")
            .get(portal +"/assets/manifest.json")
            .headers(headers_28)
      ),
      pause(5),
      http("portal_watch_page")
        .get(portal +"/watch/daad461d-505f-4361-84d0-064f843c844b") //change this recording id if you change test data
        .headers(headers_42)
        .check(status.is(200))
          .resources(
          http("player_page_css")
            .get(portal +"/main.27ec3762360528a88283.css")
            .headers(headers_26),
          http("portal_watch_assets_css")
            .get(portalassets +"/skins/amp-default/azuremediaplayer.min.css")
            .headers(headers_26),
          http("portal_watch_rainbow_icon_gif")
            .get(amsPlayer + "/icons/buffering-rainbow.gif")
            .headers(headers_45),
          http("portal_watch_js")
            .get(portalassets + "/azuremediaplayer.min.js")
            .headers(headers_26)
        ),
       pause(5),
        http("portal_watch_playback")
            .get(portal +"/watch/daad461d-505f-4361-84d0-064f843c844b/playback")
            .headers(headers_50)
            .check(bodyString.saveAs("portalwatchplaybackresponse"))
            .check(regex("""{"src"\s*:\s*"([^"]+)"""").saveAs("src"))
            .check(regex(""""authenticationToken"\s*:\s*"([^"]+)"""").saveAs("authenticationToken"))
            .check(status.is(200))
            .resources(
                http("portal_watch_playback_woff")
                  .get(portalassets + "/skins/amp-default/assets/fonts/azuremediaplayer.woff")
                  .headers(headers_51),
                http("portal_watch_playback_manifest")
                 .get(amsStreaming + "/manifest(format=mpd-time-csf,encryption=cbc)")
                 .check(regex(""""https://preamstest.keydelivery.uksouth.media.azure.net/?kid="\s*:\s*"([^"]+)"""").saveAs("kid"))
                 .check(status.is(200))
            )

    )
    .exec(session => {
      println("csrftoken.......:" + session("csrfToken").as[String])
      println("stateproperties.......:" + session("transId").as[String])
      println("portalwatchplaybackresponse.......:" + session("portalwatchplaybackresponse").as[String])
      println("key identifier.......:" + session("kid").as[String])
      session
    }
    )

	setUp(scn.inject(atOnceUsers(35))).protocols(httpProtocol)
}
