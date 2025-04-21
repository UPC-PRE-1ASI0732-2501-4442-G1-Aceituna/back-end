package upc.edu.ecomovil.api.user.interfase.rest.transform;

import upc.edu.ecomovil.api.user.domain.model.commands.CreateProfileCommand;
import upc.edu.ecomovil.api.user.interfase.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler
{
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource){
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.email(), resource.phoneNumber());
    }
}
