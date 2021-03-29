package com.techelevator.dao.jdbc;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JDBCArtDAOTests extends BaseDAOTests {

    private ArtDAO dao;

    @Before
    public void setup() {
        dao = new JDBCArtDAO(dataSource);
    }

    @Test
    public void getAll_should_return_three_records() 
    {
        //arrange
        int expectedCount = 3;

        //act
        List<Art> actualList = dao.getAll();

        //assert
        assertEquals("Because the default script only inserts 3 pieces of art",expectedCount, actualList.size());
    }
    

    @Test
    public void getByArtist_should_return_2_records_for_carol_channing() 
    {
        //arrange
        Artist testArtist = new Artist();
        testArtist.setId(3);
        testArtist.setName("Carol Channing");
        int expectedCount = 2;

        //act
        List<Art> actualList = dao.getByArtist(testArtist);

        //assert
        assertEquals("Because Carol Channing should only have 2 piecs of art.",expectedCount, actualList.size());
    }
    

    @Test
    public void add_should_insert_new_art_into_the_database_and_return_new_id() 
    {
        //arrange
    	String expectedTitle = "Swimming with Fish";
    	Art testArt = new Art();
    	testArt.setArtistId(3L);
    	testArt.setTitle(expectedTitle);        

        //act
        Art newArt = dao.add(testArt); // testing the insert   

        //assert
        Art actualArt = dao.getById(newArt.getId());
        assertNotNull("Because the database should now have the new record", actualArt);
        assertEquals("Because the new title should be returned from the database", expectedTitle, actualArt.getTitle());
    }
    

    @Test
    public void update_should_insert_new_art_into_the_database_and_return_new_id() 
    {
        //arrange
    	String expectedTitle = "Swimming with Fish";
    	Art testArt = new Art();
    	testArt.setArtistId(3L);
    	testArt.setTitle("some weird title"); 
    	
        Art newArt = dao.add(testArt); // insert the art to the database
        
        newArt.setTitle(expectedTitle);// change the title

        //act    
        dao.update(testArt); // testing the update

        //assert
        Art actualArt = dao.getById(newArt.getId());
        assertNotNull("Because the database should now have the new record", actualArt);
        assertEquals("Because the new title should be returned from the database", expectedTitle, actualArt.getTitle());
    }
    

    @Test
    public void delete_should_remove_the_art_from_the_database() 
    {
        //arrange
    	Art testArt = new Art();
    	testArt.setArtistId(3L);
    	testArt.setTitle("throw away art");  
    	Art newArt = dao.add(testArt);      

        //act
        dao.delete(newArt.getId()); // testing the delete      
        
        //assert
        Art actualArt = dao.getById(newArt.getId());
        assertNull("Because the database should not have record of the art anymnore", actualArt);
    }

}
