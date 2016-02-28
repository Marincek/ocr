package com.rockacode.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class DoubleResizeProcessor implements ImageProcessor {
	
	@Override
	public BufferedImage process(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
		BufferedImage resizedImage = new BufferedImage(imgWidth * 2, imgHeight * 2, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, imgWidth * 2, imgHeight * 2, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		return resizedImage;
	}

}
