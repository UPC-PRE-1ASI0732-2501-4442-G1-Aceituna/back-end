package upc.edu.ecomovil.api.plan2.interfaces.rest.transform;

import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.interfaces.rest.resources.Plan2Resource;

public class Plan2ResourceFromEntityAssembler {
    public static Plan2Resource toResourceFromEntity(Plan2 entity){
        return new Plan2Resource(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice());
    }
}
