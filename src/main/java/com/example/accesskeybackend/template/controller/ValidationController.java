package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping("/checkIpv6Support")
    public boolean ipv6Handler(@RequestParam String url) {

        return validationService.checkIpv6(url);
    }
}
