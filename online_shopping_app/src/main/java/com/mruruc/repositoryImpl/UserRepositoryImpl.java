package com.mruruc.repositoryImpl;

import com.mruruc.model.Address;
import com.mruruc.model.User;
import com.mruruc.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements GenericRepository<User, Long> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    };

    RowMapper<User> rowMapperForMethodGetUserByUsername = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    };


    @Override
    public User save(User user) {
        String sql = "INSERT INTO users(username,password,first_name,last_name,phone_number) VALUES(?,?,?,?,?) RETURNING *";
        return jdbcTemplate.queryForObject(
                sql, rowMapper,
                user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber()
        );
    }

    public boolean addAddress(Address address){
        String sql = "UPDATE users SET street = ?,city = ?,state = ?,zipcode = ?,country = ? WHERE user_id = ?";
        return jdbcTemplate.update(
                sql,
                address.getStreet(),address.getCity(),address.getState(),
                address.getZip(),address.getCountry(),address.getUserId()
        ) == 1;
    }


    public Optional<User> findUserByUsername(String username) {
        String sql = "SELECT user_id, username , password FROM users WHERE username LIKE ? ";
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql,rowMapperForMethodGetUserByUsername , username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long delete(Long entityId) {
        return 0L;
    }

    @Override
    public User update(Long aLong, User user) {
        return null;
    }

    @Override
    public Optional<User> findById(Long entityId) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
