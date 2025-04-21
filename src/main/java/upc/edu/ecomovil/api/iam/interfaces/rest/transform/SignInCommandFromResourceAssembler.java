package upc.edu.ecomovil.api.iam.interfaces.rest.transform;

import upc.edu.ecomovil.api.iam.domain.model.commands.SignInCommand;
import upc.edu.ecomovil.api.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}