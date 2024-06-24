package com.mruruc.repositoryImpl;

import com.mruruc.exception.ProductNotFoundException;
import com.mruruc.model.Product;
import com.mruruc.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements GenericRepository<Product, Long> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products (name,description,price,quantity,user_id) VALUES (?, ?, ?, ?,?) RETURNING product_id";
        return jdbcTemplate.queryForObject(
                sql, rowMapperForReturningSavedProduct,
                product.getName(), product.getDescription(),
                product.getPrice(),product.getQuantity(),product.getUserId()
        );
    }

    @Override
    public Optional<Product> findById(Long entityId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, entityId));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Long delete(Long entityId) {
        String sql = "DELETE FROM products WHERE product_id = ? RETURNING product_id";
        //  Long update = (long) jdbcTemplate.update(sql, entityId);
        try {
            return jdbcTemplate.queryForObject(sql, Long.class, entityId);
        } catch (EmptyResultDataAccessException exception) {
            throw new ProductNotFoundException("Product with id " + entityId + " not found");
        }
    }

    @Override
    public Product update(Long productId, Product product) {
        try {
            String sql = "UPDATE products SET user_id= ?, name = ?, description = ?, price = ? WHERE product_id = ? RETURNING *";

            return jdbcTemplate.queryForObject(
                    sql, rowMapper,
                    product.getUserId(), product.getName(),
                    product.getDescription(), product.getPrice(), productId);

        } catch (EmptyResultDataAccessException exception) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
    }

    RowMapper<Product> rowMapper = (resultSet, i) -> {
        Product product = new Product();
        product.setProductId(resultSet.getLong("product_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setUserId(resultSet.getLong("user_id"));
        return product;
    };
    RowMapper<Product> rowMapperForReturningSavedProduct = (resultSet, i) -> {
        Product product = new Product();
        product.setProductId(resultSet.getLong("product_id"));
        return product;
    };
}
