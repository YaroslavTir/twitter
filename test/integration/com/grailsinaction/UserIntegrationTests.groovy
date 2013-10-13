package com.grailsinaction

import static org.junit.Assert.*
import org.junit.*

class UserIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testFirstSaveEver() {
        def user = new User(userId: 'joe', password: 'secret', homepage: 'http://www.grailsinaction.com')
        assertNotNull user.save()
        assertNotNull user.id
        def foundUser = User.get(user.id)
        assertEquals 'joe', foundUser.userId
    }


    @Test
    void testSaveAndUpdate() {
        def user = new User(userId: 'joe', password: 'secret', homepage: 'http://www.grailsinaction.com')
        assertNotNull user.save()
        def foundUser = User.get(user.id)
        foundUser.password = 'sesame'
        foundUser.save()
        def editedUser = User.get(user.id)
        assertEquals 'sesame', editedUser.password
    }

    @Test
    void testSaveThenDelete() {
        def user = new User(userId: 'joe', password: 'secret', homepage: 'http://www.grailsinaction.com')
        assertNotNull user.save()
        def foundUser = User.get(user.id)
        foundUser.delete()
        assertFalse User.exists(foundUser.id)
    }

    @Test
    void testPostWithTags() {
        def user = new User(userId: 'joe', password: 'secret').save()
        def tagGroovy = new Tag(name: 'groovy')
        def tagGrails = new Tag(name: 'grails')
        user.addToTags(tagGroovy)
        user.addToTags(tagGrails)
        def tagNames = user.tags*.name
        assertEquals([ 'grails', 'groovy'] , tagNames.sort())
        def groovyPost = new Post(content: "A groovy post")
        user.addToPosts(groovyPost)
        groovyPost.addToTags(tagGroovy)
        assertEquals 1, groovyPost.tags.size()
        def bothPost = new Post(content: "A groovy and grails post")
        user.addToPosts(bothPost)
        bothPost.addToTags(tagGroovy)
        bothPost.addToTags(tagGrails)
        assertEquals 2, bothPost.tags.size()
    }

    @Test
    void testFollowing() {
        def glen = new User(userId: 'glen', password:'password').save()
        def peter = new User(userId: 'peter', password:'password').save()
        def sven = new User(userId: 'sven', password:'password').save()
        glen.addToFollowing(peter)
        glen.addToFollowing(sven)
        assertEquals 2, glen.following.size()
        sven.addToFollowing(peter)
        assertEquals 1, sven.following.size()
    }
}
