package com.learn2code.Shop.db.mapper;

import com.learn2code.Shop.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    //tomuto nerozumiem - zakomentovany kod som pisal ja...resp spolu s tutorialom a nesiel, aky je rozdiel medzi tymito castami kodu? padalo mi to na teste lebo objekty sa nerovnali
    // uz tomu rozumiem...moje hlupost - staci jednu property zle settnut
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setSurname(rs.getString("surname"));
        customer.setEmail(rs.getString("email"));
        customer.setAddress(rs.getString("address"));
        customer.setAge(rs.getObject("age") == null ? null : rs.getInt("age"));
        customer.setPhoneNumber(rs.getString("phone_number"));
        return customer;
    }

}
