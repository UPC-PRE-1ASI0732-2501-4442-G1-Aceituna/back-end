package upc.edu.ecomovil.api.vehicles.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.interfase.rest.acl.ProfilesContextFacade2;

import java.util.Optional;

@Service
public class ExternalStudentService {
    private final ProfilesContextFacade2 studentsContextFacade;

    @Autowired
    public ExternalStudentService(ProfilesContextFacade2 studentsContextFacade) {
        this.studentsContextFacade = studentsContextFacade;
    }

    public Optional<Profile> fetchStudentById(Long id) {
        // Usar StudentsContextFacade para obtener el estudiante por ID
        return studentsContextFacade.getProfileById(id);
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