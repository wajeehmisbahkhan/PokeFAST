package poke.fast.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class Text {
	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		
		int x = xPos;
		int y = yPos;
		
		if (center) {
			//Contains info of font
			FontMetrics fm = g.getFontMetrics(font);
			x = xPos - fm.stringWidth(text) / 2;
			y = (yPos - fm.getHeight() / 2) + fm.getAscent();
		}
		for (String line : text.split("\n")) //To detect multiple lines
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public static ArrayList<String> getTokenedMessage(String message) {
		ArrayList<String> tokenedMessage = new ArrayList<String>();
		//This will add \n's in between.
		message = rehashMessage(message);
		//Check number of lines
		int numberOfLines = 0;
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == '\n')
				numberOfLines++;
		}
		//Split it up when more than two lines
		if (numberOfLines > 2) {
			int currentNumberOfLine = 0;
			int lastPosition = 0;
			//If the message is greater than two lines
			for (int i = 0; i < message.length(); i++) {
				if (message.charAt(i) == '\n') {
					currentNumberOfLine++;
					if (currentNumberOfLine % 2 == 0) {
						//Same logic as in rehashMessage
						tokenedMessage.add(message.substring(lastPosition, i));
						lastPosition = i+1;
					}
				}
			}
			//Add the remaining part
			tokenedMessage.add(message.substring(lastPosition, message.length()));
		} else {
			//If the message is less than two lines
			tokenedMessage.add(message);
		}
		
		return tokenedMessage;
	}

	//For tokened message
	private static String rehashMessage(String message) {
		int lastPosition = 0;
		int i = 35; // Add at the break of around 35
		String hashedMessage = new String(); //Add this
		while (i < message.length()) {
			if (message.charAt(i) != ' ')
				i--;
			else { //i = 29
				hashedMessage += message.substring(lastPosition, i) + "\n";
				lastPosition = i+1; //+1 to skip space
				i += 35;
			}
		}
		hashedMessage += message.substring(lastPosition, message.length());
		return hashedMessage;
	}
	
}
