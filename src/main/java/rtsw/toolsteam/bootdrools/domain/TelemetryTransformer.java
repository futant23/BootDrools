/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootdrools.domain;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author b1050502
 */
public class TelemetryTransformer {

    public synchronized String toXml(final Telemetry data) throws JAXBException {
        final StringWriter stringWriter = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(Telemetry.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        if (data == null) {
            Telemetry bbean = new Telemetry();
            marshaller.marshal(bbean, stringWriter);
        } else {
            marshaller.marshal(data, stringWriter);
        }

        return stringWriter.toString();
    }

    public synchronized Telemetry fromXml(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Telemetry.class);
        StringReader reader = new StringReader(xml);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Telemetry bean = (Telemetry) jaxbUnmarshaller.unmarshal(reader);
        return bean;
    }

}
