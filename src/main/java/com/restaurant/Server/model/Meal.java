package com.restaurant.Server.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meal")
public class Meal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_id")
    private int mealId;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "is_available")
    @Builder.Default
    private boolean available = true;

    @Column(name = "price")
    private double price;

}
