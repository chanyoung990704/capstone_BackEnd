package unit.capstone.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMemberDTO {


    @NotBlank(message = "이름 필수 입력")
    @Size(min = 2, max = 10, message = "이름 2자 이상 10자 이하")
    private String name;

    @Email(message = "email 필수 입력")
    private String email;

    @NotBlank(message = "password 필수 입력")
    @Size(min = 8, max = 20, message = "비밀번호 8자 이상 20자 이하")
    private String password;



}
