package com.pj.core.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class ImageUtil {
	// 图片到byte数组
	public static byte[] image2byte(File file) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

	// byte数组到图片
	public void byte2image(byte[] data, String path) {
		if (data.length < 3 || path.equals(""))
			return;
		try {
			FileImageOutputStream imageOutput = new FileImageOutputStream(
					new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("Make Picture success,Please find image in "
					+ path);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}

	// byte数组到16进制字符串
	public String byte2string(byte[] data) {
		if (data == null || data.length <= 1)
			return "0x";
		if (data.length > 200000)
			return "0x";
		StringBuffer sb = new StringBuffer();
		int buf[] = new int[data.length];
		// byte数组转化成十进制
		for (int k = 0; k < data.length; k++) {
			buf[k] = data[k] < 0 ? (data[k] + 256) : (data[k]);
		}
		// 十进制转化成十六进制
		for (int k = 0; k < buf.length; k++) {
			if (buf[k] < 16)
				sb.append("0" + Integer.toHexString(buf[k]));
			else
				sb.append(Integer.toHexString(buf[k]));
		}
		return "0x" + sb.toString().toUpperCase();
	}

	public static void composePic(File bgfile, File logofile, String outsrc,
			int x, int y) {
		
		try {
			
			Image bg_src = javax.imageio.ImageIO.read(bgfile);
			
			Image logo_src = javax.imageio.ImageIO.read(logofile);
			
			int bg_width = bg_src.getWidth(null);
			
			int bg_height = bg_src.getHeight(null);
			
			int logo_width = logo_src.getWidth(null);
			
			int logo_height = logo_src.getHeight(null);
			
			BufferedImage tag = new BufferedImage(bg_width, bg_height, BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g2d = tag.createGraphics();
			
			g2d.drawImage(bg_src, 0, 0, bg_width, bg_height, null);
			
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_ATOP, 1.0f)); // 透明度设置开始
			
			g2d.drawImage(logo_src, x, y, logo_width, logo_height, null);
			
			g2d.setComposite(AlphaComposite
					.getInstance(AlphaComposite.SRC_OVER)); // 透明度设置 结束
			
			FileOutputStream out = new FileOutputStream(outsrc);
			
			ImageIO.write(tag, "jpg", out);// 写图片
			
			out.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static byte[] image2Bytes(Image image, String format) {
		BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bImage.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	
}
