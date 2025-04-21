/**
 * Sign up command
 * <p>
 *     This class represents the command to sign up a user.
 * </p>
 * @param username the username of the user
 * @param password the password of the user
 * @param roles the roles of the user
 *
 * @see com.acme.center.platform.iam.domain.model.aggregates.User
 */
package upc.edu.ecomovil.api.iam.domain.model.commands;

import upc.edu.ecomovil.api.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, String email, List<Role> roles) {
}