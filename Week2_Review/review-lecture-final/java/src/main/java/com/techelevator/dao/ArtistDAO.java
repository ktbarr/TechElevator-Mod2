package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.*;

public interface ArtistDAO
{
    List<Artist> getAll();
    Artist getById(long id);
    void add(Artist artist); // artist_id is not an auto number - so we don't return anything
    void update(Artist artist);
    void delete(long id);
}
