package upc.edu.ecomovil.api.user.interfase.rest.transform.student;

import upc.edu.ecomovil.api.user.domain.model.commands.CreateStudentCommand;
import upc.edu.ecomovil.api.user.interfase.rest.resources.student.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource){
        return new CreateStudentCommand(resource.firstName(), resource.lastName(), resource.email(), resource.phoneNumber(), resource.ruc(), resource.planId());
    }
}
