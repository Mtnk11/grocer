package com.grocer.controllers;

import com.grocer.models.Order;
import com.grocer.models.dto.FileUpload;
import com.grocer.models.dto.OrderDTO;
import com.grocer.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> findAll() {
        return orderService.findAll()
                .stream()
                .map(OrderDTO::map)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable("id") Integer id) {
        Order byId = orderService.findById(id);
        return OrderDTO.map(byId);
    }
    @PostMapping
    public Integer save(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
        return orderDTO.getId();
    }
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        orderService.delete(id);
    }
}