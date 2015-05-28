package org.oa.tp;

import java.util.List;

import org.oa.tp.dao.DaoFacade;
import org.oa.tp.data.Album;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DaoFacade facade = new DaoFacade();
		
		Album item = new Album(1, "TTT1", 1999);
		List<Album> albums = facade.getAlbumDao().loadAll();
	for (Album album : albums) {
		System.out.println(album);
	}
	System.out.println("--------------");
		Album changed =	albums.get(1);
		changed.setName("NEW NAME");
		changed.setYear(21982127);
		
		facade.getAlbumDao().update(changed);
		
		List<Album> albumsNEW = facade.getAlbumDao().loadAll();
		for (Album album : albumsNEW) {
			System.out.println(album);
		}
		
		
		facade.getAlbumDao().add(item);

		System.out.println("-->" +facade.getAlbumDao().findById(4));
		
		facade.getAlbumDao().addAll(albumsNEW);
		for (Album album : albumsNEW) {
			System.out.println(album);
		}
		facade.closeSqlConnection();
		
		
	}

}
