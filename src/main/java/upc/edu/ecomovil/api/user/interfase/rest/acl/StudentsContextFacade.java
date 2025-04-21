package upc.edu.ecomovil.api.user.interfase.rest.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.domain.model.queries.GetProfileByIdQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetStudentByIdQuery;
import upc.edu.ecomovil.api.user.domain.services.StudentCommandService;
import upc.edu.ecomovil.api.user.domain.services.StudentQueryService;

import java.util.Optional;

@Service
public class StudentsContextFacade {
    private final StudentCommandService studentService;
    private final StudentQueryService studentQueryService;

    @Autowired
    public StudentsContextFacade(StudentCommandService studentService, StudentQueryService studentQueryService) {
        this.studentService = studentService;
        this.studentQueryService = studentQueryService;
    }

    public Optional<Student> getStudentById(Long id) {
        GetStudentByIdQuery query = new GetStudentByIdQuery(id);
        return studentQueryService.handle(query);
    }
}

