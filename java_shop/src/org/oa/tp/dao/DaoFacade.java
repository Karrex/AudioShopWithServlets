package org.oa.tp.dao;

import org.oa.tp.data.Album;
import org.oa.tp.data.Genre;

public class DaoFacade {

	private final AlbumDao albumDao = new AlbumDao();
	
	private final GenreDao genreDao = new GenreDao();

	public AbstractDao<Album> getAlbumDao() {
		return albumDao;
	}

	public AbstractDao<Genre> getGenreDao() {
		return genreDao;
	}
	
	
}
