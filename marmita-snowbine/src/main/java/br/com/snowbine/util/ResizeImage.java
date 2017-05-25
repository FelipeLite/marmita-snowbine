package br.com.snowbine.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class ResizeImage
{
	public static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height)
	{
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, null);
		g.dispose();
		
		return resizedImage;
	}
}
