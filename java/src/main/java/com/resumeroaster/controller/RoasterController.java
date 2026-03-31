package com.resumeroaster.controller;

import com.resumeroaster.model.RoastResponse;
import com.resumeroaster.service.ResumeParserService;
import com.resumeroaster.service.RoasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RoasterController {

    private static final Logger log = LoggerFactory.getLogger(RoasterController.class);

    private final ResumeParserService resumeParserService;
    private final RoasterService roasterService;

    public RoasterController(ResumeParserService resumeParserService, RoasterService roasterService) {
        this.resumeParserService = resumeParserService;
        this.roasterService = roasterService;
    }

    @PostMapping(value = "/roast", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoastResponse> roastResume(@RequestParam("file") MultipartFile file) {
        log.info("Received resume roast request: filename='{}', size={} bytes",
                file.getOriginalFilename(), file.getSize());

        String resumeText = resumeParserService.extractText(file);
        RoastResponse roast = roasterService.roast(resumeText);

        return ResponseEntity.ok(roast);
    }
}