/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rtsw.toolsteam.bootdrools.domain.Limit;
import rtsw.toolsteam.bootdrools.domain.Telemetry;

/**
 *
 * @author b1050502
 */
@RestController
public class TelemetryController {

    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);

    private TelemetryService telemetryService;

    @Autowired
    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @RequestMapping(value = "/telemetry", method = RequestMethod.GET)
    public Limit getLimit(@RequestParam(required = true) String sv,
            @RequestParam(required = true) String mnemonic,
            @RequestParam(required = true) String value) {
        Telemetry telemetry = new Telemetry(sv, mnemonic, value);

        Limit limit = telemetryService.getLimits(telemetry);
        return limit;
    }

    @RequestMapping(value = "/telemetry", method = RequestMethod.POST)
    public Limit getLimit(@RequestBody Telemetry telemetry) {
        log.info("received post request: " + telemetry.toString());
        Limit limit = telemetryService.getLimits(telemetry);
        return limit;
    }

}
