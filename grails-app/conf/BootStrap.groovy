import com.grailsinaction.Post
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

            def profile = new Profile(
                    fullName: "admin",
                    bio: "bio",
                    email: "admin@yourhost.com")
            def user = new User(
                    userId: "admin",
                    password: "secret",
                    profile: profile).save()
            def post1 = new Post(content: "First post... W00t!")
            user.addToPosts(post1)
        } else {
            println "Existing admin user, skipping creation"
        }
    }
}