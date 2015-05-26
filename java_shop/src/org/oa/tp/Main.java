package org.oa.tp;

import org.oa.tp.dao.DaoFacade;
import org.oa.tp.data.Album;

import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DaoFacade facade = new DaoFacade();
		
		Album item = new Album(1, "TTT1", 1999);
		List<Album> albums = facade.getAlbumDao().loadAll();
	
		facade.getAlbumDao().add(item);
		
		facade.getAlbumDao().saveAll();

	}

}
