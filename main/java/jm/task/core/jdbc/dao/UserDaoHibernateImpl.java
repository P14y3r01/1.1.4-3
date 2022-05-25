package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE Users (id BIGINT PRIMARY KEY AUTO_INCREMENT,  name varchar(255), lastName varchar(255), age TINYINT)").addEntity(User.class).executeUpdate();
            transaction.commit();


    }

    @Override
    public void dropUsersTable() {

            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
            transaction.commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        System.out.println("User с именем - " + name + " " + lastName + " добавлен в базу данных.");
        transaction.commit();

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(session.get(User.class, id));
        transaction.commit();

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list;
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        list = session.createQuery("FROM User").list();
        transaction.commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM Users").executeUpdate();
        transaction.commit();

    }
}
