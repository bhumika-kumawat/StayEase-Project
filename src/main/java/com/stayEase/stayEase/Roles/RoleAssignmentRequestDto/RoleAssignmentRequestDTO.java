
package com.stayEase.stayEase.Roles.RoleRequestDto;

import com.stayEase.stayEase.SpringSecurity.SpringSecurityEnums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleAssignmentRequestDTO {

    private String email;

    private UserRole roleName;
}
