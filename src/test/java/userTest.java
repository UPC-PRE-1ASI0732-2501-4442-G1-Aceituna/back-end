
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;

public class userTest {
    @Test
    public void testUserUpdateUsername() {
        // Arrange
        User user = new User("juanito1234", "password123", "juan_asdasd@hotmail.com");

        // Act
        user.setUsername("juan1234");

        // Assert
        assertEquals("El nombre de usuario se actualizó correctamente", "juan1234", user.getUsername());
    }

}
