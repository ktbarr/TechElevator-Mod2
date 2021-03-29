package com.techelevator.dao;

import com.techelevator.model.*;

import java.time.LocalDate;
import java.util.List;

public interface SaleDAO
{

	List<Sale> searchByDateRange(LocalDate startDate, LocalDate endDate);
}
