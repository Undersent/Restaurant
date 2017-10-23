package com.restaurant.Server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @JsonIgnore
    @Column(name = "pesel")
    private String pesel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "staff_role", joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "staff", cascade=CascadeType.ALL)
    private List<Orders> orders;

    @Column(name = "is_available")
    @Builder.Default
    private boolean isAvailable = true;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "enabled")
    private boolean enabled;
}
