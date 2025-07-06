package com.flipfit.dao;

import com.flipfit.bean.FlipFitUser;

public interface FlipFitUserDAOInterface {
    public FlipFitUser login(String username, String password, int roleId);

    public FlipFitUser register(FlipFitUser user);

    public void deleteUser(int userId);

    public FlipFitUser updateUser(FlipFitUser user);

    public FlipFitUser getUser(int userId);
}
