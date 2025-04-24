package upc.edu.ecomovil.api.user.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.application.internal.outboundservices.acl.ExternalPlanService;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateProfileCommand;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.EmailAddress;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.RucNumber;
import upc.edu.ecomovil.api.user.domain.services.ProfileCommandService;
import upc.edu.ecomovil.api.user.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final ExternalPlanService externalPlanService;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, ExternalPlanService externalPlanService) {
        this.profileRepository = profileRepository;
        this.externalPlanService = externalPlanService;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var ruc = new RucNumber(command.rucNumber());
       profileRepository.findByRuc(ruc).ifPresent(
                student -> {
                    throw new IllegalArgumentException("Student with RUC " + command.rucNumber() + " already exists");
                });


        var plan = externalPlanService.fetchPlanById(command.planId());
        if (plan.isEmpty()) {
            throw new IllegalArgumentException("El plan con el ID especificado no existe");

            // Crear el estudiante con el plan asignado

        }
        var student = new Profile(command, plan.get());
        profileRepository.save(student);
        return Optional.of(student);
    }
    }


