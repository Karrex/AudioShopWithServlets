package org.oa.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.oa.tp.data.Genre;

class GenreDao implements AbstractDao<Genre> {

    private Statement statement;
    private Connection connection;

    public GenreDao(Statement statement, Connection connection) {
        this.connection = connection;
        this.statement = statement;
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS genre ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + "name TEXT NOT NULL);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Genre> loadAll() {
        List<Genre> genres = new ArrayList<>();

        try {
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM genre;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                genres.add(new Genre(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    @Override
    public Genre findById(long objectId) {
        Genre genre = null;
        try {
            ResultSet resultSet = statement.executeQuery("select * from genre where id = " + objectId);
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            genre = new Genre(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    public boolean delete(long id) {
        try {
            statement.executeUpdate("DELETE FROM genre where id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Genre changed) {
        try {
            statement.executeUpdate("update genre set "
                    + "name='" + changed.getName()
                    + "' where id = " + changed.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean add(Genre item) {
        try {
            statement.executeUpdate("INSERT INTO genre " + " (name)"
                    + " VALUES ('" + item.getName()
                    + "')");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<Genre> collection) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into genre (name) values (?)");
            for (Genre genre : collection) {
                preparedStatement.setString(1, genre.getName());
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
