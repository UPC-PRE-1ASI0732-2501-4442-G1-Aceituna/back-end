package upc.edu.ecomovil.api.vehicles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Review(Integer Review) {
    public Review {
        if (Review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        if (Review < 0 || Review > 5) {
            throw new IllegalArgumentException("Review must be between 0 and 5");
        }
    }

    public Integer getReview() {
        return Review;
    }
}
