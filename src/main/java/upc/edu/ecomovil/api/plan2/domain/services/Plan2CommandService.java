package upc.edu.ecomovil.api.plan2.domain.services;

import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.domain.model.commands.CreatePlan2Command;

import java.util.Optional;

public interface Plan2CommandService {
    Optional<Plan2> handle(CreatePlan2Command command);

    void deletePlan2ById(Long id);
}
