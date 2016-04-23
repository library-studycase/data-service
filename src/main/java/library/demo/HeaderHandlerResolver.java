package library.demo;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

public class HeaderHandlerResolver implements HandlerResolver {

    private UserContext userContext;

    public HeaderHandlerResolver(UserContext userContext) {
        this.userContext = userContext;
    }

    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlerChain = new ArrayList<Handler>();

        HeaderHandler hh = new HeaderHandler(userContext);

        handlerChain.add(hh);

        return handlerChain;
    }
}
