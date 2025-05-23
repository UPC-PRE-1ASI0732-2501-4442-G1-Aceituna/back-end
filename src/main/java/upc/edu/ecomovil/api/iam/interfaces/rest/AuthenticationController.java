package upc.edu.ecomovil.api.iam.interfaces.rest;

import upc.edu.ecomovil.api.iam.domain.services.UserCommandService;
import upc.edu.ecomovil.api.iam.interfaces.rest.resources.AuthenticatedUserResource;
import upc.edu.ecomovil.api.iam.interfaces.rest.resources.SignInResource;
import upc.edu.ecomovil.api.iam.interfaces.rest.resources.SignUpResource;
import upc.edu.ecomovil.api.iam.interfaces.rest.resources.UserResource;
import upc.edu.ecomovil.api.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import upc.edu.ecomovil.api.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import upc.edu.ecomovil.api.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import upc.edu.ecomovil.api.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upc.edu.ecomovil.api.plan2.infraestructure.persistence.jpa.repositories.Plan2Repository;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.infrastructure.persistence.jpa.repositories.ProfileRepository;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final ProfileRepository profileRepository;
    private final Plan2Repository plan2Repository;

    public AuthenticationController(UserCommandService userCommandService, ProfileRepository profileRepository, Plan2Repository plan2Repository) {
        this.userCommandService = userCommandService;
        this.profileRepository = profileRepository;
        this.plan2Repository = plan2Repository;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        var defaultPlan = plan2Repository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Plan with ID 1 not found"));

        var profile = new Profile(
                user.get(),
                signUpResource.username(),
                signUpResource.username(),
                signUpResource.email(),
                "000000000",
                "00000000000"
        );
        profile.setPlan(defaultPlan); // 👈 Asignamos el plan
        profileRepository.save(profile);


        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }



}