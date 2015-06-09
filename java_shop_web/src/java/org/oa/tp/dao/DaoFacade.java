package org.oa.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import javax.xml.transform.stax.StAXSource;

import org.oa.tp.data.Album;
import org.oa.tp.data.Author;
import org.oa.tp.data.Genre;

public class DaoFacade {

    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "toor";

    private Connection connection;
    private Statement statement;
    private AlbumDao albumDao;
    private GenreDao genreDao;
    private AuthorDao authorDao;

    public DaoFacade(ServletContext context) {
        try {
//            Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.jdbc.Driver");
            String path = context.getRealPath("test.db");
//            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection == null) {
            System.exit(1);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        albumDao = new AlbumDao(statement, connection);
        genreDao = new GenreDao(statement, connection);
        authorDao = new AuthorDao(statement, connection);
    }

    public AbstractDao<Album> getAlbumDao() {
        return albumDao;
    }

    public AbstractDao<Genre> getGenreDao() {
        return genreDao;
    }

    public AbstractDao<Author> getAuthorDao() {
        return authorDao;
    }

    public void closeSqlConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
