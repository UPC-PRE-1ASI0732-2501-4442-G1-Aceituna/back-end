package upc.edu.ecomovil.api.vehicles.interfase.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesByTypeQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetVehicleByIdQuery;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleCommandService;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleQueryService;
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

    public VehicleController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommandService) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommandService;
    }

    @Operation(
            summary = "Create a Vehicle",
            description = "Creates a Vehicle with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource){
        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource);
        var vehicle = vehicleCommandService.handle(createVehicleCommand);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
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
}


//    @GetMapping
//    public ResponseEntity<List<AcquirerResource>> getAllProfiles() {
//        var getAllProfilesQuery = new GetAllAcquirerQuery();
//        var acquirer = acquirerQueryService.handle(getAllProfilesQuery);
//        var acquirerResources = acquirer.stream().map(AcquirerResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
//        return ResponseEntity.ok(acquirerResources);
//    }
//}