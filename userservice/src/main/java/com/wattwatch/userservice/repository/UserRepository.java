package com.wattwatch.userservice.repository;

import org.springframework.stereotype.Repository;
import com.wattwatch.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //this repo interface is used to connect to the db using jpa 
}

