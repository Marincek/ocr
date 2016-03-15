package com.rockacode.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BlackWhiteProcessor extends BaseImageProcessor {

	static final int BLACK = 0;
	static final int WHITE = 255;
	static final int THRESHOLD = 127;
	
	@Override
	public BufferedImage process(BufferedImage image) {
		try {
			int width = image.getWidth();
			int height = image.getHeight();

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color color = new Color(image.getRGB(j, i));
					int newColorInt = (int)(color.getGreen()*.7+color.getRed()*.2+color.getBlue()*.1);
					if(newColorInt < THRESHOLD)
						newColorInt = BLACK;
					else
						newColorInt = WHITE;
					Color newColor = new Color(newColorInt, newColorInt, newColorInt);
					image.setRGB(j, i, newColor.getRGB());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnImage(image);
	}

}
