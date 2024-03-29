package dev.mas.order.mapper;

import dev.mas.order.model.Order;
import dev.mas.order.model.dto.NewOrderDto;
import dev.mas.order.model.dto.OrderDto;
import dev.mas.order.model.dto.UpdateOrderDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(Order order);

    @Mapping(target = "owner", ignore = true)
    Order newToOrder(NewOrderDto newOrderDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "owner", ignore = true)
    void updateOrderFields(UpdateOrderDto updateOrderDto,
                           @MappingTarget Order orderToBeUpdated);


}
