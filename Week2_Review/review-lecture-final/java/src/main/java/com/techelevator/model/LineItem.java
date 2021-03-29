package com.techelevator.model;

import java.math.BigDecimal;

public class LineItem 
{
    private Long saleId;
    private Long artId;
    private BigDecimal price;

    public Long getSaleId()
    {
        return saleId;
    }

    public void setSaleIdId(Long saleId)
    {
        this.saleId = saleId;
    }

    public Long getArtId()
    {
        return artId;
    }

    public void setArtId(Long artId)
    {
        this.artId = artId;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

       
}
