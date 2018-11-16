package com.cdis.microservice.example.auth.service;

import com.cdis.microservice.example.auth.event.EventDispatcher;
import com.cdis.microservice.example.auth.model.Role;
import com.cdis.microservice.example.auth.model.User;
import com.cdis.microservice.example.auth.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EventDispatcher eventDispatcher;

    @Before
    public void setUp(){
        Role testRole = new Role("TEST-ROLE");
        User testUser= new User("testUsername", "testPassword", "testFirstName", "testLastName", testRole);
        testUser.setId(1L);
        Mockito.when(userRepository.findUserByUsername(testUser.getUsername())).thenReturn(testUser);
        Mockito.when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
    }

    @Test
    public void whenValidUsername_thenUserShouldBeFound(){
        // Given
        String username = "testUsername";

        // When
        User userFound = userService.findUserByUsername(username);

        // Then
        Assert.assertEquals(userFound.getUsername(), username);
    }

    @Test
    public void whenValidCredentials_thenSendUserToBasket(){
        // Given
        User userToBeSent = userService.findUserById(1L);
        Assert.assertNotNull(userToBeSent);

        // When
        try {
            eventDispatcher.send(userToBeSent);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
