package com.rejj.ecommerce.repository;

import com.rejj.ecommerce.model.CartDetail;
import com.rejj.ecommerce.model.CartDetailID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailID> {

}
