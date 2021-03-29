package com.techelevator.auctions.controller;

import com.techelevator.auctions.DAO.AuctionDAO;
import com.techelevator.auctions.DAO.MemoryAuctionDAO;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDAO dao;

    public AuctionController()
    {
        this.dao = new MemoryAuctionDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(required = false) String title_like)
    {
    	if(title_like == null)
    	{
    		return dao.list();
    	}
    	else 
    	{
			return dao.searchByTitle(title_like);
		}
    }

}
