package com.juna.dvdlibrary;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.juna.dvdlibrary.dao.DVDLibrary;
import com.juna.dvdlibrary.dto.DVD;
import com.juna.dvdlibrary.ui.Prompt;

public class DVDController {
	Prompt consoleIo = new Prompt();
	DVDLibrary dao  = new DVDLibrary();

	public void run(){
		try{
			boolean keepAlive = true;
			int menuSelection =0;
			dao.loadDVD();

			while(keepAlive){
				DVDLibraryMenu();
				getAllDVDs();
				menuSelection =	consoleIo.readValue("Please select from the above choices", 1, 5);
				switch (menuSelection) {
				case 1:
					consoleIo.givenString("Adding a new DVD to the Library");
					addDVD();
					break;

				case 2:
					consoleIo.givenString("Removing a DVD from the library");
					removeDVD();
					break;

				case 3:
					consoleIo.givenString("Retreiving a DVD from the library by title");
					retreiveDVD();
					break;

				case 4:
					consoleIo.givenString("Retreiving all the DVDs of the library");
					getAllDVDs();
					break;

				case 5:
					consoleIo.givenString("Exiting from the menu");
					dao.writerDVD();
					keepAlive = false;
					break;

				default:
					consoleIo.givenString("Unknown Input");
					break;
				}

			}
		}catch(FileNotFoundException e){
			consoleIo.givenString("DVDLibrary file wasnot found");
		}catch(IOException e){
			consoleIo.givenString("Unable to write DVDLibrary file");
		}

	}

	private void addDVD(){
		String title = consoleIo.readString("Please enter the title of DVD");
		Integer releaseDate = consoleIo.userInput("Please enter the DVD release date");
		float ratings = consoleIo.floatValue("Please enter the ratings of DVD");
		String directorName = consoleIo.readString("Please enter the name of a director");
		String studio = consoleIo.readString("Please enter the studio name");
		String userNotes = consoleIo.readString("Please enter any message or notes");

		DVD dvd = new DVD();
		dvd.setTitle(title);
		dvd.setReleaseDate(releaseDate);
		dvd.setRatings(ratings);
		dvd.setDirectorName(directorName);
		dvd.setStudio(studio);
		dvd.setUserNotes(userNotes);


		dao.addDVD(dvd);
		consoleIo.givenString("New DVD Added to the library");	
	}


	private void removeDVD(){
		String title = consoleIo.readString("Please enter the title of the DVD you want to delete");
		DVD dvd = dao.removeDVD(title);
		if(dvd != null){
			consoleIo.givenString("The DVD with title " + dvd.getTitle()+ " is deleted");
		}
		else{
			consoleIo.givenString("DVD with title " + title + " doesnot exist");
		}
	}


	private void retreiveDVD(){
		String title = consoleIo.readString("Please enter the title of DVD you want to retreive");
		DVD dvd = dao.getDVD(title);
		if(dvd!= null){
			consoleIo.givenString("Title: " + dvd.getTitle());
			consoleIo.givenString("Release Date: " + dvd.getReleaseDate());
			consoleIo.givenString("Ratings: " + dvd.getRatings());
			consoleIo.givenString("Director's name: " + dvd.getDirectorName() );
			consoleIo.givenString("Studio: " + dvd.getStudio());
			consoleIo.givenString("User Notes: " + dvd.getUserNotes());
		}
		else{
			consoleIo.givenString("No DVD with title " + title + "is found");
		}

	}

	public void getAllDVDs(){
		String [] dvdTitles = dao.getAllDVDTitle();
		for(int i =0; i < dvdTitles.length; i++){
			DVD dvd = dao.getDVD(dvdTitles[i]);
			consoleIo.givenString(  "Title:  " + dvdTitles[i] + " , " + dvd.getReleaseDate() + " by " + dvd.getDirectorName());

		}
		consoleIo.returnSc("Please hit enter to continue");
	}


	private void DVDLibraryMenu(){
		consoleIo.givenString("Welcome to DVDLibrary Main Menu.");
		consoleIo.givenString("1. Add a DVD ");
		consoleIo.givenString("2. Remove a DVD");
		consoleIo.givenString("3. Retrieve a DVD by Title");
		consoleIo.givenString("4. Retreive all DVDs in the library");
		consoleIo.givenString("5. EXIT");


	}

}
