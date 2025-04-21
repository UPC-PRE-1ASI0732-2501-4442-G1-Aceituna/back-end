package upc.edu.ecomovil.api.plan2.interfaces.rest.transform;

import upc.edu.ecomovil.api.plan2.domain.model.commands.CreatePlan2Command;
import upc.edu.ecomovil.api.plan2.interfaces.rest.resources.CreatePlan2Resource;

public class CreatePlan2CommandFromResourceAssembler {
    public static CreatePlan2Command toCommandFromResource(CreatePlan2Resource resource){
        return new CreatePlan2Command(resource.name(), resource.description(), resource.price());
    }
}
