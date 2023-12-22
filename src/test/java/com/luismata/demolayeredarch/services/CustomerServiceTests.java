package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Customer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void givenValidCustomerId_whenCallingGetCustomer_thenGetsCustomer(int customerId) {
        // given

        //when
        Customer customer = customerService.getCustomerById(customerId);

        //then
        assertNotNull(customer);
        assertEquals(customer.getCustomerId(), customerId);
    }
}
