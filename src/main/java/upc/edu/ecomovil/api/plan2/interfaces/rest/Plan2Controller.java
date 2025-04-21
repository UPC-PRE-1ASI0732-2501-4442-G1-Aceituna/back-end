package upc.edu.ecomovil.api.plan2.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetAllPlans2Query;
import upc.edu.ecomovil.api.plan2.domain.model.queries.GetPlan2ByIdQuery;
import upc.edu.ecomovil.api.plan2.domain.services.Plan2CommandService;
import upc.edu.ecomovil.api.plan2.domain.services.Plan2QueryService;
import upc.edu.ecomovil.api.plan2.interfaces.rest.resources.CreatePlan2Resource;
import upc.edu.ecomovil.api.plan2.interfaces.rest.resources.Plan2Resource;
import upc.edu.ecomovil.api.plan2.interfaces.rest.transform.CreatePlan2CommandFromResourceAssembler;
import upc.edu.ecomovil.api.plan2.interfaces.rest.transform.Plan2ResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/plan2", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Plan2", description = "Plan2 Management Endpoints")
public class Plan2Controller {
    private final Plan2QueryService plan2QueryService;
    private final Plan2CommandService plan2CommandService;

    public Plan2Controller(Plan2QueryService plan2QueryService, Plan2CommandService plan2CommandService) {
        this.plan2QueryService = plan2QueryService;
        this.plan2CommandService = plan2CommandService;
    }

    @Operation(
            summary = "Create a Plan2",
            description = "Creates a Plan2 with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plan2 created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<Plan2Resource> createPlan2(@RequestBody CreatePlan2Resource resource){
        var createPlan2Command = CreatePlan2CommandFromResourceAssembler.toCommandFromResource(resource);
        var plan2 = plan2CommandService.handle(createPlan2Command);
        if (plan2.isEmpty()) return ResponseEntity.badRequest().build();
        var plan2Resource = Plan2ResourceFromEntityAssembler.toResourceFromEntity(plan2.get());
        return new ResponseEntity<>(plan2Resource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Plan2Resource>> getAllPlan2(){
        var getAllPlan2Query = new GetAllPlans2Query();
        var plan2 = plan2QueryService.handle(getAllPlan2Query);
        var plan2Resources = plan2.stream().map(Plan2ResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(plan2Resources);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Plan2Resource> getPlan2ById(@PathVariable Long id){
        var getPlan2ByIdQuery = new GetPlan2ByIdQuery(id);
        var plan2 = plan2QueryService.handle(getPlan2ByIdQuery);
        if (plan2.isEmpty()) return ResponseEntity.notFound().build();
        var plan2Resource = Plan2ResourceFromEntityAssembler.toResourceFromEntity(plan2.get());
        return ResponseEntity.ok(plan2Resource);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletePlan2ById(@PathVariable Long id){
        var getPlan2ByIdQuery = new GetPlan2ByIdQuery(id);
        var plan2 = plan2QueryService.handle(getPlan2ByIdQuery);
        if (plan2.isEmpty()) return ResponseEntity.notFound().build();
        plan2CommandService.deletePlan2ById(id);
        return ResponseEntity.noContent().build();
    }

}
