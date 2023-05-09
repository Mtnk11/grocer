package com.grocer.models.dto;

import com.grocer.models.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {
    private Integer id;
    private List<Integer> productIDs;
    private List<ProductDTO> products;
    private Integer userID;
    private UserDTO userDTO;
    private String email;
    private Integer number;
    private Integer qty;
    private Integer amount;
    private String token;
    private LocalDateTime timestamps;


    public static Order map(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .amount(orderDTO.getAmount())
                .email(orderDTO.getEmail())
                .number(orderDTO.getNumber())
                .qty(orderDTO.getQty())
                .timestamps(orderDTO.getTimestamps())
                .token(orderDTO.getToken())
                .build();
    }
    public static OrderDTO map(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .email(order.getEmail())
                .number(order.getNumber())
                .products(ProductDTO.mapToDto(order.getProducts()))
                .qty(order.getQty())
                .timestamps(order.getTimestamps())
                .token(order.getToken())
                .userDTO(UserDTO.map(order.getUser()))
                .build();
    }
}
