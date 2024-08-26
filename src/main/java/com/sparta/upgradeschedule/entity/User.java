package com.sparta.upgradeschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,name = "user_create_date")
    private LocalDateTime userCreateDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_update_date")
    private LocalDateTime userUpdateDate;

    @OneToMany(mappedBy = "user")
    private List<Pic> picList = new ArrayList<>();

    public User(String userName, String email) {
        this.userName=userName;
        this.email=email;
    }

    public void update(String userName,String email) {
        this.userName=userName;
        this.email=email;
    }
}
