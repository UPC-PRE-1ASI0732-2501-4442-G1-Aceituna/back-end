package upc.edu.ecomovil.api.reservations.application.internal.outboundservices.acl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.interfase.rest.acl.ProfilesContextFacade;

import java.util.Optional;

@Service
public class ExternalProfileService {
    public final ProfilesContextFacade profilesContextFacade;

    @Autowired
    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

  public Optional<Profile> fetchProfileById(Long id) {
        return profilesContextFacade.getProfileById(id);
    }
}

