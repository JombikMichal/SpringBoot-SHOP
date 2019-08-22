package com.learn2code.Shop.db.repository;

import com.learn2code.Shop.db.mapper.BankAccountRowMapper;
import com.learn2code.Shop.db.mapper.BoughtProductRowMapper;
import com.learn2code.Shop.domain.BoughtProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoughtProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BoughtProductRowMapper boughtProductRowMapper = new BoughtProductRowMapper();

    public BoughtProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(BoughtProduct boughtProduct){
        final String sql = "insert into bought_product(product_id,customer_id,quantity,bought_at) values (?,?,?,?)";
        jdbcTemplate.update(sql,boughtProduct.getProduct_id(),boughtProduct.getCustomer_id(),boughtProduct.getQuantity(),boughtProduct.getBoughtAt());
    }


    public List<BoughtProduct> getAllByCustomerId(int customer_id){
        final String sql = "SELECT * FROM bought_product WHERE customer_id = " + customer_id;
        return jdbcTemplate.query(sql,boughtProductRowMapper);
    }
}
