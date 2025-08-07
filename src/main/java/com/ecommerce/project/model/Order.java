package com.ecommerce.project.model;

import jakarta.persistence.*;
        import lombok.*;

        import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private String status;  // e.g. "Pending", "Shipped", "Completed", "Cancelled"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // The user who placed the order

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
