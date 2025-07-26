package com.edunova.commons.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {
        "com.edunova.user.service",
        "com.edunova.commons.web"
})
@Component
public class CommonWebConfiguration {
}
