package fr.filrougeback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserForm {
    private String currentEmail;
    private String newUsername;
    private String newPassword;
    private String newEmail;
}
