package upc.edu.ecomovil.api.plan2.interfaces.rest.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetPlan2ByIdQuery;
import upc.edu.ecomovil.api.plan2.domain.services.Plan2CommandService;
import upc.edu.ecomovil.api.plan2.domain.services.Plan2QueryService;

import java.util.Optional;

@Service
public class PlansContextFacade {
    private final Plan2CommandService planService;
    private final Plan2QueryService planQueryService;


    @Autowired
    public PlansContextFacade(Plan2CommandService planService, Plan2QueryService planQueryService) {
        this.planService = planService;
        this.planQueryService = planQueryService;
    }

    public Optional<Plan2> getPlanById(Long id) {
        GetPlan2ByIdQuery query = new GetPlan2ByIdQuery(id);
        return planQueryService.handle(query);
    }

}