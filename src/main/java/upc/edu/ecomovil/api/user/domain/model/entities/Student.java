package upc.edu.ecomovil.api.user.domain.model.entities;

import jakarta.persistence.*;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateStudentCommand;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.RucNumber;

@Entity
public class Student extends Profile {
    //falta relaciones con vehicles y plans

    @ManyToOne
    @JoinColumn(name = "plan_id")  // Relaciona con la tabla 'Plan'
    private Plan2 plan;

    @Embedded
    private RucNumber ruc;

    public Student(String firstName, String lastName, String email, String phoneNumber, String ruc) {
        super(firstName, lastName, email, phoneNumber);
        this.ruc = new RucNumber(ruc);
    }

    public Student() {
        super();
    }

    public Student(CreateStudentCommand command, Plan2 plan) {
        super(command.firstName(), command.lastName(), command.email(), command.phoneNumber());
        this.ruc = new RucNumber(command.rucNumber());
        this.plan = plan;
    }

    public void updateRuc(String ruc){
        this.ruc = new RucNumber(ruc);
    }

    public String getRuc(){
        return ruc.getRucNumber();
    }
    public Plan2 getPlan() {
        return plan;
    }

}
