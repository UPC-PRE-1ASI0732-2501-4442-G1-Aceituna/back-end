package upc.edu.ecomovil.api.iam.interfaces.rest.transform;

import upc.edu.ecomovil.api.iam.domain.model.entities.Role;
import upc.edu.ecomovil.api.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
