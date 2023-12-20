package unit.capstone.controller.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String username;
    private String email;

    public MemberDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
