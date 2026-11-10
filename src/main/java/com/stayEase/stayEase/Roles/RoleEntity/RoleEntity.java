package com.stayEase.stayEase.Roles.RoleEntity;

import com.stayEase.stayEase.SpringSecurity.SpringSecurityEnums.UserRole;
import com.stayEase.stayEase.UserMangement.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRole roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();






}
