package poke.fast.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
	public static String loadFileAsString (String path) {
		StringBuilder builder = new StringBuilder(); //Makes loading strings easy
		//Try to read
		try {
			BufferedReader br = new BufferedReader(new FileReader (path));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace(); //Prints error to screen
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
