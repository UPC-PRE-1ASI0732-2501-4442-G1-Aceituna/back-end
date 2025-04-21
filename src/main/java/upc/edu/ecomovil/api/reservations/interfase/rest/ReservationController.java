package upc.edu.ecomovil.api.reservations.interfase.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.ecomovil.api.reservations.domain.model.commands.UpdateReservationStatusCommand;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetAllReservationsByProfileIdQuery;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetAllReservationsQuery;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetReservationByIdQuery;
import upc.edu.ecomovil.api.reservations.domain.services.ReservationCommandService;
import upc.edu.ecomovil.api.reservations.domain.services.ReservationQueryService;
import upc.edu.ecomovil.api.reservations.interfase.rest.resources.CreateReservationResource;
import upc.edu.ecomovil.api.reservations.interfase.rest.resources.ReservationResource;
import upc.edu.ecomovil.api.reservations.interfase.rest.transform.CreateReservationCommandFromResourceAssembler;
import upc.edu.ecomovil.api.reservations.interfase.rest.transform.ReservationResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Reservation Management Endpoints")
public class ReservationController {
    private final ReservationQueryService reservationQueryService;
    private final ReservationCommandService reservationCommandService;

    public ReservationController(ReservationQueryService reservationQueryService, ReservationCommandService reservationCommandService) {
        this.reservationQueryService = reservationQueryService;
        this.reservationCommandService = reservationCommandService;
    }

    @Operation(
            summary = "Create a Reservation",
            description = "Creates a Reservation with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource){
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservation = reservationCommandService.handle(createReservationCommand);
        if (reservation.isEmpty()) return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationResource>> getAllReservations(){
        var getAllReservationsQuery = new GetAllReservationsQuery();
        var reservations = reservationQueryService.handle(getAllReservationsQuery);
        var reservationResources = reservations.stream().map(ReservationResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("/id/{reservationId}")
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable Long reservationId){
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);
        if (reservation.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ReservationResource>> getAllReservationsByProfileId(@PathVariable Long profileId){
        var getAllReservationsByProfileIdQuery = new GetAllReservationsByProfileIdQuery(profileId);
        var reservations = reservationQueryService.handle(getAllReservationsByProfileIdQuery);
        if (reservations.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResources = reservations.stream().map(ReservationResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @Operation(
            summary = "Update the status of a Reservation",
            description = "Updates the status of a Reservation with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PutMapping("/{reservationId}/status")
    public ResponseEntity<ReservationResource> updateReservationStatus(@PathVariable Long reservationId, @RequestBody String status) {
        var updateCommand = new UpdateReservationStatusCommand(reservationId, status);
        var reservation = reservationCommandService.handle(updateCommand);
        if (reservation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }

  //update reservation
    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationResource> updateReservation(@PathVariable Long reservationId, @RequestBody CreateReservationResource resource){
        var updateCommand = new UpdateReservationStatusCommand(reservationId, resource.status());
        var reservation = reservationCommandService.handle(updateCommand);
        if (reservation.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId){
        var deleteCommand = new UpdateReservationStatusCommand(reservationId, "DELETED");
        var reservation = reservationCommandService.handle(deleteCommand);
        if (reservation.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

}


//@RestController
//@RequestMapping(value = "api/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
//@Tag(name = "Vehicles", description = "Vehicle Management Endpoints")
//public class VehicleController {
//    private final VehicleQueryService vehicleQueryService;
//    private final VehicleCommandService vehicleCommandService;
//
//    public VehicleController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommandService) {
//        this.vehicleQueryService = vehicleQueryService;
//        this.vehicleCommandService = vehicleCommandService;
//    }
//
//    @Operation(
//            summary = "Create a Vehicle",
//            description = "Creates a Vehicle with the provided data")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Vehicle created"),
//            @ApiResponse(responseCode = "400", description = "Bad request")
//    })
//    @PostMapping
//    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource){
//        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource);
//        var vehicle = vehicleCommandService.handle(createVehicleCommand);
//        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
//        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
//        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<VehicleResource>> getAllVehicles(){
//        var getAllVehiclesQuery = new GetAllVehiclesQuery();
//        var vehicles = vehicleQueryService.handle(getAllVehiclesQuery);
//        var vehicleResources = vehicles.stream().map(VehicleResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
//        return ResponseEntity.ok(vehicleResources);
//    }
//
//    @GetMapping("/id/{vehicleId}")
//    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId){
//        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
//        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
//        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
//        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
//        return ResponseEntity.ok(vehicleResource);
//    }
//
//    @GetMapping("/type/{type}")
//    public ResponseEntity<List<VehicleResource>> getAllVehiclesByType(@PathVariable String type){
//        var getAllVehiclesByTypeQuery = new GetAllVehiclesByTypeQuery(type);
//        var vehicles = vehicleQueryService.handle(getAllVehiclesByTypeQuery);
//        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
//        var vehicleResources = vehicles.stream().map(VehicleResourceFromEntityAssembler::toResourceFromEntity).toList();
//        return ResponseEntity.ok(vehicleResources);
//    }
//
//}