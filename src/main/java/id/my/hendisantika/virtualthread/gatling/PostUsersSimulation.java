package id.my.hendisantika.virtualthread.gatling;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
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
 * Date: 20/07/25
 * Time: 06.56
 * To change this template use File | Settings | File Templates.
 */
public class PostUsersSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080") // Base URL of the Spring Boot application
            .acceptHeader("application/json");

    ScenarioBuilder scn = scenario("Load test simulation for users endpoint with 1000 users at once and ramp up to 1000 users during 10 seconds with assertions")
            .exec(http("users_post")
                    .post("/users")
                    .header("Content-Type", "application/json")
                    .body(StringBody("""
                            {
                                "name": "John Doe",
                                "email": "johndoe@gmail.com",
                                "age": 30
                            }
                            """))
                    .check(status().is(201)));

    {
        setUp(
                scn.injectOpen(
                        atOnceUsers(10), // Simulate 10 users at once
                        rampUsers(10).during(10) // Ramp up to 10 users during 10 seconds
                )
        )
                .assertions(
                        global().responseTime().max().lt(1500), // Assert that the maximum response time is less than 1500ms
                        global().successfulRequests().percent().gt(95.0), // Assert that 95% of the requests are successful
                        forAll().failedRequests().count().lt(5L) // Assert that the number of failed requests is less than 5
                )
                .protocols(httpProtocol);
    }
}