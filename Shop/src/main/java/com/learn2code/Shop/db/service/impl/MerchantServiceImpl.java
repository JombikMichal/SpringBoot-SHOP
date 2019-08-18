package com.learn2code.Shop.db.service.impl;

import com.learn2code.Shop.db.repository.MerchantReposiroty;
import com.learn2code.Shop.db.service.api.MerchantService;
import com.learn2code.Shop.domain.Merchant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    final MerchantReposiroty merchantReposiroty;

    public MerchantServiceImpl(MerchantReposiroty merchantReposiroty) {
        this.merchantReposiroty = merchantReposiroty;
    }

    @Override
    public List<Merchant> getMerchants() {
        return merchantReposiroty.getAll();
    }

    @Override
    public Merchant get(int id) {
        return merchantReposiroty.get(id);
    }

    @Override
    public Integer add(Merchant merchant) {
        return merchantReposiroty.add(merchant);
    }
}
