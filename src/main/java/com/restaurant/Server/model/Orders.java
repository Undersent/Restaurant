package com.restaurant.Server.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orders_id", nullable = false, unique = true)
    private int id;

    @Column(name = "staff_id", length = 20, nullable = false)
    private int staffId;

    @OneToOne(mappedBy = "order")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer;

    @Column(name = "customer_id", length = 20, nullable = false)
    private String dateOfOrder;

    @Column(name = "other_details")
    private String otherDetails;
}
