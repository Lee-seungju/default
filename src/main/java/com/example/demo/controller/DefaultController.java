package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DefaultController {

    private final Environment env;

    @GetMapping("test")
    public String defaultTest() {
        return "Hello World";
    }

    @GetMapping("/profile")
    public String getProfile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> testProfiles = Arrays.asList("test1", "test2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(testProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
