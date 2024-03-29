package dev.mas.order.model.dto;

import dev.mas.order.model.OrderStatus;
import dev.mas.user.model.dto.UserDtoForOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Order dto for request response
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {


    private String name;

    private String description;

    private OrderStatus status;

    private UserDtoForOrder owner;
}
