package com.restaurant.Server.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "counter_orders")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "counter_id")
    private int id;

    @Column(name = "count")
    private int count;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "staff_id")
    private Staff staff;


}
