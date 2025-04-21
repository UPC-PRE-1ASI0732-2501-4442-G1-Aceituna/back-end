package upc.edu.ecomovil.api.user.domain.services;

import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.domain.model.queries.GetAllProfilesQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.GetProfileByEmailQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
}
