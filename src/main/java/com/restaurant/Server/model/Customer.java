package com.restaurant.Server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @OneToMany(mappedBy = "customer",
            cascade=CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private List<Orders> order;
}
