package com.codecool.web.dao.database;

import com.codecool.web.dao.ShopDao;
import com.codecool.web.model.Coupon;
import com.codecool.web.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseShopDao extends AbstractDao implements ShopDao {

    public DatabaseShopDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Shop> findAll() throws SQLException {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT id, name, creator_id FROM shops";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                shops.add(fetchShop(resultSet));
            }
        }
        return shops;
    }

    @Override
    public List<Shop> findAllByCouponId(int couponId) throws SQLException {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.creator_id " +
            "FROM shops AS s " +
            "JOIN coupons_shops AS cs ON s.id = cs.shop_id " +
            "JOIN coupons AS c ON c.id = cs.coupon_id " +
            "WHERE c.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, couponId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    shops.add(fetchShop(resultSet));
                }
            }
        }
        return shops;
    }

    @Override
    public List<Shop> findAllByCreatorId(int creatorId) throws SQLException {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT * FROM shops " +
            "JOIN users ON shops.creator_id = users.id " +
            "WHERE shops.creator_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, creatorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    shops.add(fetchShop(resultSet));
                }
            }
        }
        return null;
    }

    @Override
    public Shop findById(int id) throws SQLException {
        String sql = "SELECT id, name, creator_id FROM shops WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchShop(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Shop add(String name, int creator_id) throws SQLException {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO shops (name, creator_id) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setInt(2, creator_id);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            return new Shop(id, name, creator_id);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private Shop fetchShop(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int creator_id = resultSet.getInt("creator_id");
        return new Shop(id, name, creator_id);
    }
}
