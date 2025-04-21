package upc.edu.ecomovil.api.user.domain.model.commands;

import upc.edu.ecomovil.api.iam.domain.model.aggregates.User;

public record CreateStudentCommand(User user,String firstName, String lastName, String email, String phoneNumber, String rucNumber, Long planId) {
}
