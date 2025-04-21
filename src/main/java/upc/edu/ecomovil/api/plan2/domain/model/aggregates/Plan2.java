package upc.edu.ecomovil.api.plan2.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import upc.edu.ecomovil.api.plan2.domain.model.commands.CreatePlan2Command;
import upc.edu.ecomovil.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
public class Plan2 extends AuditableAbstractAggregateRoot<Plan2> {

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Double price;

    public Plan2(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Plan2(CreatePlan2Command command){
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();

    }

    public Plan2(){}

    public void updateDetails(String name, String description, Double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }



}
