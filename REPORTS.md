# Performance Testing with Apache Benchmark

## Testing Reports Endpoint

```shell
ab -n 1000 -c 100 -m POST http://localhost:8080/reports/US
```

Results:

```shell
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            8080

Document Path:          /reports/US
Document Length:        33 bytes

Concurrency Level:      100
Time taken for tests:   0.300 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      166000 bytes
HTML transferred:       33000 bytes
Requests per second:    3336.55 [#/sec] (mean)
Time per request:       29.971 [ms] (mean)
Time per request:       0.300 [ms] (mean, across all concurrent requests)
Transfer rate:          540.89 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.6      0       3
Processing:     1   19  15.6     16     103
Waiting:        1   18  15.4     16     103
Total:          1   19  15.7     17     103

Percentage of the requests served within a certain time (ms)
  50%     17
  66%     26
  75%     30
  80%     33
  90%     42
  95%     46
  98%     53
  99%     59
 100%    103 (longest request)

```

## Performance Testing with Apache Benchmark

## Testing Platform Threading Endpoint

```shell
ab -n 1000 -c 100 -m POST http://localhost:8080/reports/platform/US
```

Results:

```shell
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            8080

Document Path:          /reports/platform/US
Document Length:        42 bytes

Concurrency Level:      100
Time taken for tests:   0.058 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      175000 bytes
HTML transferred:       42000 bytes
Requests per second:    17101.91 [#/sec] (mean)
Time per request:       5.847 [ms] (mean)
Time per request:       0.058 [ms] (mean, across all concurrent requests)
Transfer rate:          2922.69 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    3   1.5      2       6
Processing:     1    3   1.1      2       8
Waiting:        1    2   1.1      2       8
Total:          3    5   2.4      4      11

Percentage of the requests served within a certain time (ms)
  50%      4
  66%      6
  75%      8
  80%      9
  90%      9
  95%      9
  98%      9
  99%      9
 100%     11 (longest request)
```

## Performance Testing with Apache Benchmark

## Testing Virtual Threading Endpoint

```shell
ab -n 1000 -c 100 -m POST http://localhost:8080/reports/virtual/US
```

Results:

```shell
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            8080

Document Path:          /reports/virtual/US
Document Length:        41 bytes

Concurrency Level:      100
Time taken for tests:   0.057 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      174000 bytes
HTML transferred:       41000 bytes
Requests per second:    17473.05 [#/sec] (mean)
Time per request:       5.723 [ms] (mean)
Time per request:       0.057 [ms] (mean, across all concurrent requests)
Transfer rate:          2969.05 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    3   1.4      2       6
Processing:     1    3   0.9      2       5
Waiting:        1    3   0.9      2       5
Total:          2    5   2.2      4      10
WARNING: The median and mean for the processing time are not within a normal deviation
        These results are probably not that reliable.
WARNING: The median and mean for the waiting time are not within a normal deviation
        These results are probably not that reliable.

Percentage of the requests served within a certain time (ms)
  50%      4
  66%      7
  75%      8
  80%      8
  90%      9
  95%     10
  98%     10
  99%     10
 100%     10 (longest request)
```