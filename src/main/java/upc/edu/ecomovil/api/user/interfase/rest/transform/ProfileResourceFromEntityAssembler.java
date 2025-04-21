package upc.edu.ecomovil.api.user.interfase.rest.transform;

import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.interfase.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity){
        return new ProfileResource(entity.getId(), entity.getEmail(), entity.getFullName(), entity.getPhoneNumber());
    }
}
