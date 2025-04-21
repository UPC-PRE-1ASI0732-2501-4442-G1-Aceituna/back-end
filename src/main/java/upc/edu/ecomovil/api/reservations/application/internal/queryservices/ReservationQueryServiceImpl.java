package upc.edu.ecomovil.api.reservations.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.reservations.domain.model.aggregates.Reservation;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetAllReservationsByProfileIdQuery;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetAllReservationsQuery;
import upc.edu.ecomovil.api.reservations.domain.model.queries.GetReservationByIdQuery;
import upc.edu.ecomovil.api.reservations.domain.services.ReservationQueryService;
import upc.edu.ecomovil.api.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return reservationRepository.findById(query.reservationid());
    }

    @Override
    public List<Reservation> handle(GetAllReservationsQuery query) {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> handle(GetAllReservationsByProfileIdQuery query) {
        return reservationRepository.findAllByProfileId(query.profileId());
    }
}


//    @Override
//    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
//        return vehicleRepository.findById(query.vehicleId());
//    }
//
//    @Override
//    public List<Vehicle> handle(GetAllVehiclesQuery query) {
//        return vehicleRepository.findAll();
//    }
//
//    @Override
//    public List<Vehicle> handle(GetAllVehiclesByTypeQuery query) {
//        return vehicleRepository.findAllByDetails_Type(query.type());
//    }
//}