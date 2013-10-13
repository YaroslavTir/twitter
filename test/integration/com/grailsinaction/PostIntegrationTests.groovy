package com.grailsinaction

import static org.junit.Assert.*
import org.junit.*

class PostIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testFirstPost() {
        def user = new User(userId: 'joe', password: 'secret').save()
        def post1 = new Post(content: "First post... W00t!")
        user.addToPosts(post1)
        def post2 = new Post(content: "Second post...")
        user.addToPosts(post2)
        def post3 = new Post(content: "Third post...")

        user.addToPosts(post3)
        assertEquals 3, User.get(user.id).posts.size()
    }
}
