/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b1050502
 */
@XmlRootElement
public class OutOfLimits extends Limit{

    public OutOfLimits() { }
    
    
   public OutOfLimits(Telemetry tlm) {
        super(tlm);
    }
}
