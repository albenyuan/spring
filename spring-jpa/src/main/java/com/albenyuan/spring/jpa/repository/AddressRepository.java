package com.albenyuan.spring.jpa.repository;

import com.albenyuan.spring.jpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Alben Yuan
 * @Date 2019-04-09 08:44
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    

}
