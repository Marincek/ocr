//package com.rockacode.image;
//
//import java.awt.image.BufferedImage;
//
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//
//public class OpenCVProcessor extends BaseImageProcessor{
//
//	private int imgWidth;
//	private int imgHeight;
//
//	@Override
//	public BufferedImage process(BufferedImage image) {
//		imgWidth = image.getWidth();
//		imgHeight = image.getHeight();
//
//		Mat matImage = img2Mat(image);
//
//		// Enhancing Image Contrast
////		int alpha = 2;
////		int beta = 10;
////		matImage.convertTo(matImage, CvType.CV_8UC3, alpha, beta);
//
//		Imgproc.cvtColor(matImage, matImage, Imgproc.COLOR_BGR2GRAY);
//
//		Imgproc.GaussianBlur(matImage, matImage, new Size(3, 3), 2, 2);
//		Imgproc.adaptiveThreshold(matImage, matImage, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 5, 2);
//
//		Imgproc.dilate(matImage, matImage, Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(4, 4)));
//
//		// getNegativeImage(mat2Img(matImage));
//		
//		return returnImage(mat2Img(matImage));
//	}
//
//	private BufferedImage getNegativeImage(BufferedImage img) {
//		int w1 = img.getWidth();
//		int h1 = img.getHeight();
//		// int value[][] = new int[w1][h1];
//		BufferedImage gray = new BufferedImage(w1, h1, 1);
//		int value, alpha, r, g, b;
//		for (int i = 0; i < w1; i++) {
//			for (int j = 0; j < h1; j++) {
//				value = img.getRGB(i, j); // store value
//				alpha = getAlpha(value);
//				r = 255 - getRed(value);
//				g = 255 - getGreen(value);
//				b = 255 - getBlue(value);
//
//				value = createRGB(alpha, r, g, b);
//				gray.setRGB(i, j, value);
//			}
//		}
//		return gray;
//	}
//
//	private int createRGB(int alpha, int r, int g, int b) {
//		int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
//		return rgb;
//	}
//
//	private int getAlpha(int rgb) {
//		return (rgb >> 24) & 0xFF;
//	}
//
//	private int getRed(int rgb) {
//		return (rgb >> 16) & 0xFF;
//	}
//
//	private int getGreen(int rgb) {
//		return (rgb >> 8) & 0xFF;
//	}
//
//	private int getBlue(int rgb) {
//		return rgb & 0xFF;
//	}
//
//	private BufferedImage mat2Img(Mat in) {
//		BufferedImage out;
//		byte[] data = new byte[imgWidth * imgHeight * (int) in.elemSize()];
//		int type;
//		in.get(0, 0, data);
//
//		if (in.channels() == 1)
//			type = BufferedImage.TYPE_BYTE_GRAY;
//		else
//			type = BufferedImage.TYPE_3BYTE_BGR;
//
//		out = new BufferedImage(imgWidth, imgHeight, type);
//
//		out.getRaster().setDataElements(0, 0, imgWidth, imgHeight, data);
//		return out;
//	}
//
//	private Mat img2Mat(BufferedImage in) {
//		Mat out;
//		byte[] data;
//		int r, g, b;
//
//		if (in.getType() == BufferedImage.TYPE_3BYTE_BGR) {
//			out = new Mat(imgHeight, imgWidth, CvType.CV_8UC3);
//			data = new byte[imgWidth * imgHeight * (int) out.elemSize()];
//			int[] dataBuff = in.getRGB(0, 0, imgWidth, imgHeight, null, 0, imgWidth);
//			for (int i = 0; i < dataBuff.length; i++) {
//				data[i * 3] = (byte) ((dataBuff[i] >> 16) & 0xFF);
//				data[i * 3 + 1] = (byte) ((dataBuff[i] >> 8) & 0xFF);
//				data[i * 3 + 2] = (byte) ((dataBuff[i] >> 0) & 0xFF);
//			}
//		} else {
//			out = new Mat(imgHeight, imgWidth, CvType.CV_8UC1);
//			data = new byte[imgWidth * imgHeight * (int) out.elemSize()];
//			int[] dataBuff = in.getRGB(0, 0, imgWidth, imgHeight, null, 0, imgWidth);
//			for (int i = 0; i < dataBuff.length; i++) {
//				r = (byte) ((dataBuff[i] >> 16) & 0xFF);
//				g = (byte) ((dataBuff[i] >> 8) & 0xFF);
//				b = (byte) ((dataBuff[i] >> 0) & 0xFF);
//				data[i] = (byte) ((0.21 * r) + (0.71 * g) + (0.07 * b)); // luminosity
//			}
//		}
//		out.put(0, 0, data);
//		return out;
//	}
//
//}
