package com.techelevator.vendingmachine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.vendingmachine.models.security.Role;
import com.techelevator.vendingmachine.models.security.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSqlDAO implements UserDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleDAO roleDao;

    @Override
    public int findIdByUsername(String username)
    {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

    @Override
    public User getUserById(Long userId)
    {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next())
        {
            return mapRowToUser(results);
        } else
        {
            throw new RuntimeException("userId " + userId + " was not found.");
        }
    }

    @Override
    public List<User> findAll()
    {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id " 
                + "     , username " 
                + "     , password_hash " 
                + "     , balance " 
                + "FROM users; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next())
        {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username)
    {
        User user = null;

        String sql = "SELECT user_id " 
                + "     , username " 
                + "     , password_hash "
                + "     , balance "
                + "FROM users "
                + "WHERE username = ?; ";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, username);

        if(row.next())
        {
            user = mapRowToUser(row);
        }

        return user;
    }

    @Override
    public boolean create(String username, String password)
    {
        int userCreated;

        // create user
        String sql = "INSERT INTO users (user_id, username,password_hash, balance) VALUES (?,?,?,0)";
        String passwordHash = new BCryptPasswordEncoder().encode(password);
        int userId = getNextUserId();

        userCreated = jdbcTemplate.update(sql, userId, username, passwordHash);

        roleDao.addUserToRole(userId, 1); // default to general user

        return userCreated == 1;
    }

    private int getNextUserId()
    {
        String sql = "SELECT nextval('users_user_id_seq') AS id;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        if (row.next())
        {
            return row.getInt("id");
        }

        throw new RuntimeException("UserId could not be generated.");
    }

    private User mapRowToUser(SqlRowSet row)
    {
        User user = new User();
        long userId = row.getLong("user_id");
        String userName = row.getString("username");
        String passwordHash = row.getString("password_hash");
        BigDecimal balance = row.getBigDecimal("balance");
        String rolesString = "";

        List<Role> roles = roleDao.getRolesByUserId((int) userId);
        for (Role role : roles)
        {
            if (rolesString.length() > 0)
            {
                rolesString += ",";
            }

            rolesString += role.getRoleName().toUpperCase();
        }

        user.setId(userId);
        user.setUsername(userName);
        user.setPassword(passwordHash);
        user.setActivated(true);
        user.setBalance(balance);
        user.setAuthorities(rolesString);

        return user;
    }

    @Override
    public void purchase(Long userId, BigDecimal price)
    {
        String sql = "UPDATE users " 
        		+ "SET balance = balance - CAST(? AS money) " 
        		+ "WHERE user_id = ?; ";

        jdbcTemplate.update(sql, price, userId);
    }

    @Override
    public void deposit(Long userId, BigDecimal amount)
    {
        String sql = "UPDATE users " 
        		+ "SET balance = balance + CAST(? AS money) " 
        		+ "WHERE user_id = ?; ";

        jdbcTemplate.update(sql, amount, userId);
    }
}
