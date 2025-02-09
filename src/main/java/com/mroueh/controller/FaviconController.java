package com.mroueh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {
    @GetMapping("favicon.ico")
    public void favicon() {
        // This ensures that the request is ignored or logged silently
    }
}
