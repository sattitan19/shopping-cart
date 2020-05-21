package com.tak.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tak.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long>{

}
