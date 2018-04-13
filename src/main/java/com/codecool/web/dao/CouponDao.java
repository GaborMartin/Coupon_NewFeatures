package com.codecool.web.dao;

import com.codecool.web.model.Coupon;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.sql.SQLPermission;
import java.util.List;

public interface CouponDao {

    List<Coupon> findAll() throws SQLException;

    Coupon findById(int id) throws SQLException;

    List<Coupon> findAllByCreatorId(int id) throws SQLException;

    List<Coupon> findCouponsForShopByCreatorId(int shopId, int creatorId) throws SQLException;

    Coupon add(String name, int percentage, int creator_id) throws SQLException;

    void add(int couponId, int... shopIds) throws SQLException;
}
