package com.cellarhq.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoginSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:5050")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip,deflate,sdch")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .connection("keep-alive")
    .contentTypeHeader("application/x-www-form-urlencoded")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.122 Safari/537.36")

  val scn = scenario("LoginSimulation")
    .exec(http("homepage")
      .get("""/""")
      .headers(Headers.get)
    )
    .pause(3, 20)
    .exec(http("login")
      .get("/login")
      .headers(Headers.get)
    )
    .pause(3, 20)
    .exec(http("login.submit")
      .post("/pac4j-callback")
      .headers(Headers.post)
      .formParam("username", "rob@cellarhq.com")
      .formParam("password", "password1")
      .formParam("client_name", "FormClient")
    )

  setUp(
    scn.inject(
      rampUsers(25) over(30 seconds)
    )
  ).protocols(httpProtocol)
}
