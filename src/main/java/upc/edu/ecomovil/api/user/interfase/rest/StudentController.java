package upc.edu.ecomovil.api.user.interfase.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;
import upc.edu.ecomovil.api.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetAllStudentQuery;
import upc.edu.ecomovil.api.user.domain.model.queries.Student.GetStudentByIdQuery;
import upc.edu.ecomovil.api.user.domain.services.StudentCommandService;
import upc.edu.ecomovil.api.user.domain.services.StudentQueryService;
import upc.edu.ecomovil.api.user.interfase.rest.resources.student.CreateStudentResource;
import upc.edu.ecomovil.api.user.interfase.rest.resources.student.StudentResource;
import upc.edu.ecomovil.api.user.interfase.rest.transform.student.CreateStudentCommandFromResourceAssembler;
import upc.edu.ecomovil.api.user.interfase.rest.transform.student.StudentResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Student Management Endpoints")
public class StudentController {
    private final StudentQueryService studentQueryService;
    private final StudentCommandService studentCommandService;
    private final UserRepository userRepository;

    public StudentController(StudentQueryService studentQueryService, StudentCommandService studentCommandService , UserRepository userRepository) {
        this.studentQueryService = studentQueryService;
        this.studentCommandService = studentCommandService;
        this.userRepository = userRepository;
    }


    @Operation(
            summary = "Create a Student",
            description = "Creates a Student with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<StudentResource> createProfile(@RequestBody CreateStudentResource resource) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
        var createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource, user );
        var student = studentCommandService.handle(createStudentCommand);
        if (student.isEmpty()) return ResponseEntity.badRequest().build();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return new ResponseEntity<>(studentResource, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<StudentResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllStudentQuery();
        var students = studentQueryService.handle(getAllProfilesQuery);
        var studentResources = students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(studentResources);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<StudentResource>> getAllStudentsByPlanId(@PathVariable Long planId) {
        var getAllProfilesQuery = new GetAllStudentQuery();
        var students = studentQueryService.handle(getAllProfilesQuery);
        var studentResources = students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(studentResources);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentResource> getProfileById(@PathVariable Long id) {
        var getProfileByIdQuery = new GetStudentByIdQuery(id);
        var student = studentQueryService.handle(getProfileByIdQuery);
        if (student.isEmpty()) return ResponseEntity.notFound().build();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.ok(studentResource);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<StudentResource> updateProfile(@PathVariable Long id, @RequestBody CreateStudentResource resource) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        var createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource, user);
        var student = studentCommandService.handle(createStudentCommand);
        if (student.isEmpty()) return ResponseEntity.badRequest().build();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.ok(studentResource);
    }
}
