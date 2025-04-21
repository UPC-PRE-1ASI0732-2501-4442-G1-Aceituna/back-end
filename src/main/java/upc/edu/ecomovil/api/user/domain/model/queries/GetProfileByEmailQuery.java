package upc.edu.ecomovil.api.user.domain.model.queries;

import upc.edu.ecomovil.api.user.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress email) {
}
