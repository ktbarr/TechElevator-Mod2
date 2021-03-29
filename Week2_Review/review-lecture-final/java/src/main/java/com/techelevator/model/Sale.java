package com.techelevator.model;

import java.time.LocalDate;

public class Sale 
{
    private long saleId;
    private long customerId;
    private LocalDate purchaseDate;

    public long getSaleId()
    {
        return saleId;
    }

    public void setSaleId(long saleId)
    {
        this.saleId = saleId;
    }

    public long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate()
    {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }    
    
}
