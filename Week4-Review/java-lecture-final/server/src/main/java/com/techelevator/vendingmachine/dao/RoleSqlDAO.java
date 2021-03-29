package com.techelevator.vendingmachine.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.vendingmachine.models.security.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class RoleSqlDAO implements RoleDAO 
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> getRoles() 
    {
        List<Role> roles = new ArrayList<Role>();

        String sql = "SELECT role_id "
            + "     , rolename "
            + "FROM roles;";
        
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while(rows.next())
        {
            Role role = mapRowToRole(rows);
            roles.add(role);
        }

        return roles;
    }

    @Override
    public List<Role> getRolesByUserId(int userId) 
    {
        List<Role> roles = new ArrayList<Role>();

        String sql = "SELECT r.role_id "
            + "     , r.rolename "
            + "FROM roles AS r "
            + "INNER JOIN user_roles AS ur "
            + "     ON r.role_id = ur.role_id "
            + "WHERE ur.user_id = ?; ";
        
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId);

        while(rows.next())
        {
            Role role = mapRowToRole(rows);
            roles.add(role);
        }

        return roles;
    }

    @Override
    public Role findRoleById(int id) 
    {
        Role role = null;

        String sql = "SELECT r.role_id "
            + "     , r.rolename "
            + "FROM roles AS r "
            + "WHERE r.role_id = ?; ";
        
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);

        if(row.next())
        {
            role = mapRowToRole(row);
        }

        return role;
    }

    @Override
    public void addUserToRole(int userId, int roleId) 
    {
        String sql = "INSERT INTO user_roles (user_id, role_id) VALUES (?,?); ";

        jdbcTemplate.update(sql, userId, roleId);
    }

    @Override
    public void removeUserFromRole(int userId, int roleId) 
    {
        String sql = "DELETE FROM user_roles "
            + "WHERE user_id = ? "
            + "     AND role_id = ?; ";

        jdbcTemplate.update(sql, userId, roleId);
    }
    
    private Role mapRowToRole(SqlRowSet row)
    {
        Role role = new Role();

        role.setId(row.getInt("role_id"));
        role.setRoleName(row.getString("rolename"));

        return role;
    }
}
