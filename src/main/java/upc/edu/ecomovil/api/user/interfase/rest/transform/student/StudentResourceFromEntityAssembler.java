package upc.edu.ecomovil.api.user.interfase.rest.transform.student;

import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.interfase.rest.resources.student.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student student){
        return new StudentResource(student.getId(), student.getFullName(), student.getEmail(), student.getPhoneNumber(), student.getRuc(), student.getPlan().getId());
    }
}
