package upc.edu.ecomovil.api.user.interfase.rest.transform;

import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateProfileCommand;
import upc.edu.ecomovil.api.user.interfase.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler
{
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource, User user){
        return new CreateProfileCommand(user, resource.firstName(), resource.lastName(), resource.email(), resource.phoneNumber(), resource.ruc(), resource.planId());
    }
}
