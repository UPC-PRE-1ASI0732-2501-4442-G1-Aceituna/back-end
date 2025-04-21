package upc.edu.ecomovil.api.reservations.domain.model.commands;

public record CreateReservationCommand(String status, Long vehicleId, Long profileId) {
}
