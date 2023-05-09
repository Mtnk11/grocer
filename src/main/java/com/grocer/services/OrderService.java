package com.grocer.services;

import com.grocer.models.Product;
import com.grocer.models.User;
import com.grocer.models.dto.FileUpload;
import com.grocer.models.dto.OrderDTO;
import com.grocer.models.Order;
import com.grocer.models.dto.ProductDTO;
import com.grocer.models.dto.UserDTO;
import com.grocer.repositories.OrderRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id '" + id + "' not found"));
    }
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = OrderDTO.map(orderDTO);
        User user = userService.findById(orderDTO.getUserID());
        List<Product> products = productService.findAllByIDs(orderDTO.getProductIDs());
        order.setProducts(products);
        order.setUser(user);
        orderRepository.save(order);
        return OrderDTO.map(order);
    }
    public OrderDTO update(int id, OrderDTO orderDTO) {
        Order orderToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with number '" + orderDTO.getNumber() + "' is not found"));

        orderToUpdate.setAmount(orderDTO.getAmount());
        orderToUpdate.setEmail(orderDTO.getEmail());
        orderToUpdate.setNumber(orderDTO.getNumber());
        orderToUpdate.setQty(orderDTO.getQty());
        if (orderDTO.getUserID()!=null) {
            User user = userService.findById(orderDTO.getUserID());
            orderToUpdate.setUser(user);
        }
        if (orderDTO.getProductIDs()!=null && !orderDTO.getProductIDs().isEmpty()) {
            List<Product> products = productService.findAllByIDs(orderDTO.getProductIDs());
            orderToUpdate.setProducts(products);
        }
        orderToUpdate.setTimestamps(LocalDateTime.now());
        orderRepository.save(orderToUpdate);
        return OrderDTO.map(orderToUpdate);

    }

    public void delete(int id) {
        Order orderToDelete = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with " + id + " not found"));
        orderRepository.delete(orderToDelete);
    }
}