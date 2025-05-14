import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;

public class plan2Test {
    @Test
    public void testPlan2UpdateDetails() {
        // Arrange
        Plan2 plan = new Plan2("Plan Básico", "Acceso limitado a funciones", 29.99);

        // Act
        plan.updateDetails("Plan Premium", "Acceso completo a todas las funciones", 49.99);

        // Assert
        Assert.assertEquals("El plan se actualizó correctamente", "Plan Premium", plan.getName());
        Assert.assertEquals("La descripcion del plan se actualizó correctamente",
                "Acceso completo a todas las funciones", plan.getDescription());
        Assert.assertEquals("El precio del plan se actualizó correctamente", 49.99, plan.getPrice(), 0.01);

    }
}
