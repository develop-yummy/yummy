package com.six.yummy.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor
//@SQLDelete(sql = "UPDATE User SET user.deleted_at = true WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column
    private LocalDateTime deletedAt = null;

    public User(String username, String password, String email, String phoneNumber,
        UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // 업데이트하고 싶지 않은 값들이 들어왔을 때도 가능하게
    public void updateUser(String username, String email, String phoneNumber) {
        if (username != null) {
            this.username = username;
        }
        if (email != null) {
            this.email = email;
        }
        if (email != null) {
            this.phoneNumber = phoneNumber;
        }

    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
