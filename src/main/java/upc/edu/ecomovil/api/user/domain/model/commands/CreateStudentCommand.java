package upc.edu.ecomovil.api.user.domain.model.commands;

public record CreateStudentCommand(String firstName, String lastName, String email, String phoneNumber, String rucNumber, Long planId) {
}
