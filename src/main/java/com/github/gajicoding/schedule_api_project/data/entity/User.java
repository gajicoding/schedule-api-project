package com.github.gajicoding.schedule_api_project.data.entity;

import com.github.gajicoding.schedule_api_project.data.dto.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Setter
    @Column
    private String password;

    public User() {
        // no-arg 생성자가 포함되어야 함
    }

    public User(Long id){
        this.id = id;
    }

    public void update(UserRequestDTO requestDTO) {
        this.name = requestDTO.getName();
        this.email = requestDTO.getEmail();
        this.password = requestDTO.getPassword();
    }
}
