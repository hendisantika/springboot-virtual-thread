# Spring Boot Virtual Thread Demo

This project demonstrates the use of Java's Virtual Threads (Project Loom) in a Spring Boot application, comparing it
with traditional threading models.

## Overview

This application provides a simple API for generating customer reports using three different threading models:

1. **Simple Threading**: Uses the default Tomcat thread pool (synchronous execution)
2. **Platform Threading**: Uses a fixed thread pool with traditional platform threads (asynchronous execution)
3. **Virtual Threading**: Uses Java's Virtual Threads (Project Loom) for lightweight concurrency (asynchronous
   execution)

The application allows you to compare the performance and resource usage of these different threading models under
various load conditions.

## Prerequisites

- Java 21+ (for Virtual Thread support)
- Gradle
- Docker (optional, for running with Docker Compose)

## Getting Started

### Clone the repository

```bash
git clone https://github.com/hendisantika/springboot-virtual-thread.git
cd springboot-virtual-thread
```

### Build the application

```bash
./gradlew build
```

### Run the application

```bash
./gradlew bootRun
```

Or using Docker Compose:

```bash
docker-compose up
```

## API Endpoints

The application provides three endpoints for generating reports:

1. **Simple Threading (Synchronous)**:
   ```
   POST /reports/{region}
   ```

2. **Platform Threading (Asynchronous with fixed thread pool)**:
   ```
   POST /reports/platform/{region}
   ```

3. **Virtual Threading (Asynchronous with virtual threads)**:
   ```
   POST /reports/virtual/{region}
   ```

Where `{region}` is a parameter specifying the region for which to generate the report (e.g., "US", "EU", etc.).

## Performance Testing

### Apache Benchmark (ab) Testing

You can use Apache Benchmark to test the performance of the different endpoints:

#### Testing Simple Threading

```bash
ab -n 1000 -c 100 -m POST http://localhost:8080/reports/US
```

#### Testing Platform Threading

```bash
ab -n 1000 -c 100 -m POST http://localhost:8080/reports/platform/US
```

#### Testing Virtual Threading

```bash
ab -n 1000 -c 100 -m POST http://localhost:8080/reports/virtual/US
```

Parameters:

- `-n 1000`: Send 1000 requests
- `-c 100`: Concurrency level of 100 requests at a time
- `-m POST`: Use POST method

### k6 Testing

[k6](https://k6.io/) is a modern load testing tool. Create a file named `load-test.js` with the following content:

```javascript
import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
   vus: 100,  // Number of virtual users
   duration: '30s',  // Test duration
};

export default function () {
   // Test simple threading
   http.post('http://localhost:8080/reports/US');

   // Test platform threading
   http.post('http://localhost:8080/reports/platform/US');

   // Test virtual threading
   http.post('http://localhost:8080/reports/virtual/US');

   sleep(1);
}
```

Run the test:

```bash
k6 run load-test.js
```

For more targeted testing, you can create separate scripts for each endpoint:

**simple-test.js**:

```javascript
import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
   vus: 100,
   duration: '30s',
};

export default function () {
   http.post('http://localhost:8080/reports/US');
   sleep(1);
}
```

**platform-test.js**:

```javascript
import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
   vus: 100,
   duration: '30s',
};

export default function () {
   http.post('http://localhost:8080/reports/platform/US');
   sleep(1);
}
```

**virtual-test.js**:

```javascript
import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
   vus: 100,
   duration: '30s',
};

export default function () {
   http.post('http://localhost:8080/reports/virtual/US');
   sleep(1);
}
```

Run individual tests:

```bash
k6 run simple-test.js
k6 run platform-test.js
k6 run virtual-test.js
```

## Understanding the Threading Models

### Simple Threading

The simple threading model uses the default Tomcat thread pool (typically 200 threads) to handle requests synchronously.
Each request is processed in the current thread, and if all threads are busy, new requests are queued (up to 100
requests by default).

### Platform Threading

The platform threading model uses a fixed thread pool (5 threads in this implementation) to handle requests
asynchronously. This allows the Tomcat threads to be released quickly, but the actual work is still done by platform
threads, which are relatively heavyweight.

### Virtual Threading

The virtual threading model uses Java's Virtual Threads (Project Loom) to handle requests asynchronously. Virtual
threads are lightweight and managed by the JVM, allowing for much higher concurrency without the overhead of platform
threads.

## Expected Results

When testing under high load:

1. **Simple Threading**: May become unresponsive as Tomcat threads are exhausted
2. **Platform Threading**: Better than simple threading but still limited by the number of platform threads
3. **Virtual Threading**: Should handle much higher concurrency with lower resource usage

## License

This project is licensed under the MIT License - see the LICENSE file for details.