package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(locations="classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testUserService() {

        assertNotNull(userService);

        assertNotNull(userService.getUserRepository());

        assertEquals("MySQL", userService.getUserRepository().getDbType());

        assertNotNull(userService.getSecurityService());

        boolean result = userService.registerUser("user1", "혜인", "1234");

        assertTrue(result);
    }
}