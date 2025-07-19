package id.my.hendisantika.virtualthread.controller;

import id.my.hendisantika.virtualthread.service.PlatformReportService;
import id.my.hendisantika.virtualthread.service.ReportService;
import id.my.hendisantika.virtualthread.service.VirtualReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Tag(name = "Reports", description = "API endpoints for generating different types of reports")
public class ReportController {

    private final ReportService reportService;

    private final PlatformReportService platformReportService;

    private final VirtualReportService virtualReportService;

    @Operation(
            summary = "Generate a standard report for a region",
            description = "Initiates the generation of a standard report for the specified region",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Report generation started successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid region provided"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/{region}")
    public ResponseEntity<String> generateReport(
            @Parameter(description = "Region code for which to generate the report", required = true)
            @PathVariable String region) {
        reportService.generateReportForRegion(region);
        return ResponseEntity.ok("✅ report started for region: " + region);
    }

    @Operation(
            summary = "Generate a platform report for a region",
            description = "Initiates the generation of a platform-specific report for the specified region",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Platform report generation started successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid region provided"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/platform/{region}")
    public ResponseEntity<String> generateReportPlatform(
            @Parameter(description = "Region code for which to generate the platform report", required = true)
            @PathVariable String region) {
        platformReportService.generateReportForRegion(region);
        return ResponseEntity.ok("✅ Platform report started for region: " + region);
    }

    @Operation(
            summary = "Generate a virtual report for a region",
            description = "Initiates the generation of a virtual thread-based report for the specified region",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Virtual report generation started successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid region provided"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/virtual/{region}")
    public ResponseEntity<String> generateReportVirtual(
            @Parameter(description = "Region code for which to generate the virtual report", required = true)
            @PathVariable String region) {
        virtualReportService.generateReportForRegion(region);
        return ResponseEntity.ok("✅ Virtual report started for region: " + region);
    }
}
