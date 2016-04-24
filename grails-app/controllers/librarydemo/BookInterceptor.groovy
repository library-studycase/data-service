package librarydemo

import library.demo.User
import library.demo.UserContext
import org.springframework.web.client.RestTemplate


class BookInterceptor {

    UserContext userContext

    boolean before() {
        String token = request.getHeader("X-Auth-Token")
        User user = new RestTemplate().getForObject("http://localhost:8092/users?token={token}", User.class, token)

        userContext.setUser(user)

        true
    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
