package unit.capstone.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMemberDTO {


    @NotBlank(message = "이름 필수 입력")
    @Size(min = 2, max = 10, message = "Name length 2 ~ 10 !!!")
    private String name;

    @Email(message = "email 필수 입력")
    private String email;

    @NotBlank(message = "password 필수 입력")
    @Size(min = 8, max = 20, message = "Password length 8 ~ 20 !!!")
    private String password;



}
