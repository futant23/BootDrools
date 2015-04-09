/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.domain;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author b1050502
 */
@XmlTransient
@XmlSeeAlso({InLimits.class, OutOfLimits.class, NoLimitsFound.class})
public abstract class Limit {
    protected Telemetry telemetry;

    public Limit() {}

    public Limit(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    
    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    
    public String getLimitType() {
        return this.getClass().getSimpleName();
    }

    
}
