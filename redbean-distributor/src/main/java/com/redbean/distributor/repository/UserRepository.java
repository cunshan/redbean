package com.redbean.distributor.repository;

import com.redbean.distributor.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

  Page<User> findAllBy(User user, Pageable pageable);

}
