
package com.stayEase.stayEase.Roles.RoleController;

import com.stayEase.stayEase.Roles.RoleRequestDto.RoleRequestDTO;
import com.stayEase.stayEase.Roles.RoleRequestDto.RoleAssignmentRequestDTO;
import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import com.stayEase.stayEase.Roles.RoleService.RoleService;
import com.stayEase.stayEase.UserMangement.UserEntity.UserEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(
            @RequestBody RoleRequestDTO roleRequestDTO) {

        RoleEntity createdRole = roleService.createRole(roleRequestDTO);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleEntity> getRoleById(
            @PathVariable Long roleId) {

        RoleEntity getRole = roleService.getRoleId(roleId);
        return ResponseEntity.ok(getRole);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignRole(
            @RequestBody RoleAssignmentRequestDTO requestDTO) {

        UserEntity updatedUser = roleService.assignRoleToUser(
                requestDTO.getEmail(),
                requestDTO.getRoleName()
        );

        return ResponseEntity.ok(
                "Role " + requestDTO.getRoleName()
                        + " assigned successfully to " + updatedUser.getEmail()
        );
    }
}
