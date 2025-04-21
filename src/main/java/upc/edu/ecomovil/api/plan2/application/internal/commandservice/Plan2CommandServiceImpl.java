package upc.edu.ecomovil.api.plan2.application.internal.commandservice;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.domain.model.commands.CreatePlan2Command;
import upc.edu.ecomovil.api.plan2.domain.services.Plan2CommandService;
import upc.edu.ecomovil.api.plan2.infraestructure.persistence.jpa.repositories.Plan2Repository;

import java.util.Optional;

@Service
public class Plan2CommandServiceImpl implements Plan2CommandService {
    private final Plan2Repository plan2Repository;

    public Plan2CommandServiceImpl(Plan2Repository plan2Repository) {
        this.plan2Repository = plan2Repository;
    }

    @Override
    public Optional<Plan2> handle(CreatePlan2Command command) {
        var plan2 = new Plan2(command);
        plan2Repository.save(plan2);
        return Optional.of(plan2);
    }

    @Override
    public void deletePlan2ById(Long id) {
        plan2Repository.deleteById(id);
    }

}
