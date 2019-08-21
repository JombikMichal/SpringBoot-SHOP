package com.learn2code.Shop.db.mapper;

import com.learn2code.Shop.domain.BankAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRowMapper implements RowMapper<BankAccount> {

    @Override
    public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(rs.getInt("id"));
        bankAccount.setCustomerId(rs.getInt("customer_id"));
        bankAccount.setBankAccountNumber(rs.getString("bank_account_number"));
        bankAccount.setAccountBalance(rs.getDouble("account_balance"));
        bankAccount.setCreatedAt(rs.getTimestamp("created_at"));
        return bankAccount;
    }
}
