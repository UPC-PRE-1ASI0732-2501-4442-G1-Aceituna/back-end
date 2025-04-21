package upc.edu.ecomovil.api.user.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;
import upc.edu.ecomovil.api.plan2.interfaces.rest.acl.PlansContextFacade;

import java.util.Optional;

@Service
public class ExternalPlanService {
    private final PlansContextFacade plansContextFacade;

    @Autowired
    public ExternalPlanService(PlansContextFacade plansContextFacade) {
        this.plansContextFacade = plansContextFacade;
    }


    public Optional<Plan2> fetchPlanById(Long id) {
        // Usar PlansContextFacade para obtener el plan por ID
        return plansContextFacade.getPlanById(id);
    }
}