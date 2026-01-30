package com.skr1l.repository;

import com.skr1l.exception.NotFoundException;
import com.skr1l.model.Beverage;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbctemplate")
public class JdbcTemplateBeverageRepository implements BeverageRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateBeverageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Beverage> beverageRowMapper = (rs, rowNum) ->
            new Beverage(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price")
            );

    @Override
    public List<Beverage> findAll() {
        String sql = "SELECT id, name, price FROM beverages";
        return jdbcTemplate.query(sql, beverageRowMapper);
    }

    @Override
    public Optional<Beverage> findById(Long id) {
        String sql = "SELECT id, name, price FROM beverages WHERE id = ?";
        return jdbcTemplate.query(sql, beverageRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public Beverage save(Beverage beverage) {
        String sql = "INSERT INTO beverages(name, price) VALUES (?, ?) RETURNING id";

        Long id = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                beverage.getName(),
                beverage.getPrice()
        );

        return new Beverage(id, beverage.getName(), beverage.getPrice());
    }

    @Override
    public Beverage update(Beverage beverage) {
        String sql = "UPDATE beverages SET name = ?, price = ? WHERE id = ?";

        int updatedRows = jdbcTemplate.update(
                sql,
                beverage.getName(),
                beverage.getPrice(),
                beverage.getId()
        );

        if (updatedRows == 0) {
            throw new NotFoundException(
                    "Beverage not found with id " + beverage.getId()
            );
        }

        return beverage;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM beverages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
