# Gatling Performance Testing for ReportController

This document provides instructions on how to run Gatling performance tests for the ReportController and analyze the
results.

## Overview

The Gatling simulation tests the three endpoints of the ReportController:

1. Standard Report Generation: `POST /reports/US`
2. Platform Report Generation: `POST /reports/platform/US`
3. Virtual Report Generation: `POST /reports/virtual/US`

The simulation is designed to compare the performance of these three different report generation approaches:

- Synchronous execution (standard)
- Asynchronous execution with platform threads
- Asynchronous execution with virtual threads

## Prerequisites

- Java 21 or higher
- Gradle 8.x or higher
- The Spring Boot application must be running

## Running the Tests

### Step 1: Start the Spring Boot Application

Start the Spring Boot application on port 8081:

```bash
./gradlew bootRun --args='--server.port=8081'
```

### Step 2: Run the Gatling Simulation

Run the Gatling simulation:

```bash
./gradlew gatlingRun
```

This will run all Gatling simulations in the project, including the ReportControllerSimulation.

If you want to run only the ReportControllerSimulation, you can use:

```bash
./gradlew gatlingRun-id.my.hendisantika.virtualthread.gatling.ReportControllerSimulation
```

### Step 3: View the Gatling Report

After the simulation completes, Gatling will generate an HTML report in the `build/reports/gatling` directory. Open the
HTML report in a web browser to view the results.

## Understanding the Gatling Report

The Gatling report provides detailed information about the performance of each endpoint:

### Global Information

- Number of requests
- Minimum, maximum, mean, and standard deviation of response times
- Number of successful and failed requests
- Throughput (requests per second)

### Response Time Distribution

- Response time distribution graph
- Percentiles (50th, 75th, 95th, 99th)

### Active Users over Time

- Number of active users over time

### Response Time over Time

- Response time over time for each request

## Expected Results

The simulation is designed to compare the performance of the three different report generation approaches. We expect to
see:

1. **Standard Report Generation**: Slowest response time, as it executes synchronously on the calling thread.
2. **Platform Report Generation**: Better response time than standard, as it executes asynchronously using a fixed
   thread pool.
3. **Virtual Report Generation**: Best response time, as it executes asynchronously using virtual threads, which are
   more efficient for I/O-bound operations.

## Customizing the Simulation

You can customize the simulation by modifying the `ReportControllerSimulation.java` file:

- Change the number of users by modifying the `rampUsers` parameter
- Change the ramp-up time by modifying the `during` parameter
- Add more assertions by adding more `assertions` calls
- Test different regions by changing the region parameter in the URL

## Troubleshooting

If you encounter issues running the simulation:

1. Make sure the Spring Boot application is running on port 8081
2. Check the logs for any errors
3. Increase the timeout for the Gatling simulation by adding `--timeout=600s` to the Gradle command
4. Reduce the number of users in the simulation to reduce the load

## Conclusion

Gatling performance testing provides valuable insights into the performance characteristics of the ReportController
endpoints. By comparing the results of the three different report generation approaches, you can make informed decisions
about which approach to use in your application.