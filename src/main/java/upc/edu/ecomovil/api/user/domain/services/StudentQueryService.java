package upc.edu.ecomovil.api.user.domain.services;

import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetAllStudentByPlanIdQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetAllStudentQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetStudentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentQueryService {
    List<Student> handle(GetAllStudentQuery query);
    List<Student> handle(GetAllStudentByPlanIdQuery query);
    Optional<Student> handle(GetStudentByIdQuery query);
//    List<Student> handle(GetAllStudentsByPlanIdQuery query);
}
