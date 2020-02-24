package com.ma.vue.boot.common;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康检查 可以通过 actuator 的health来查看
 */
@Component
public class CaseIndicator implements HealthIndicator {

    @Override
    public Health health() {

        return Health.up().withDetail("caseName","mayantao").build();
    }
}
