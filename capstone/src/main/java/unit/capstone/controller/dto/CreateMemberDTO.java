package unit.capstone.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMemberDTO {

    @NotBlank(message = "{name.required}")
    @Size(min = 2, max = 10, message = "{name.size}")
    private String name;

    @Email(message = "{email.required}")
    private String email;

    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 20, message = "{password.size}")
    private String password;

}
