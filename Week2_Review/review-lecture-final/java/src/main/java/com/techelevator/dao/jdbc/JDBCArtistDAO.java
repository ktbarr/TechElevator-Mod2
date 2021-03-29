package com.techelevator.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.dao.ArtistDAO;
import com.techelevator.model.Artist;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCArtistDAO extends BaseJdbcDao implements ArtistDAO
{

    public JDBCArtistDAO(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Artist> getAll()
    {
        List<Artist> artists = new ArrayList<Artist>();
        
        String sql = "SELECT artist_id " 
                    + " , name " 
                    + "FROM artist;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
        
        while(rows.next())
        {
            Artist artist = mapRowToArtist(rows);
            artists.add(artist);
        }
        
        return artists;
    }

    @Override
    public Artist getById(long id)
    {
        Artist artist = null;
        
        String sql = "SELECT artist_id " 
                    + " , name " 
                    + "FROM artist"
                    + "WHERE artist_id = ?";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
        
        if(rows.next())
        {
            artist = mapRowToArtist(rows);
        }
        
        return artist;
    }

    @Override
    public void add(Artist artist)
    {
        // since artist_id is not an autonumber - there is no need to return anything
        // because we already know the artistID - we created it

        String sql = "INSERT INTO artist "
                    + "( "
                    + "     artist_id "
                    + "     , name "
                    + ")"
                    + "VALUES (?, ?);";

        jdbcTemplate.update(sql
                            , artist.getId()
                            , artist.getName());

    }

    @Override
    public void update(Artist artist)
    {
        String sql = "UPDATE artist "
                    + "SET name = ? "
                    + "WHERE artist_id = ?;";

        jdbcTemplate.update(sql
                            , artist.getName()
                            , artist.getId());

    }

    @Override
    public void delete(long id)
    {
        String sql = "DELETE FROM artist "
                    + "WHERE artist_id = ?;";

        jdbcTemplate.update(sql, id);
    }

    private Artist mapRowToArtist(SqlRowSet row)
    {
        Artist artist = new Artist();
        artist.setId(row.getLong("artist_id"));
        artist.setName(row.getString("name"));
        return artist;
    }
    
}
