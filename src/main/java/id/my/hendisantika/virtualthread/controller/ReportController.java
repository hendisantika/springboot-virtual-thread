package id.my.hendisantika.virtualthread.controller;

import id.my.hendisantika.virtualthread.service.PlatformReportService;
import id.my.hendisantika.virtualthread.service.ReportService;
import id.my.hendisantika.virtualthread.service.VirtualReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : virtual-thread
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 20/07/25
 * Time: 05.36
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final PlatformReportService platformReportService;

    private final VirtualReportService virtualReportService;
}
