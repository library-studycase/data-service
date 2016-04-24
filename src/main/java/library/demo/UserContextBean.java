package library.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class UserContextBean implements UserContext {

    private User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
    this.user = user;
    }
}
