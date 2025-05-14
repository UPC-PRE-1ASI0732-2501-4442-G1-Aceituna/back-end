import org.junit.Assert;
import org.junit.Test;

import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;
import upc.edu.ecomovil.api.reservations.domain.model.aggregates.Reservation;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;

public class reservationTest {

    @Test
    public void testReservationUpdateStatus() {
        // Arrange
        Vehicle vehicle = new Vehicle(
                "Sedan", // type
                "Toyota Corolla", // name
                2022, // year
                4, // review
                150.0, // pricerent
                25000.0, // pricesell
                true, // isAvailable
                "https://autoland.com.pe/wp-content/uploads/2023/10/tipo-auto-coupe-autoland.png", // imageUrl
                -12.0464f, // lat
                -77.0428f, // lng
                "Auto en excelente estado, poco uso" // description
        );

        User user = new User("juanito1234", "password123", "juan_asdasd@hotmail.com");

        Profile profile = new Profile(user, "Juan", "Pérez", "juan_asdasd@hotmail.com", "987654321", "12345678901");

        Reservation reservation = new Reservation("Disponible", vehicle, profile);

        // Act
        reservation.updateStatus("Reservado");

        // Assert
        Assert.assertEquals("El estado de la reserva se actualizó correctamente", "Reservado",
                reservation.getStatus());

    }

}
