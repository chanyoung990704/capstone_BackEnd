package unit.capstone.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMemberDTO {

    @NotBlank(message = "이름은 필수적입니다.")
    @Size(min = 2, max = 10, message = "길이는 최소2자 최대 10자입니다.")
    private String name;

    @Email(message = "이메일은 필수적입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수적입니다.")
    @Size(min = 8, max = 20, message = "길이는 최소 8자 최대 20자입니다.")
    private String password;

}
