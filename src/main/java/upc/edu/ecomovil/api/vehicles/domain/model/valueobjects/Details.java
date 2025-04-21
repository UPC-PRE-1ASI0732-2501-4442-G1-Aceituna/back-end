package upc.edu.ecomovil.api.vehicles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;


@Embeddable
public record Details(String type, String name, Integer year) {
    public Details() { this(null, null, null); }

    public Details {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (year == null) {
            throw new IllegalArgumentException("Year cannot be null");
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

}

//@Embeddable
//public record PersonName(String firstName, String lastName) {
//    public PersonName() { this(null, null); }
//
//    public PersonName {
//        if (firstName == null || firstName.isBlank()) {
//            throw new IllegalArgumentException("First name cannot be null or blank");
//        }
//        if (lastName == null || lastName.isBlank()) {
//            throw new IllegalArgumentException("Last name cannot be null or blank");
//        }
//    }
//    public String getFullName() {
//        return String.format("%s %s", firstName, lastName);
//    }
//}