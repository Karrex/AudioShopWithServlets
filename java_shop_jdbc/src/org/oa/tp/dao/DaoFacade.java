package org.oa.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.stax.StAXSource;

import org.oa.tp.data.Album;
import org.oa.tp.data.Genre;

public class DaoFacade {

	
	private Connection connection;
	private AlbumDao albumDao;
	
	private GenreDao genreDao = new GenreDao();
	private Statement statement;

	public DaoFacade() {
		try {
	        connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
		if(connection==null){
			System.exit(1);
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		albumDao = new AlbumDao(statement, connection);
	}
	public AbstractDao<Album> getAlbumDao() {
		return albumDao;
	}

	public AbstractDao<Genre> getGenreDao() {
		return genreDao;
	}
	
	public void closeSqlConnection(){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!= null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
