package upc.edu.ecomovil.api.vehicles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Prices(Double PriceRent,Double PriceSell ) {
    public Prices() { this(null, null ); }

    public Prices {
        if (PriceRent == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (PriceSell == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
    }

    public Double getPriceRent() {
        return PriceRent;
    }
    public Double getPriceSell() {
        return PriceSell;
    }
}
