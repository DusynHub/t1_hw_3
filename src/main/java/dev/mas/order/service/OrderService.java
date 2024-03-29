package dev.mas.order.service;

import dev.mas.order.model.dto.NewOrderDto;
import dev.mas.order.model.dto.OrderDto;
import dev.mas.order.model.dto.UpdateOrderDto;

import java.util.List;

public interface OrderService {

    /**
     * Method to save new order
     *
     * @param newOrderDto new order
     * @return saved order in dto form
     */
    OrderDto saveOrder(NewOrderDto newOrderDto);

    /**
     * Method to update order by id and body
     *
     * @param id             order id to be updated
     * @param updateOrderDto dto with new field values
     * @return updated order in dto form
     */
    OrderDto updateOrder(long id, UpdateOrderDto updateOrderDto);

    /**
     * Method to delete order by id
     *
     * @param id order id to be deleted
     */
    void deleteOrder(long id);

    /**
     * Get all orders
     *
     * @param page required page
     * @param size
     * @return
     */
    List<OrderDto> getAllOrders(int page, int size);

    OrderDto getOrderById(long orderId);
}
