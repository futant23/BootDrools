/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.services;

import java.util.ArrayList;
import java.util.List;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rtsw.toolsteam.bootdrools.domain.Limit;
import rtsw.toolsteam.bootdrools.domain.NoLimitsFound;
import rtsw.toolsteam.bootdrools.domain.Telemetry;

/**
 *
 * @author 
 */
@Service
public class TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryService.class);

    private final KieContainer kieContainer;

    private final String ID = "TelemetrySession";

    
    @Autowired
    public TelemetryService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        log.info("initializing a new telemetry session ...");
    }

    public synchronized Limit getLimits(Telemetry telemetry) {
        log.info("getting limits for: " + telemetry);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(telemetry);
        kieSession.fireAllRules();
        Limit limit = getLimit(kieSession);
        kieSession.dispose();
        return limit;
    }

    private Limit getLimit(KieSession kieSession) {
        ObjectFilter telemetryFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (object instanceof Limit) {
                    return true;
                }
                return false;
            }
        };
        
        List<Limit> facts =new ArrayList<>();
        for(FactHandle handle : kieSession.getFactHandles(telemetryFilter)) {
            Limit limit =(Limit)kieSession.getObject(handle);
            facts.add(limit);
        }
        if(facts.isEmpty()) {
            log.warn("no facts found matching TelemetryFilter ...");
            return new NoLimitsFound();
        }
        
        return facts.get(0);
    }

}
