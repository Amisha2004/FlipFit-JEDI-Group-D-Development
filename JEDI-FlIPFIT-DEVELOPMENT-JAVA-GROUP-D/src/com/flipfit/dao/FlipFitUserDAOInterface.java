package com.flipfit.dao;

import com.flipfit.bean.FlipFitUser;

import java.util.List;

public interface FlipFitUserDAOInterface {
    public FlipFitUser login(String emailId, String password);

    public boolean register(FlipFitUser user);

    public void deleteUser(int userId);

    public FlipFitUser updateUser(FlipFitUser user);

    public FlipFitUser getUser(int userId);
}
