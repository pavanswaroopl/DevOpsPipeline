package com.example.devopsPipeline.repository;

import org.springframework.stereotype.Repository;

import com.example.devopsPipeline.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addCustomer( Customer customer) {
        String sql = "INSERT INTO customer (firstName, lastName, email, loginId, password, contactNumber) VALUES (?, ?,?,?,?,?)";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getLoginId(), customer.getPassword(), customer.getContactNumber() );
    }

    @SuppressWarnings("null")
    public int findByLoginId(String loginId) {
        String sql = "Select count(*) from  customer where loginId = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, loginId);
      
    }

    public List<Customer> getAllCustomers() {
        String sql = "Select * from  customer";


        return jdbcTemplate.query(sql, new RowMapper<Customer>() {

            @Override
            @Nullable
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Customer c = new Customer();
                    c.setFirstName(rs.getString("firstName"));
                    c.setLastName(rs.getString("lastName"));
                    c.setEmail(rs.getString("email"));
                    c.setLoginId(rs.getString("loginId"));
                    c.setPassword(rs.getString("password"));
                    c.setContactNumber(rs.getString("contactNumber"));
                    return c;
            }
            
        });
      
    }

   



}
