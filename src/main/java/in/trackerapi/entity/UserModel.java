package in.trackerapi.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Email should not be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "password should not be empty")
    @Size(min = 5, message = "password should be at least 5 characters")
    private String password;

    private Long age = 0L;

}
