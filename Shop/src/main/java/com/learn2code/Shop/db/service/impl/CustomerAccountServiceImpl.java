package com.learn2code.Shop.db.service.impl;

import com.learn2code.Shop.db.repository.CustomerAccountRepository;
import com.learn2code.Shop.db.service.api.CustomerAccountService;
import com.learn2code.Shop.db.service.api.CustomerService;
import com.learn2code.Shop.domain.CustomerAccount;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountServiceImpl(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }
    @Override
    public void addCustomerAccount(CustomerAccount customerAccount){
        customerAccountRepository.add(customerAccount);
    }

    @Override
    public Double getMoney(int customer_id){
        return customerAccountRepository.getMoney(customer_id);
    }

    @Override
    public void setMoney(int customer_id, double money){
        customerAccountRepository.setMoney(customer_id,money);
    }

}
