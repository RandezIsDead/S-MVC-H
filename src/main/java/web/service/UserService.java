package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUser(int id);
    void updateUser(int id, User user);
    void removeUser(int id);
    List<User> getUsers();
}
