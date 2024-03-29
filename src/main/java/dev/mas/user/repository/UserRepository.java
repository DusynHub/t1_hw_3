package dev.mas.user.repository;

import dev.mas.user.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllBy(PageRequest pageRequest);
}
