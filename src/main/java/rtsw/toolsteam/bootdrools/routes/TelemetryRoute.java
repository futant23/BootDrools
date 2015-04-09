/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.routes;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rtsw.toolsteam.bootdrools.services.TelemetryService;

/**
 *
 * @author b1050502
 */
@Component
public class TelemetryRoute extends RouteBuilder{

    private static final Logger log =LoggerFactory.getLogger(TelemetryRoute.class);
    
    private final TelemetryService telemetryService;

    @Autowired
    public TelemetryRoute(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }
    
    
    @Override public void configure() throws Exception {
        log.info("configure()");
        from("{{route.telemetry.from}}")
                .setExchangePattern(ExchangePattern.InOut)
                .bean(telemetryService, "getLimits");
    }
    
}
