import com.grailsinaction.Profile
import com.grailsinaction.User
import grails.util.Environment

class BootStrap {
    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                createAdminUserIfRequired()
                break;
            case Environment.PRODUCTION:
                println "No special configuration required"
                break;
        }
    }
    def destroy = {
    }

    void createAdminUserIfRequired() {
        if (!User.findByUserId("admin")) {
            println "Fresh Database. Creating ADMIN user."
            def profile = new Profile(email: "admin@yourhost.com")
            def user = new User(userId: "admin",
                    password: "secret", profile: profile).save()
        } else {
            println "Existing admin user, skipping creation"
        }
    }
}