package com.ecommerce.project.controller;

import com.ecommerce.project.model.OrderItem;
import com.ecommerce.project.repository.OrderItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin
public class OrderController {

    private final OrderItemRepository orderItemRepository;

    public OrderController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get order item by ID
    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    // Create new order item
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Update order item
    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id).map(orderItem -> {
            orderItem.setQuantity(updatedOrderItem.getQuantity());
            orderItem.setPrice(updatedOrderItem.getPrice());
            orderItem.setOrder(updatedOrderItem.getOrder());
            orderItem.setProduct(updatedOrderItem.getProduct());
            return orderItemRepository.save(orderItem);
        }).orElse(null);
    }

    // Delete order item
    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemRepository.deleteById(id);
    }
}
