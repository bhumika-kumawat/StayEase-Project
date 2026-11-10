package com.stayEase.stayEase.Roles.RoleController;

import com.stayEase.stayEase.Roles.RoleRequestDto.RoleRequestDTO;
import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import com.stayEase.stayEase.Roles.RoleService.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {


    RoleService roleService;
    RoleController (RoleService roleService)
    {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleEntity>createRole(@RequestBody RoleRequestDTO  roleRequestDTO)
    {

        RoleEntity createdRole = roleService.createRole(roleRequestDTO);
        return ResponseEntity.ok(createdRole);

    }


}