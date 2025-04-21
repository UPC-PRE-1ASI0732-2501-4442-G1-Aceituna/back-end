package upc.edu.ecomovil.api.reservations.domain.model.commands;

public record UpdateReservationStatusCommand(Long reservationId, String status) {
}
