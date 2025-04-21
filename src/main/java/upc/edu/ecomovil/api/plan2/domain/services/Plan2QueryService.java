package upc.edu.ecomovil.api.plan2.domain.services;

import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetAllPlans2Query;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetPlan2ByIdQuery;

import java.util.List;
import java.util.Optional;

public interface Plan2QueryService {
    Optional<Plan2> handle(GetPlan2ByIdQuery query);
    List<Plan2> handle(GetAllPlans2Query query);
}
