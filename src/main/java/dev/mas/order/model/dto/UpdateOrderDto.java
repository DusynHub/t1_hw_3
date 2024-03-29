package dev.mas.order.model.dto;

import dev.mas.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Order dto for update without validators
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateOrderDto {


    private String name;


    private String description;


    private OrderStatus status;


    private Long owner;
}
