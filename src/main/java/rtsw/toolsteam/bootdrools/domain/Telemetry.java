/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 
 */
@XmlRootElement(name ="telemetry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Telemetry {
    
    @XmlAttribute
    private String sv;
    
    @XmlAttribute
    private String mnemonic;
    
    @XmlAttribute
    private String value;

    public Telemetry() {}

    public Telemetry(String sv, String mnemonic, String value) {
        this();
        this.sv = sv;
        this.mnemonic = mnemonic;
        this.value = value;
    }
    
    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Telemetry{" + "sv=" + sv + ", mnemonic=" + mnemonic + ", value=" + value + '}';
    }
    
}
