# Sample Gatling Report Structure

This document provides a sample of what a Gatling report looks like and how to interpret it.

## Report Structure

A typical Gatling report consists of the following sections:

### 1. Global Information

This section provides an overview of the simulation:

```
---- Global Information --------------------------------------------------------
> request count                                        150 (OK=150    KO=0     )
> min response time                                     10 ms (OK=10    KO=-     )
> max response time                                    523 ms (OK=523    KO=-     )
> mean response time                                   125 ms (OK=125    KO=-     )
> std deviation                                         95 ms (OK=95    KO=-     )
> response time 50th percentile                        102 ms (OK=102    KO=-     )
> response time 75th percentile                        156 ms (OK=156    KO=-     )
> response time 95th percentile                        312 ms (OK=312    KO=-     )
> response time 99th percentile                        487 ms (OK=487    KO=-     )
> mean requests/sec                                     15 (OK=15    KO=-     )
```

### 2. Statistics by Request

This section provides statistics for each request:

```
---- Statistics by Request ------------------------------------------------------
> standard_report_generation                                     
  >> min response time                                     45 ms (OK=45    KO=-     )
  >> max response time                                    523 ms (OK=523    KO=-     )
  >> mean response time                                   187 ms (OK=187    KO=-     )
  >> std deviation                                        112 ms (OK=112    KO=-     )
  >> response time 50th percentile                        156 ms (OK=156    KO=-     )
  >> response time 75th percentile                        234 ms (OK=234    KO=-     )
  >> response time 95th percentile                        412 ms (OK=412    KO=-     )
  >> response time 99th percentile                        498 ms (OK=498    KO=-     )
  >> mean requests/sec                                      5 (OK=5    KO=-     )

> platform_report_generation                                     
  >> min response time                                     12 ms (OK=12    KO=-     )
  >> max response time                                    312 ms (OK=312    KO=-     )
  >> mean response time                                   102 ms (OK=102    KO=-     )
  >> std deviation                                         78 ms (OK=78    KO=-     )
  >> response time 50th percentile                         87 ms (OK=87    KO=-     )
  >> response time 75th percentile                        134 ms (OK=134    KO=-     )
  >> response time 95th percentile                        245 ms (OK=245    KO=-     )
  >> response time 99th percentile                        298 ms (OK=298    KO=-     )
  >> mean requests/sec                                      5 (OK=5    KO=-     )

> virtual_report_generation                                     
  >> min response time                                     10 ms (OK=10    KO=-     )
  >> max response time                                    287 ms (OK=287    KO=-     )
  >> mean response time                                    87 ms (OK=87    KO=-     )
  >> std deviation                                         65 ms (OK=65    KO=-     )
  >> response time 50th percentile                         67 ms (OK=67    KO=-     )
  >> response time 75th percentile                        112 ms (OK=112    KO=-     )
  >> response time 95th percentile                        198 ms (OK=198    KO=-     )
  >> response time 99th percentile                        267 ms (OK=267    KO=-     )
  >> mean requests/sec                                      5 (OK=5    KO=-     )
```

### 3. Response Time Distribution

The report includes a graph showing the distribution of response times:

```
Response time distribution
t < 800 ms     150 (100%)
800 ms <= t < 1200 ms     0 (0%)
t >= 1200 ms     0 (0%)
failed     0 (0%)
```

### 4. Response Time Percentiles

The report includes a graph showing response time percentiles:

```
Response time percentiles (ms)
50%     102
75%     156
95%     312
99%     487
```

### 5. Active Users over Time

The report includes a graph showing the number of active users over time.

### 6. Response Time over Time

The report includes a graph showing response times over time for each request.

## Interpreting the Results

In this sample report, we can see:

1. **Standard Report Generation**: Has the highest response times (mean: 187ms, max: 523ms)
2. **Platform Report Generation**: Has better response times than standard (mean: 102ms, max: 312ms)
3. **Virtual Report Generation**: Has the best response times (mean: 87ms, max: 287ms)

This confirms our expectations:

- Synchronous execution (standard) is the slowest
- Asynchronous execution with platform threads is faster
- Asynchronous execution with virtual threads is the fastest

The virtual thread implementation shows a 53% improvement in mean response time compared to the standard implementation,
and a 15% improvement compared to the platform thread implementation.

## Conclusion

The Gatling report provides valuable insights into the performance characteristics of the ReportController endpoints. By
comparing the results of the three different report generation approaches, we can see that the virtual thread
implementation provides the best performance for this type of I/O-bound operation.

This is consistent with the expected benefits of virtual threads, which are designed to be more efficient for I/O-bound
operations by reducing the overhead of thread management.