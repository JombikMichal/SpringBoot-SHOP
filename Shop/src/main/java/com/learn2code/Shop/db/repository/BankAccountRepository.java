package com.learn2code.Shop.db.repository;

import com.learn2code.Shop.db.mapper.BankAccountRowMapper;
import com.learn2code.Shop.db.service.api.request.UpdateBankAccount;
import com.learn2code.Shop.domain.BankAccount;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

//Obecná anotace, ze které ostatní anotace dědí.
@Component
public class BankAccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BankAccountRowMapper bankAccountRowMapper = new BankAccountRowMapper();

    public BankAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BankAccount get(int id){
        final String sql = "SELECT * FROM bank_account WHERE id=" +id;
        try {
            return jdbcTemplate.queryForObject(sql,bankAccountRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Integer add(BankAccount bankAccount){
        final String sql = "insert into bank_account(customer_id,bank_account_number,account_balance,created_at) values (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1,bankAccount.getCustomerId());
                ps.setString(2,bankAccount.getBankAccountNumber());
                ps.setDouble(3,bankAccount.getAccountBalance());
                if (bankAccount.getCreatedAt() == null) {
                    bankAccount.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(4, bankAccount.getCreatedAt());

                return ps;
            }
        }, keyHolder);
        if(keyHolder != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }

    public List<BankAccount> getAll(){
        final String sql = "SELECT * FROM bank_account";
        return jdbcTemplate.query(sql,bankAccountRowMapper);
    }

    public void update(int id, UpdateBankAccount request) {
        final String sql = "update bank_account set account_balance = ?  where id = ?";
        jdbcTemplate.update(sql, request.getAccount_balance(), id);
    }

}
