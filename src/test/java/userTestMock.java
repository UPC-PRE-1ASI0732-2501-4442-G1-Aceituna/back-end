import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;
import upc.edu.ecomovil.api.iam.domain.model.entities.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class userTestMock {
    private Role mockRole1;
    private Role mockRole2;

    @Before
    public void setUp() {
        mockRole1 = mock(Role.class);
        mockRole2 = mock(Role.class);
    }

    @Test
    public void testAddRoles() {
        User user = new User("usuario", "pass", "correo@mail.com");
        List<Role> roles = Arrays.asList(mockRole1, mockRole2);

        // Simula la validación de roles
        Mockito.mockStatic(Role.class).when(() -> Role.validateRoleSet(roles)).thenReturn(roles);

        user.addRoles(roles);
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(mockRole1));
        assertTrue(user.getRoles().contains(mockRole2));
    }

}
