package com.stayEase.stayEase.UserMangement.UserEntity;

import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity

@Setter
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;

    @Column (nullable = false , unique = true)
    private String email;
    private String phoneNo;
    private String password;

    @ManyToMany
    @JoinTable (
            name = "userRoles",
            joinColumns = @JoinColumn (name = "userId"),
            inverseJoinColumns = @JoinColumn (name = "roleId")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
