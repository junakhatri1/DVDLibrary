package com.juna.dvdlibrary.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.juna.dvdlibrary.dto.DVD;

public class DVDLibrary {
	private Map<String, DVD >libraryMap = new HashMap<>();
	private static final String DVDLIBRARY_FILE = "dvdlibrary.txt";
	private static final String DELIMITER = "::";


	public DVD addDVD(DVD dvd){
		libraryMap.put(dvd.getTitle(), dvd);
		return dvd;
	}

	public DVD removeDVD(String title){
		DVD dvd = libraryMap.remove(title);
		return dvd;

	}
	public DVD getDVD(String title){                                 
		DVD dvd = libraryMap.get(title);
		return dvd;
	}


	public String[] getAllDVDTitle(){
		Set<String>keySet = libraryMap.keySet();
		String[] allDvds = new String[keySet.size()];
		allDvds = keySet.toArray(allDvds);
		return allDvds;
	}


	public void writerDVD() throws IOException{
		PrintWriter writer = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
		String[] dvdTitles = this.getAllDVDTitle();
		for(int i =0; i < dvdTitles.length; i++){
			DVD dvd = this.getDVD(dvdTitles[i]);
			writer.println(dvd.getTitle()+ DELIMITER
					+ dvd.getReleaseDate()+ DELIMITER
					+ dvd.getRatings() + DELIMITER
					+ dvd.getDirectorName()+ DELIMITER
					+ dvd.getStudio() + DELIMITER
					+ dvd.getUserNotes());
			writer.flush();

		}
		writer.close();

	}

	public void loadDVD() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader(DVDLIBRARY_FILE)));
		String currentLine;                                  
		String[] currentTokens;
		while(sc.hasNext()){
			currentLine = sc.nextLine();
			currentTokens = currentLine.split(DELIMITER);
			DVD dvd = new DVD();
			dvd.setTitle(currentTokens[0]);
			dvd.setReleaseDate(Integer.parseInt(currentTokens[1]));
			dvd.setRatings(Float.parseFloat(currentTokens[2]));
			dvd.setDirectorName(currentTokens[3]);
			dvd.setStudio(currentTokens[4]);
			dvd.setUserNotes(currentTokens[5]);
			libraryMap.put(dvd.getTitle(), dvd);
		}
		sc.close();
	}


}
