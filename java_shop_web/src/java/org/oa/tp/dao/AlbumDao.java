package org.oa.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.oa.tp.data.*;

class AlbumDao implements AbstractDao<Album> {

    private Statement statement;
    private Connection connection;

    public AlbumDao(Statement statement, Connection connection) {
        this.connection = connection;
        this.statement = statement;
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS album ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + "name TEXT NOT NULL," + "year INT NOT NULL" + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Album> loadAll() {
        List<Album> albums = new ArrayList<>();

        try {
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM album;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                albums.add(new Album(id, name, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albums;
    }

    @Override
    public Album findById(long objectId) {
        Album album = null;
        try {
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM album WHERE id=" + objectId
                            + ";");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                album = new Album(id, name, year);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }

    @Override
    public boolean delete(long id) {

        try {
            statement.executeUpdate("DELETE FROM album WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean update(Album changed) {
        try {
            statement.executeUpdate("UPDATE album SET name='"
                    + changed.getName() + "',year='" + changed.getYear()
                    + "' WHERE id=" + changed.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean add(Album item) {
        try {
            statement.executeUpdate("INSERT INTO album " + " (name, year)"
                    + " VALUES ('" + item.getName() + "','" + item.getYear()
                    + "')");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<Album> collection) {

        String sqlQuery = "INSERT INTO album " + " (name, year)"
                + " VALUES ( ? , ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Album album : collection) {
                statement.setString(1, album.getName());
                statement.setInt(2, album.getYear());
                statement.executeUpdate();
            }

            connection.commit();
            connection.setAutoCommit(true);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
}
