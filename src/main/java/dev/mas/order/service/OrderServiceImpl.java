package dev.mas.order.service;

import dev.mas.exception.ResourceNotFoundException;
import dev.mas.order.mapper.OrderMapper;
import dev.mas.order.model.Order;
import dev.mas.order.model.dto.NewOrderDto;
import dev.mas.order.model.dto.OrderDto;
import dev.mas.order.model.dto.UpdateOrderDto;
import dev.mas.order.repository.OrderRepository;
import dev.mas.user.model.User;
import dev.mas.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {


    private final UserService userService;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;


    @Override
    @Transactional
    public OrderDto saveOrder(NewOrderDto newOrderDto) {

        User userToSave = userService.getUserEntity(newOrderDto.getOwner());
        Order orderToSave = orderMapper.newToOrder(newOrderDto);
        orderToSave.setOwner(userToSave);
        return orderMapper.toOrderDto(orderRepository.save(orderToSave));
    }

    @Override
    @Transactional
    public OrderDto updateOrder(long id, UpdateOrderDto updateOrderDto) {

        Order orderToUpdate = getOrderByIdMandatory(id);
        orderMapper.updateOrderFields(updateOrderDto, orderToUpdate);
        if (updateOrderDto.getOwner() != null && updateOrderDto.getOwner() != orderToUpdate.getOwner().getId()) {
            User userUpdate = userService.getUserEntity(updateOrderDto.getOwner());
            orderToUpdate.setOwner(userUpdate);
        }

        return orderMapper.toOrderDto(orderRepository.save(orderToUpdate));

    }

    @Override
    @Transactional
    public void deleteOrder(long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    String.format("Order with id = '%d' not found and can't be deleted", id)
            );
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getAllOrders(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return orderRepository.findAllBy(pageRequest)
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(long orderId) {
        return orderMapper.toOrderDto(getOrderByIdMandatory(orderId));
    }

    private Order getOrderByIdMandatory(long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("Order with id = '%d' not found", id)
                )
        );
    }
}
