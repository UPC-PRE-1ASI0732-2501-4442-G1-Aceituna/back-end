import static org.junit.Assert.assertEquals;
import org.junit.Test;

import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;

public class profileTest {
    @Test
    public void testProfileUpdateEmail() {
        // Arrange
        User user = new User("juanito1234", "password123", "juan_asdasd@hotmail.com");
        Profile profile = new Profile(user, "Juan", "Pérez", "juan_asdasd@hotmail.com", "987654321", "12345678901");

        // Act
        profile.updateEmail("juan_perez1998@hotmail.com");

        // Assert
        assertEquals("El correo electronico se actualizó correctamente", "juan_perez1998@hotmail.com",
                profile.getEmail());

    }

    @Test
    public void testProfileUpdateFullName() {
        // Arrange
        User user = new User("juanito1234", "password123", "juan_asdasd@hotmail.com");
        Profile profile = new Profile(user, "Juan", "Pérez", "juan_asdasd@hotmail.com", "987654321", "12345678901");

        // Act
        profile.updateName("Oscar", "Gonzales");

        // Assert
        assertEquals("El nombre se actualizó correctamente", "Oscar Gonzales", profile.getFullName());

    }
}
