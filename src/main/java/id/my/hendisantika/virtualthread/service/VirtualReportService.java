package id.my.hendisantika.virtualthread.service;

import id.my.hendisantika.virtualthread.entity.Customer;
import id.my.hendisantika.virtualthread.repository.CustomerRepository;
import id.my.hendisantika.virtualthread.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by IntelliJ IDEA.
 * Project : virtual-thread
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 20/07/25
 * Time: 05.33
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VirtualReportService {

    private final CustomerRepository repository;

    private final Executor virtualThreadExecutor;

    public void generateReportForRegion(String region) {

        virtualThreadExecutor.execute(() -> {
            log.info("Virtual generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);//1
            try {
                CsvReportUtil.writeCustomersToCsv("virtual_" + region, customers);//2
            } catch (Exception e) {
                System.out.println("❌ Virtual Error writing report for region: " + region);
            }
        });

    }

}
