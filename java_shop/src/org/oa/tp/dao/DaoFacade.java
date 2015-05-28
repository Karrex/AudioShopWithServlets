package org.oa.tp.dao;

import org.oa.tp.data.Album;
import org.oa.tp.data.Genre;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFacade {

	public DaoFacade() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	private final AlbumDao albumDao = new AlbumDao();
	
	private final GenreDao genreDao = new GenreDao();

	public AbstractDao<Album> getAlbumDao() {
		return albumDao;
	}

	public AbstractDao<Genre> getGenreDao() {
		return genreDao;
	}
	
	
}
