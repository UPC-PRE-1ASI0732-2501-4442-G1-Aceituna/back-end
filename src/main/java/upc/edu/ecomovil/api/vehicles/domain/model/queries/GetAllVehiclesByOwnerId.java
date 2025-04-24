package upc.edu.ecomovil.api.vehicles.domain.model.queries;

public record GetAllVehiclesByOwnerId(Long ownerId) {
    // This record is used to encapsulate the query for getting all vehicles by owner ID
    // It contains a single field, ownerId, which represents the ID of the vehicle owner
    // The record automatically generates a constructor, getters, equals, hashCode, and toString methods
}
