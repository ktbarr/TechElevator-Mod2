package com.techelevator.dao.jdbc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseJdbcDao 
{
    protected JdbcTemplate jdbcTemplate;

    public BaseJdbcDao(DataSource dataSource) 
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
