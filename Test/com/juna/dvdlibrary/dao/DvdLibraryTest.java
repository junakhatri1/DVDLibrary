package com.juna.dvdlibrary.dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.juna.dvdlibrary.dto.DVD;

public class DvdLibraryTest {
	DVDLibrary dao;
	DVD d1;
	DVD d2;


	@Before

	public void setUp(){
		dao = new DVDLibrary();
		d1 = new DVD();
		d1.setTitle("Daangal");
		d1.setReleaseDate(2016);
		d1.setRatings(4.8f);
		d1.setStudio("Bollywood");
		d1.setDirectorName("AAmir Khan");
		d1.setUserNotes("Highly rated movie");

		d2 = new DVD();
		d2.setTitle("Logan");
		d2.setReleaseDate(2017);
		d2.setRatings(4.8f);
		d2.setStudio("Hollywood");
		d2.setDirectorName("James Mangold");
		d2.setUserNotes("Action Movie");
	}


	@Test
	public void addDVDTest() {
		//AAA
		//Arrange
		DVD dvd = 	dao.addDVD(d1);
		//Act
		DVD result = dao.getDVD(dvd.getTitle());
		//Assert
		assertEquals(d1, result);

	}

	@Test
	public void removeDVDTest(){

		//Arrange
		dao.addDVD(d1);
		dao.addDVD(d2);
		//Act
		DVD dvd1 = dao.removeDVD(d1.getTitle());
		DVD result = dao.getDVD(dvd1.getTitle());
		//Assert
		assertNull(result);

	}

	@Test
	public void getDVDTest(){
		//AAA
		//Arrange
		dao.addDVD(d1);
		//Act
		DVD dvd1 = dao.getDVD(d1.getTitle());
		DVD result = dao.getDVD(dvd1.getTitle());
		//Assert
		assertEquals(d1, result);
	}

	@Test

	public void getAllDVDTitleTest(){
		//AAA
		//Arrange
		dao.addDVD(d1);
		dao.addDVD(d2);
		//Act
		String[] dvd1 = dao.getAllDVDTitle();
		int result = dvd1.length;
		//Assert
		Assert.assertEquals(2, result);

	}


}
