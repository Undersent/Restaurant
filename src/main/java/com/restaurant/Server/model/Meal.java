package com.restaurant.Server.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_id")
    private int meal_id;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "is_available")
    private boolean isAvailable = true;
}
