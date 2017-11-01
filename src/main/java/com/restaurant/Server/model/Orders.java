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
    @Column(name = "orders_id")
    private int id;

    //@ManyToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name = "staff_id")
    @Column(name = "staff_id")
    private int staffId;

    @JoinColumn(name = "customer_id")
    @ManyToOne(cascade=CascadeType.MERGE)
    private Customer customer;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @Column(name = "date_of_order")
    private String dateOfOrder;

    @Column(name = "other_details")
    private String otherDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (customer != null ? !customer.equals(orders.customer) : orders.customer != null) return false;
        if (meal != null ? !meal.equals(orders.meal) : orders.meal != null) return false;
        if (dateOfOrder != null ? !dateOfOrder.equals(orders.dateOfOrder) : orders.dateOfOrder != null) return false;
        return otherDetails != null ? otherDetails.equals(orders.otherDetails) : orders.otherDetails == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (meal != null ? meal.hashCode() : 0);
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        result = 31 * result + (otherDetails != null ? otherDetails.hashCode() : 0);
        return result;
    }
}
