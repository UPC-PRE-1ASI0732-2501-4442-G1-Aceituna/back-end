package upc.edu.ecomovil.api.user.interfase.rest.resources.student;

public record CreateStudentResource(String firstName, String lastName, String email,String phoneNumber, String ruc, Long planId) {
}
