package org.oa.tp.dao;

import org.oa.tp.data.Author;

import java.sql.*;
import java.util.*;

class AuthorDao implements AbstractDao<Author> {

    private static final String PATH = "author.txt";
    private Set<Author> items = new HashSet<>();

    private Connection connection;
    private Statement statement;

    public AuthorDao(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS test.author\n"
                    + "(\n"
                    + "    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n"
                    + "    firstName VARCHAR(20) NOT NULL,\n"
                    + "    lastName VARCHAR(20) NOT NULL,\n"
                    + "    age INT NOT NULL,\n"
                    + "    gender ENUM('M', 'F', 'Male', 'Female') NOT NULL\n"
                    + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> loadAll() {
        List<Author> authors = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Author author = new Author(id, firstName, lastName, age, gender);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author findById(long objectId) {
        Author author = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + objectId + ";");
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            author = new Author(id, firstName, lastName, age, gender);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public boolean delete(long id) {
        try {
            statement.executeUpdate("DELETE FROM author WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Author changed) {
        try {
            statement.executeUpdate("update author set"
                    + " firstName='" + changed.getFirstName()
                    + "',lastName='" + changed.getLastName()
                    + "',age='" + changed.getAge()
                    + "',gender='" + changed.getGender()
                    + "' WHERE id=" + changed.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean add(Author item) {
        try {
            statement.executeUpdate("INSERT INTO author (firstName, lastName, age, gender)"
                    + " VALUES ('" + item.getFirstName() + "','" + item.getLastName()
                    + "','" + item.getAge() + "','" + item.getGender()
                    + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<Author> collection) {
        String sqlQuery = "insert into author (firstName, lastName, age, gender)"
                + "values ( ? , ? , ? , ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            for (Author author : collection) {
                preparedStatement.setString(1, author.getFirstName());
                preparedStatement.setString(2, author.getLastName());
                preparedStatement.setInt(3, author.getAge());
                preparedStatement.setString(4, author.getGender());
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
