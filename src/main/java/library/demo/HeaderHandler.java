package library.demo;

import library.demo.UserContext;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

    UserContext userContext;

    public HeaderHandler(UserContext userContext) {
        this.userContext = userContext;
    }

    public boolean handleMessage(SOAPMessageContext smc) {

        try {

/*            smc.getMessage().getMimeHeaders().addHeader("Native-Id", userContext.getUser().getNativeId().toString());

            SOAPMessage message = smc.getMessage();*/

            SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
            SOAPHeader header = envelope.getHeader();

            SOAPElement authHeader = header.addChildElement("Native-Id", "tns", "http://spbnb-prc789:8081/soap/library.wsdl");
            authHeader.addTextNode(userContext.getUser().getNativeId().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public Set getHeaders() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }

    public boolean handleFault(SOAPMessageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    public void close(MessageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }


}
