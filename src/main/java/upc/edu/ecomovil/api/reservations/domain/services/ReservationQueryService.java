package upc.edu.ecomovil.api.reservations.domain.services;

import upc.edu.ecomovil.api.reservations.domain.model.aggregates.Reservation;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetAllReservationsByProfileIdQuery;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetAllReservationsQuery;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetReservationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle (GetReservationByIdQuery query);
    List<Reservation> handle (GetAllReservationsQuery query);
    List<Reservation> handle (GetAllReservationsByProfileIdQuery query);
}
