package com.techelevator.dao.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.dao.SaleDAO;
import com.techelevator.model.Sale;

public class JDBCSaleDAO extends BaseJdbcDao implements SaleDAO
{

	public JDBCSaleDAO(DataSource dataSource)
	{
		super(dataSource);
	}

	@Override
	public List<Sale> searchByDateRange(LocalDate startDate, LocalDate endDate)
	{
		List<Sale> sales = new ArrayList<Sale>();
		
		String sql = "SELECT sale_id "
				+ "	, customer_id "
				+ " , purchase_date "
				+ "FROM sale "
				+ "WHERE purchase_date BETWEEN ? AND ?;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, startDate, endDate);
		
		while(rows.next())
		{
			Sale sale = new Sale();
			sale.setSaleId(rows.getLong("sale_id"));
			sale.setCustomerId(rows.getLong("customer_id"));
			sale.setPurchaseDate(rows.getDate("purchase_date").toLocalDate());
			
			sales.add(sale);
		}
		
		return sales;
		
	}

}
