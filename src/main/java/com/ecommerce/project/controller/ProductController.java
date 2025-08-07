package com.ecommerce.project.controller;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }
    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        return repo.save(p);
    }
    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        p.setId(id);
        return repo.save(p);
    }
}
