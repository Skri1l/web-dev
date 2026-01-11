package com.skr1l.repository;

public class JDBCBeverageRepository implements BeverageRepository {

    @Override
    public String getSourceName() {
        return "JDBCBeverageRepository";
    }
}
