package id.my.hendisantika.virtualthread.gatling;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.forAll;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

/**
 * Created by IntelliJ IDEA.
 * Project : virtual-thread
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/07/22
 * Time: 08:00
 * To change this template use File | Settings | File Templates.
 */
public class ReportControllerSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8081") // Base URL of the Spring Boot application
            .acceptHeader("application/json");

    // Scenario for standard report generation
    ScenarioBuilder standardReportScenario = scenario("Standard Report Generation")
            .exec(http("standard_report_generation")
                    .post("/reports/US")
                    .check(status().is(200)));

    // Scenario for platform report generation
    ScenarioBuilder platformReportScenario = scenario("Platform Report Generation")
            .exec(http("platform_report_generation")
                    .post("/reports/platform/US")
                    .check(status().is(200)));

    // Scenario for virtual report generation
    ScenarioBuilder virtualReportScenario = scenario("Virtual Report Generation")
            .exec(http("virtual_report_generation")
                    .post("/reports/virtual/US")
                    .check(status().is(200)));

    {
        setUp(
                // Test standard report endpoint with 10 users at once and ramp up to 50 users over 30 seconds
                standardReportScenario.injectOpen(
                        rampUsers(50).during(30)
                ),
                // Test platform report endpoint with 10 users at once and ramp up to 50 users over 30 seconds
                platformReportScenario.injectOpen(
                        rampUsers(50).during(30)
                ),
                // Test virtual report endpoint with 10 users at once and ramp up to 50 users over 30 seconds
                virtualReportScenario.injectOpen(
                        rampUsers(50).during(30)
                )
        )
                .assertions(
                        global().responseTime().max().lt(3000), // Assert that the maximum response time is less than 3000ms
                        global().successfulRequests().percent().gt(95.0), // Assert that 95% of the requests are successful
                        forAll().failedRequests().count().lt(10L) // Assert that the number of failed requests is less than 10
                )
                .protocols(httpProtocol);
    }
}