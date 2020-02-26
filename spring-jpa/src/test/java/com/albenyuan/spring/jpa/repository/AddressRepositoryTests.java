package com.albenyuan.spring.jpa.repository;

import com.albenyuan.spring.jpa.SpringJpaApplicationTests;
import com.albenyuan.spring.jpa.entity.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alben Yuan
 * @Date 2019-04-29 11:44
 */
public class AddressRepositoryTests extends SpringJpaApplicationTests {

    @Autowired
    private AddressRepository addressRepository;


    @Test
    public void testSave() {
        List<Address> addresses = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Address address = new Address();
            address.setName("name" + i);
            addresses.add(address);
        }
        addressRepository.saveAll(addresses);

    }
}
