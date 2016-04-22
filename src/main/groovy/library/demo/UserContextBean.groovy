package library.demo

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
class UserContextBean implements UserContext {

    private User user

    @Override
    User getUser() {
        return this.user
    }

    @Override
    void setUser(User user) {
        this.user = user
    }

}
