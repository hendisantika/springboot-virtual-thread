package id.my.hendisantika.virtualthread.service;

import id.my.hendisantika.virtualthread.entity.Customer;
import id.my.hendisantika.virtualthread.repository.CustomerRepository;
import id.my.hendisantika.virtualthread.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : virtual-thread
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 20/07/25
 * Time: 05.32
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final CustomerRepository customerRepository;

    //300
    //tomcat default thread 200
    //200 request processing
    //100 request waiting in queue
    public void generateReportForRegion(String region) {
        log.info("generating report for region: {} | {}", region, Thread.currentThread());

        List<Customer> customers = customerRepository.findByRegion(region);//1
        try {
            CsvReportUtil.writeCustomersToCsv("simple_" + region, customers);//2
        } catch (Exception e) {
            System.out.println("❌ Error writing report for region: " + region);
        }

    }
}
