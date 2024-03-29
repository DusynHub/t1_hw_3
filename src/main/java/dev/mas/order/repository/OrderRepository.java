package dev.mas.order.repository;

import dev.mas.order.model.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllBy(PageRequest pageRequest);
}
