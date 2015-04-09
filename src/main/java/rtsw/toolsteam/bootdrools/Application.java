/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;
import org.apache.camel.CamelContext;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rtsw.toolsteam.bootdrools.domain.Telemetry;
import rtsw.toolsteam.bootdrools.domain.TelemetryTransformer;

/**
 *
 * @author b1050502
 */
@SpringBootApplication
public class Application {
    private static final Logger log =LoggerFactory.getLogger(Application.class);
    
    @Value("${rule}")
    String rule;
    
    @Autowired
    CamelContext camelContext;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
//        Telemetry t =new Telemetry("sv003", "a", "b");
//        System.out.println(new TelemetryTransformer().toXml(t));
    }
  
    @Bean
    public KieContainer getKieContainer() throws FileNotFoundException{
        //return KieServices.Factory.get().getKieClasspathContainer();
        log.info("loading rule from: " +rule);
        KieServices services =KieServices.Factory.get();
        KieFileSystem fileSystem =services.newKieFileSystem();
        
        FileInputStream fis =new FileInputStream(rule);
        fileSystem.write("src/main/resources/rtsw/toolsteam/rules/Telemetry.drl", services.getResources().newInputStreamResource(fis));
        
        KieBuilder kieBuilder =services.newKieBuilder(fileSystem).buildAll();
        Results results =kieBuilder.getResults();
        if(results.hasMessages(Message.Level.ERROR)) {
            log.error(results.getMessages().toString());
            throw new IllegalStateException("Errors detected while building: "+rule);
        }
        else {
            log.info("no errors detected from: "+rule);
        }
        
        KieContainer kieContainer =services.newKieContainer(services.getRepository().getDefaultReleaseId());
        return kieContainer;
    }
}
