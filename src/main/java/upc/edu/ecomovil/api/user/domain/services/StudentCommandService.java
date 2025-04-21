package upc.edu.ecomovil.api.user.domain.services;

import upc.edu.ecomovil.api.user.domain.model.commands.CreateStudentCommand;
import upc.edu.ecomovil.api.user.domain.model.entities.Student;

import java.util.Optional;

public interface StudentCommandService {
    Optional<Student> handle(CreateStudentCommand command);
}
