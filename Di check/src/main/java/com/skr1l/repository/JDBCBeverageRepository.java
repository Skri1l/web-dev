package com.skr1l.repository;

import com.skr1l.exception.NotFoundException;
import com.skr1l.model.Beverage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JDBCBeverageRepository implements BeverageRepository {

    private final DataSource dataSource;

    public JDBCBeverageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Beverage> findAll() {
        String sql = "SELECT id, name, price FROM beverages";
        List<Beverage> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new Beverage(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch beverages", e);
        }
    }

    @Override
    public Optional<Beverage> findById(Long id) {
        String sql = "SELECT id, name, price FROM beverages WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Beverage(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price")
                    ));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find beverage by id", e);
        }
    }

    @Override
    public Beverage save(Beverage beverage) {
        String sql = "INSERT INTO beverages(name, price) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, beverage.getName());
            ps.setInt(2, beverage.getPrice());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return new Beverage(
                            keys.getLong(1),
                            beverage.getName(),
                            beverage.getPrice()
                    );
                }
                throw new SQLException("No generated key returned");

            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save beverage", e);
        }
    }

    @Override
    public Beverage update(Beverage beverage) {
        String sql = "UPDATE beverages SET name = ?, price = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, beverage.getName());
            ps.setInt(2, beverage.getPrice());
            ps.setLong(3, beverage.getId());

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                throw new NotFoundException(
                        "Beverage not found with id " + beverage.getId()
                );
            }

            return beverage;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update beverage", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM beverages WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete beverage", e);
        }
    }
}
