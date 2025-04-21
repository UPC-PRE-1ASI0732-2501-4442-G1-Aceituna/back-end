package upc.edu.ecomovil.api.reservations.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import upc.edu.ecomovil.api.reservations.domain.model.commands.CreateReservationCommand;
import upc.edu.ecomovil.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;

@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    String status; //pending, accepted, rejected, cancelled

 @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

 @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

 public Reservation(String status, Vehicle vehicle, Profile profile) {
    this.status = status;
    this.vehicle = vehicle;
    this.profile = profile;
 }

 public Reservation(CreateReservationCommand command, Vehicle vehicle, Profile profile) {
    this.status = command.status();
    this.vehicle = vehicle;
    this.profile = profile;
 }

 public Reservation(){}



    public void updateStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public Profile getProfile(){
        return profile;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
