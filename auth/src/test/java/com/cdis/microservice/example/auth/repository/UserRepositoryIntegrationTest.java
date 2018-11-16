package com.cdis.microservice.example.auth.repository;

import com.cdis.microservice.example.auth.model.Role;
import com.cdis.microservice.example.auth.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindUserByName_ReturnUsername(){
        //  Given
        Role testRole = new Role("TEST-ROLE");
        entityManager.persist(testRole);
        entityManager.flush();

        User testUser= new User("testUsername", "testPassword", "testFirstName", "testLastName", testRole);
        entityManager.persist(testUser);
        entityManager.flush();

        // When
        User foundUserbyUsername = userRepository.findUserByUsername("testUsername");

        // Then
        Assert.assertNotNull(foundUserbyUsername);
        Assert.assertEquals(foundUserbyUsername, testUser);
    }
}
