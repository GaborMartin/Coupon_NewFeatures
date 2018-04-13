package com.codecool.web.dao;

import com.codecool.web.model.Shop;

import java.sql.SQLException;
import java.util.List;

public interface ShopDao {

    List<Shop> findAll() throws SQLException;

    List<Shop> findAllByCouponId(int couponId) throws SQLException;

    List<Shop> findAllByCreatorId(int creatorId) throws SQLException;

    Shop findById(int id) throws SQLException;

    Shop add(String name, int creator_id) throws SQLException;
}
