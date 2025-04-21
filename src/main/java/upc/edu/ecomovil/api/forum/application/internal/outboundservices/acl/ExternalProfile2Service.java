package upc.edu.ecomovil.api.forum.application.internal.outboundservices.acl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.interfase.rest.acl.ProfilesContextFacade2;

import java.util.Optional;

@Service
public class ExternalProfile2Service {
    private final ProfilesContextFacade2 profilesContextFacade;
    @Autowired
    public ExternalProfile2Service(ProfilesContextFacade2 profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<Profile> fetchProfileById(Long id) {
        return profilesContextFacade.getProfileById(id);
    }
}

//@Service
//public class ExternalStudentService {
//    private final StudentsContextFacade studentsContextFacade;
//
//    @Autowired
//    public ExternalStudentService(StudentsContextFacade studentsContextFacade) {
//        this.studentsContextFacade = studentsContextFacade;
//    }
//
//    public Optional<Student> fetchStudentById(Long id) {
//        // Usar StudentsContextFacade para obtener el estudiante por ID
//        return studentsContextFacade.getStudentById(id);
//    }
//}