package id.my.hendisantika.virtualthread.service;

import id.my.hendisantika.virtualthread.entity.Customer;
import id.my.hendisantika.virtualthread.repository.CustomerRepository;
import id.my.hendisantika.virtualthread.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * Project : virtual-thread
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 20/07/25
 * Time: 05.30
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformReportService {

    private final CustomerRepository repository;


    private final Executor executor = Executors.newFixedThreadPool(5);

    public void generateReportForRegion(String region) {
        executor.execute(() -> {
            log.info("Platform generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);//1
            try {
                CsvReportUtil.writeCustomersToCsv("platform_" + region, customers);//2
            } catch (Exception e) {
                System.out.println("❌ Platform Error writing report for region: " + region);
            }
        });

    }
}
