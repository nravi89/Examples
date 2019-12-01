package com.fod89.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fod89.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
