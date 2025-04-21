package upc.edu.ecomovil.api.plan2.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetAllPlans2Query;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetPlan2ByIdQuery;
import upc.edu.ecomovil.api.plan2.domain.services.Plan2QueryService;
import upc.edu.ecomovil.api.plan2.infraestructure.persistence.jpa.repositories.Plan2Repository;

import java.util.List;
import java.util.Optional;

@Service
public class Plan2QueryServiceImpl implements Plan2QueryService {
    private final Plan2Repository plan2Repository;

    public Plan2QueryServiceImpl(Plan2Repository plan2Repository) {
        this.plan2Repository = plan2Repository;
    }


    @Override
    public Optional<Plan2> handle(GetPlan2ByIdQuery query) {
        return plan2Repository.findById(query.id());
    }

    @Override
    public List<Plan2> handle(GetAllPlans2Query query) {
        return plan2Repository.findAll();
    }
}
