package upc.edu.ecomovil.api.user.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.user.application.internal.outboundservices.acl.ExternalPlanService;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateStudentCommand;
import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.RucNumber;
import upc.edu.ecomovil.api.user.domain.services.StudentCommandService;
import upc.edu.ecomovil.api.user.infrastructure.persistence.jpa.repositories.StudentRepository;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;
    private final ExternalPlanService externalPlanService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ExternalPlanService externalPlanService) {
        this.studentRepository = studentRepository;
        this.externalPlanService = externalPlanService;
    }

    @Override
    public Optional<Student> handle(CreateStudentCommand command){
        var ruc = new RucNumber(command.rucNumber());
        studentRepository.findByRuc(ruc).ifPresent(
                student -> {
                    throw new IllegalArgumentException("Student with RUC " + command.rucNumber() + " already exists");
                });


        var plan = externalPlanService.fetchPlanById(command.planId());
        if (plan.isEmpty()) {
            throw new IllegalArgumentException("El plan con el ID especificado no existe");

            // Crear el estudiante con el plan asignado

        }
        var student = new Student(command, plan.get());
        studentRepository.save(student);
        return Optional.of(student);
    }
}
