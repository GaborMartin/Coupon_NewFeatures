package com.codecool.web.model;

import java.util.Objects;

public final class Shop extends AbstractModel {

    private final String name;
    private final int creatorId;

    public Shop(int id, String name, int creatorId) {
        super(id);
        this.name = name;
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public int getCreatorId() {
        return creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, creatorId);
    }
}
