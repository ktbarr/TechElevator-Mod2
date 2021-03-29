package com.techelevator.dao.jdbc;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.dao.SaleDAO;
import com.techelevator.model.Sale;

public class JDBCSaleDAOTests extends BaseDAOTests
{

	SaleDAO dao;
	
	@Before
	public void setUp() throws Exception
	{
		dao = new JDBCSaleDAO(dataSource);
	}

	@Test
	public void searchByDateRange_should_return_2_rows_for_2000()
	{
		// arrange
		int expectedCount = 2;
		LocalDate startDate = LocalDate.parse("2000-01-01");
		LocalDate endDate = LocalDate.of(2000, 12, 31);
		
		// act
		List<Sale> actualSales = dao.searchByDateRange(startDate, endDate);
		
		// assert
		assertEquals(expectedCount, actualSales.size());
	}

}
