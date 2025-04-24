package upc.edu.ecomovil.api.vehicles.interfase.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upc.edu.ecomovil.api.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import upc.edu.ecomovil.api.user.infrastructure.persistence.jpa.repositories.ProfileRepository;
import upc.edu.ecomovil.api.vehicles.application.internal.commandservices.VehicleCommandServiceImpl;
import upc.edu.ecomovil.api.vehicles.domain.model.commands.CreateVehicleCommand;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesByTypeQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetVehicleByIdQuery;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleCommandService;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleQueryService;
import upc.edu.ecomovil.api.vehicles.infraestructure.persistence.jpa.repositories.VehicleRepository;
import upc.edu.ecomovil.api.vehicles.interfase.rest.resources.CreateVehicleResource;
import upc.edu.ecomovil.api.vehicles.interfase.rest.resources.VehicleResource;
import upc.edu.ecomovil.api.vehicles.interfase.rest.transform.CreateVehicleCommandFromResourceAssembler;
import upc.edu.ecomovil.api.vehicles.interfase.rest.transform.VehicleResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vehicles", description = "Vehicle Management Endpoints")
public class VehicleController {
    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommandService, ProfileRepository profileRepository, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommandService;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource) {
        // 1. Obtener el usuario logueado
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = authentication.getName();

        // 2. Obtener el usuario y perfil correspondiente
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        var profile = profileRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para el usuario: " + username));

        // 3. Armar el comando manualmente (sin profileId en el body del request)
        var command = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource, profile.getId());

        // 4. Ejecutar comando
        var vehicle = vehicleCommandService.handle(command);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();

        // 5. Retornar recurso
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResource>> getAllVehicles(){
        var getAllVehiclesQuery = new GetAllVehiclesQuery();
        var vehicles = vehicleQueryService.handle(getAllVehiclesQuery);
        var vehicleResources = vehicles.stream().map(VehicleResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId){
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByType(@PathVariable String type){
        var getAllVehiclesByTypeQuery = new GetAllVehiclesByTypeQuery(type);
        var vehicles = vehicleQueryService.handle(getAllVehiclesByTypeQuery);
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleResources = vehicles.stream().map(VehicleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("/my-vehicles")
    public ResponseEntity<List<VehicleResource>> getMyVehicles() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = authentication.getName();

        // Paso 1: Buscar el User por username
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        // Paso 2: Buscar el Profile por ID (porque ID de Profile == ID de User)
        var profile = profileRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para el usuario: " + username));

        // Paso 3: Buscar vehículos por el profile.id
        var vehicles = vehicleRepository.findAllByOwnerId(profile.getId());

        // Paso 4: Convertir a recursos
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(vehicleResources);
    }
}


//    @GetMapping
//    public ResponseEntity<List<AcquirerResource>> getAllProfiles() {
//        var getAllProfilesQuery = new GetAllAcquirerQuery();
//        var acquirer = acquirerQueryService.handle(getAllProfilesQuery);
//        var acquirerResources = acquirer.stream().map(AcquirerResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
//        return ResponseEntity.ok(acquirerResources);
//    }
//}