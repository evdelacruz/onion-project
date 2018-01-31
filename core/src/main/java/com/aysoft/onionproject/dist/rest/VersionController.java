package com.aysoft.onionproject.dist.rest;

import com.aysoft.onionproject.infrastructure.seedwork.context.SystemContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

    @GetMapping
    public String version() {
        return SystemContext.version();
    }
}
