package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.*;

public interface ArtDAO 
{

    List<Art> getAll();
    List<Art> getByArtist(Artist artist);
    List<Art> searchByTitle(String title);
    Art getById(long id);
    Art add(Art art);
    void update(Art art);
    void delete(long id);
    
}
