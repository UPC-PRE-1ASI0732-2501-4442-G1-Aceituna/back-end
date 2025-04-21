package upc.edu.ecomovil.api.user.domain.model.aggregates;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import upc.edu.ecomovil.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateProfileCommand;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.EmailAddress;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.PersonName;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.PhoneNumber;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private PersonName name;

    @Embedded
    EmailAddress email;

    @Embedded
    private PhoneNumber phoneNumber;

    public Profile(String firstName, String lastName, String email, String phoneNumber) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);

    }

    public Profile(CreateProfileCommand command){
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.phoneNumber = new PhoneNumber(command.phoneNumber());
    }

    public Profile(){}

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



}
