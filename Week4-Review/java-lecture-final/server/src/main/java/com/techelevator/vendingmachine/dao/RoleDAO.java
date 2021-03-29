package com.techelevator.vendingmachine.dao;

import java.util.List;

import com.techelevator.vendingmachine.models.security.Role;

public interface RoleDAO 
{
       List<Role> getRoles();
       List<Role> getRolesByUserId(int userId);
       Role findRoleById(int id);
       void addUserToRole(int userId, int roleId);
       void removeUserFromRole(int userId, int roleId);
}
