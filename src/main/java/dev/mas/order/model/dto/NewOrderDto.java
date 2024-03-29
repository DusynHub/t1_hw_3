package dev.mas.order.model.dto;

import dev.mas.order.model.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Order dto foe order creating with validators
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewOrderDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Long owner;
}
