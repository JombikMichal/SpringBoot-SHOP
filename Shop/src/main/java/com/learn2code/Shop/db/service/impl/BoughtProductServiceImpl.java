package com.learn2code.Shop.db.service.impl;

import com.learn2code.Shop.db.repository.BoughtProductRepository;
import com.learn2code.Shop.db.service.api.BoughtProductService;
import com.learn2code.Shop.domain.BoughtProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoughtProductServiceImpl implements BoughtProductService {

    private BoughtProductRepository boughtProductRepository;

    public BoughtProductServiceImpl(BoughtProductRepository boughtProductRepository) {
        this.boughtProductRepository = boughtProductRepository;
    }

    @Override
    public void add(BoughtProduct boughtProduct) {
        boughtProductRepository.add(boughtProduct);
    }

    @Override
    public List<BoughtProduct> getAllByCustomerId(int customer_id) {
        return boughtProductRepository.getAllByCustomerId(customer_id);
    }
}
