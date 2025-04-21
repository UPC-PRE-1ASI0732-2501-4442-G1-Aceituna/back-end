package upc.edu.ecomovil.api.user.interfase.rest.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.domain.model.queries.GetProfileByIdQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetStudentByIdQuery;
import upc.edu.ecomovil.api.user.domain.services.ProfileCommandService;
import upc.edu.ecomovil.api.user.domain.services.ProfileQueryService;
import upc.edu.ecomovil.api.user.domain.services.StudentCommandService;
import upc.edu.ecomovil.api.user.domain.services.StudentQueryService;

import java.util.Optional;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileService;
    private final ProfileQueryService profileQueryService;

    @Autowired
    public ProfilesContextFacade(ProfileCommandService profileService, ProfileQueryService profileQueryService) {
        this.profileService = profileService;
        this.profileQueryService = profileQueryService;
    }

    public Optional<Profile> getProfileById(Long id) {
        GetProfileByIdQuery query = new GetProfileByIdQuery(id);
        return profileQueryService.handle(query);
    }
}



