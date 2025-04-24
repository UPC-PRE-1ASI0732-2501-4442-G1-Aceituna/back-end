package upc.edu.ecomovil.api.user.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateProfileCommand;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.EmailAddress;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.PersonName;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.PhoneNumber;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.RucNumber;

@Getter
@Entity
public class Profile {

    @Id // 👈 Añades esto para sobrescribir la estrategia
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id") // Usa el ID de User como FK y PK
    private User user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "plan_id")  // Relaciona con la tabla 'Plan'
    private Plan2 plan;


    @Embedded
    private RucNumber ruc;



    @Embedded
    private PersonName name;

    @Embedded
    EmailAddress email;

    @Embedded
    private PhoneNumber phoneNumber;

    public Profile(User user, String firstName, String lastName, String email, String phoneNumber, String ruc) {
        this.user = user;
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.ruc = new RucNumber(ruc);

    }

    public Profile(CreateProfileCommand command, Plan2 plan) {
        this.user = command.user();
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.phoneNumber = new PhoneNumber(command.phoneNumber());
        this.ruc = new RucNumber(command.rucNumber());
        this.plan = plan;
    }

    public Profile(){}


    public void updateRuc(String ruc){
        this.ruc = new RucNumber(ruc);
    }

    public String getRuc(){
        return ruc.getRucNumber();
    }


    public void updateName(String firstName, String lastName){
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email){
        this.email = new EmailAddress(email);
    }

    public void updatePhoneNumber(String phoneNumber){
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public String getFullName(){
        return name.getFullName();
    }

    public String getEmail(){
        return email.address();
    }

    public String getPhoneNumber(){
        return phoneNumber.getPhoneNumber();
    }


    public void setPlan(Plan2 plan) {
        this.plan = plan;
    }
}
