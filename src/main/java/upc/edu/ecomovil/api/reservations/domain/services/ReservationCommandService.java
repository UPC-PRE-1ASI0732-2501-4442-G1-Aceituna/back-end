package upc.edu.ecomovil.api.reservations.domain.services;

import jakarta.transaction.Transactional;
import upc.edu.ecomovil.api.reservations.domain.model.aggregates.Reservation;
import upc.edu.ecomovil.api.reservations.domain.model.commands.CreateReservationCommand;
import upc.edu.ecomovil.api.reservations.domain.model.commands.UpdateReservationStatusCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Optional<Reservation> handle (CreateReservationCommand command);

    @Transactional
    Optional<Reservation> handle(UpdateReservationStatusCommand command);
}
