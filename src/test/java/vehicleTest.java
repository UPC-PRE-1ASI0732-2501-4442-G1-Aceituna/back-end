
import org.junit.Assert;
import org.junit.Test;

import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;

public class vehicleTest {
        @Test
        public void testVehicleUpdateDetails() {
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

                // Act
                vehicle.updateDetails("Honda", "Civic", 2021);

                // Assert
                Assert.assertEquals("El fabricante se actualizó correctamente", "Honda", vehicle.getType());
                Assert.assertEquals("El nombre se actualizó correctamente", "Civic", vehicle.getName());
                Assert.assertEquals("El año se actualizó correctamente", Integer.valueOf(2021), vehicle.getYear());

        }

        @Test
        public void testVehicleUpdatePrices() {
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

                // Act
                vehicle.updatePrices(200.0, 30000.0);

                // Assert
                Assert.assertEquals("El precio de renta se actualizó correctamente", Double.valueOf(200.0),
                                vehicle.getPriceRent());
                Assert.assertEquals("El precio de venta se actualizó correctamente", Double.valueOf(30000.0),
                                vehicle.getPriceSell());
        }

}
