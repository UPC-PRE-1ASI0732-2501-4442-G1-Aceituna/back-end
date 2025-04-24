package upc.edu.ecomovil.api.vehicles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.vehicles.application.internal.outboundservices.acl.ExternalStudentService;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.domain.model.commands.CreateVehicleCommand;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleCommandService;
import upc.edu.ecomovil.api.vehicles.infraestructure.persistence.jpa.repositories.VehicleRepository;

import java.util.Optional;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {
    private final VehicleRepository vehicleRepository;
    private final ExternalStudentService externalStudentService;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository, ExternalStudentService externalStudentService) {
        this.vehicleRepository = vehicleRepository;
        this.externalStudentService = externalStudentService;
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {

        // Aquí va lo que te mencioné 👇
        var owner = externalStudentService.fetchStudentById(command.profileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        var vehicle = new Vehicle(command, owner);
        vehicleRepository.save(vehicle);
        return Optional.of(vehicle);
    }
}

/**
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

 */