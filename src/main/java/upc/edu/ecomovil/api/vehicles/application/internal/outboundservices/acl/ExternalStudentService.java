package upc.edu.ecomovil.api.vehicles.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.interfase.rest.acl.StudentsContextFacade;

import java.util.Optional;

@Service
public class ExternalStudentService {
    private final StudentsContextFacade studentsContextFacade;

    @Autowired
    public ExternalStudentService(StudentsContextFacade studentsContextFacade) {
        this.studentsContextFacade = studentsContextFacade;
    }

    public Optional<Student> fetchStudentById(Long id) {
        // Usar StudentsContextFacade para obtener el estudiante por ID
        return studentsContextFacade.getStudentById(id);
    }
}


//@Service
//public class ExternalPlanService {
//    private final PlansContextFacade plansContextFacade;
//
//    @Autowired
//    public ExternalPlanService(PlansContextFacade plansContextFacade) {
//        this.plansContextFacade = plansContextFacade;
//    }
//
//
//    public Optional<Plan2> fetchPlanById(Long id) {
//        // Usar PlansContextFacade para obtener el plan por ID
//        return plansContextFacade.getPlanById(id);
//    }
//}