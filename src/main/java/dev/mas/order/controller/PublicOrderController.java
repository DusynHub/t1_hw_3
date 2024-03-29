package dev.mas.order.controller;

import dev.mas.order.model.dto.NewOrderDto;
import dev.mas.order.model.dto.OrderDto;
import dev.mas.order.model.dto.UpdateOrderDto;
import dev.mas.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
@Validated
public class PublicOrderController {

    private final OrderService orderService;

    @Operation(summary = "Save new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved new order",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto postOrder(@RequestBody @Valid NewOrderDto newOrderDto) {
        return orderService.saveOrder(newOrderDto);
    }

    @Operation(summary = "Get all orders by page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with orders has been returned",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))})
    })
    @GetMapping
    List<OrderDto> getAllOrders(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @PositiveOrZero int size) {
        return orderService.getAllOrders(page, size);
    }

    @Operation(summary = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Required order",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping("/{orderId}")
    OrderDto getOrder(@PathVariable @Positive long orderId) {
        return orderService.getOrderById(orderId);
    }

    @Operation(summary = "Delete order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable @Positive long orderId) {
        orderService.deleteOrder(orderId);
    }

    @Operation(summary = "Update order by id and required fields in body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto putOrder(
            @PathVariable @Positive long orderId,
            @RequestBody UpdateOrderDto updateOrder) {
        return orderService.updateOrder(orderId, updateOrder);
    }
}
