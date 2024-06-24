package com.mruruc.repositoryImpl;

import com.mruruc.model.Product;
import com.mruruc.model.ShoppingCart;
import com.mruruc.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ShoppingCartRepositoryImpl  {
    private JdbcTemplate jdbcTemplate;
    private ProductRepositoryImpl productRepository;

    @Autowired
    public ShoppingCartRepositoryImpl(JdbcTemplate jdbcTemplate, ProductRepositoryImpl productRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
    }

    RowMapper<ShoppingCart> shoppingCartRowMapper = (rs, rowNum) -> {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(rs.getLong(1));
        shoppingCart.setUserId(rs.getLong(2));
        shoppingCart.setProductId(rs.getLong(3));
        shoppingCart.setQuantity(rs.getInt(4));
        return shoppingCart;
    };


    public Long save(ShoppingCart shoppingCart) {
        String sql = " INSERT INTO shopping_cart (user_id , product_id) VALUES(?,?) RETURNING user_id ";
         //jdbcTemplate.update(sql,shoppingCart.getUserId(),shoppingCart.getProductId());
        return jdbcTemplate.queryForObject(sql, Long.class, shoppingCart.getUserId(), shoppingCart.getProductId());
    }

    public List<Product> getAllProductInShoppingCartByUserId(Long userId) {
        String sql = """
                    SELECT p.product_id, p.name, p.description, p.price, p.quantity, p.user_id
                    FROM shopping_cart sc
                    JOIN products p ON sc.product_id = p.product_id
                    WHERE sc.user_id = ?
                """;
        return jdbcTemplate.query(sql,productRepository.rowMapper,userId);
    }
}
