package com.flipfit.dao;
import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitUserDAOImpl implements FlipFitUserDAOInterface {
    private List<FlipFitUser> userList = new ArrayList<>();

    // Method to get ALL users
    public List<FlipFitUser> getAllUsers() {
        return userList;
    }

    public FlipFitUser login(String emailId, String password) {
        for (FlipFitUser user : userList) {
            if (user.getEmailID().equalsIgnoreCase(emailId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean register(FlipFitUser user) {
        for (FlipFitUser existingUser : userList) {
            if (existingUser.getEmailID().equalsIgnoreCase(user.getEmailID())) {
                System.out.println("DAO: Registration failed. Email already in use.");
                return false;
            }
        }
        user.setUserId(userList.size() + 1);
        userList.add(user);
        return true;
    }

    public void deleteUser(int userId){
        System.out.println("Deleting user");
    }

    public FlipFitUser updateUser(FlipFitUser user) {
        System.out.println("Updating user");
        return null;
    }

    public FlipFitUser getUser(int userId){
        System.out.println("Getting user");
        return null;
    }
}
