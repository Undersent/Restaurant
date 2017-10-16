package com.restaurant.Server.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

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
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen", strategy="foreign",
            parameters = @Parameter(name="property", value ="order"))
    private int customerId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Orders order;
}
