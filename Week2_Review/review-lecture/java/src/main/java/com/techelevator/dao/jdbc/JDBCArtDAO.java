package com.techelevator.dao.jdbc;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCArtDAO extends BaseJdbcDao implements ArtDAO 
{
    public JDBCArtDAO(DataSource dataSource) 
    {
        super(dataSource);
    }

    @Override
    public List<Art> getAll()
    {
        List<Art> artList = new ArrayList<Art>();

        String sql = "SELECT art_id "
                    + " , artist_id "
                    + " , title "
                    + "FROM art;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while(rows.next())
        {
            Art art = mapRowToArt(rows);

            artList.add(art);
        }
        
        return artList;
    }

    @Override
    public List<Art> getByArtist(Artist artist)
    {
        List<Art> artList = new ArrayList<Art>();

        String sql = "SELECT art_id "
                    + " , artist_id "
                    + " , title "
                    + "FROM art "
                    + "WHERE artist_id = ? ";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, artist.getId());

        while(rows.next())
        {
            Art art = mapRowToArt(rows);

            artList.add(art);
        }
        
        return artList;
    }

    @Override
    public Art getById(long id)
    {
        Art art = null;

        String sql = "SELECT art_id "
                    + " , artist_id "
                    + " , title "
                    + "FROM art "
                    + "WHERE art_id = ? ";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);

        if(rows.next())
        {
            art = mapRowToArt(rows);
        }
        
        return art;
    }

    @Override
    public Art add(Art art)
    {
        long newId = getNextArtId();
        art.setId(newId);

        String sql = "INSERT INTO art "
                    + "( "
                    + "     art_id "
                    + "     , artist_id "
                    + "     , title "
                    + ")"
                    + "VALUES (?, ?, ?);";
        

        jdbcTemplate.update(sql
                            , art.getId()
                            , art.getArtistId()
                            , art.getTitle());

        return art;
    }

    @Override
    public void update(Art art)
    {
        String sql = "UPDATE art "
                    + "SET artist_id = ? "
                    + "     , title = ?"
                    + "WHERE art_id = ?;";
        

        jdbcTemplate.update(sql
                            , art.getArtistId()
                            , art.getTitle()
                            , art.getId());

    }

    @Override
    public void delete(long id)
    {
        String sql = "DELETE FROM art "
                    + "WHERE art_id = ?;";        

        jdbcTemplate.update(sql, id);
    }

    private long getNextArtId() 
    {
        String sql = "SELECT nextval('art_art_id_seq') AS id";
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet(sql);
        
        if(nextIdResult.next()) 
        {
			return nextIdResult.getLong("id");
        } 
        else 
        {
			throw new RuntimeException("Something went wrong while getting an id for the new art");
		}
	}
    

    private Art mapRowToArt(SqlRowSet row) {
        Art art = new Art();
        art.setId(row.getLong("art_id"));
        art.setArtistId(row.getLong("artist_id"));
        art.setTitle(row.getString("title"));
        return art;
    }
}
