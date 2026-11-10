package com.stayEase.stayEase.OwnerManagement.OwnerEntity;

import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import com.stayEase.stayEase.SpringSecurity.SpringSecurityEnums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class OwnerEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;

    @Column (unique = true , nullable = false)
    private String email;

    @Column (unique = true , nullable = false)
    private String phoneNo;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany (mappedBy = "ownerEntity")
    List<HotelEntity> hotelEntity = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


}
