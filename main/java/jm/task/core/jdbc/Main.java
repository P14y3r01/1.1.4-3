package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Riley", "Reid", (byte) 27);
        userService.saveUser("Aloha", "Dance", (byte) 25);
        userService.saveUser("Aleksandr", "Levin", (byte) 25);
        userService.saveUser("Anton", "Shastun", (byte) 29);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
