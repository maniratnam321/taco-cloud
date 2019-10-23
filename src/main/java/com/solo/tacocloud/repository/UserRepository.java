package com.solo.tacocloud.repository;

import com.solo.tacocloud.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

}
