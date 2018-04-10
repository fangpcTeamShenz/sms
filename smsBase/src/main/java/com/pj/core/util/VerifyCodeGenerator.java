package com.pj.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeGenerator {
	 private static final VerifyCodeGenerator generator = new VerifyCodeGenerator(); 
	    private final String ATTRIBUTE_NAME = "verifycode";  
	    private final int WIDTH = 16;
	    private final int HEIGHT=20;
	    private final int CODE_LENGTH = 4;

	    private final String RAND_RANGE = "abcdefghijklmnopqrstuvwxyz" 
	        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
	        + "1234567890"; 
	     
	    private final char[] CHARS = RAND_RANGE.toCharArray(); 
	     
	    private Random random = new Random(); 
	     
	    private VerifyCodeGenerator(){ 
	    } 
	     
	    public static VerifyCodeGenerator getInstance(){ 
	        return generator; 
	    } 
	     
	    private String getRandString(){ 
	        StringBuilder sb = new StringBuilder(); 
	        for (int i = 0; i < CODE_LENGTH; i++) 
	            sb.append(CHARS[random.nextInt(CHARS.length)]); 
	        return sb.toString(); 
	    } 

	    private Color getRandColor(int ll, int ul){ 
	        if (ll > 255) ll = 255; 
	        if (ll < 1) ll = 1; 
	        if (ul > 255) ul = 255; 
	        if (ul < 1) ul = 1; 
	        if (ul == ll) ul = ll + 1; 
	        int r = random.nextInt(ul - ll) + ll; 
	        int g = random.nextInt(ul - ll) + ll; 
	        int b = random.nextInt(ul - ll) + ll; 
	        Color color = new Color(r,g,b); 
	        return color; 
	    } 
	     

	    private BufferedImage getImage(String verifyCode){ 
	         
	        BufferedImage image = new BufferedImage(WIDTH * CODE_LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
	        Graphics graphics = image.getGraphics(); 
	        graphics.setColor(getRandColor(200,250));
	        graphics.fillRect(0, 0, WIDTH*4, HEIGHT);
	        graphics.setColor(getRandColor(160,200));
	        for (int i=0; i<50; i++){ 
	        	int x = random.nextInt(WIDTH); 
	        	int y = random.nextInt(HEIGHT); 
	        	int xl = random.nextInt(12); 
	        	int yl = random.nextInt(12); 
	        	graphics.drawLine(x,y,x+xl,y+yl);
	        } 
	        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 22));
	        for (int i=0; i<this.CODE_LENGTH; i++){ 
	            String temp = verifyCode.substring(i, i+1); 
	            graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110))); 
	            graphics.drawString(temp, 13 * i + 6, 16); 
	        } 
	        graphics.dispose(); 
	         
	        return image; 
	    } 
	    
		public void getShYidongURL(HttpServletRequest request, HttpServletResponse respons) {
//			respons.setCharacterEncoding(request.getCharacterEncoding());
//			String payno = WebUtils.getCleanParam(request,"payno");
//			String payfee = WebUtils.getCleanParam(request,"payfee");
//			int payTotalFee = 0;
//
//	        if(null!=payfee&&payfee!=""){
//	       	 payTotalFee = Integer.parseInt(payfee);
//	        }
//	        
//	        Object object = new Object();
//	        String pagecode,alertString;
//	        synchronized (object)
//	        {
//	        pagecode = GetAlipayRequest.getpagecode(payno, payTotalFee);
//	        alertString = GetAlipayRequest.getShPayRequestJson(payno, payTotalFee,pagecode);
//	        }
//		    respons.setContentType("text/plain; charset=UTF-8");  
//		    PrintWriter out = null;
//			try {
//				out = respons.getWriter();
//				out.println(alertString);  
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//				out.flush();  
//				out.close();  
//				
//			}
		} 
//	public void printImage(HttpServletRequest request, 
//            HttpServletResponse response){ 
//        response.setContentType("image/jpeg"); 
//        response.setHeader("Pragma", "No-cache"); 
//        response.setHeader("Cache-Control", "no-cache"); 
//        response.setDateHeader("Expires", 2000); 
//         
//        String verifyCode = this.getRandString(); 
//        String str = "ssss"; 
//        for(int i=0; i<10; i++) 
//            str = str + str; 
//        BufferedImage bi = this.getImage(verifyCode); 
//        request.getSession().setAttribute("ATTRIBUTE_NAME", verifyCode); 
//        try{ 
//            ServletOutputStream outStream = response.getOutputStream(); 
//
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream); 
//            encoder.encode(bi);
//            outStream.flush(); 
//       
//        }catch(IOException ex){ 
//            ex.printStackTrace(); 
//        } 
//    }
    public boolean check(HttpServletRequest request){ 
        if (((String)request.getParameter(ATTRIBUTE_NAME)).equalsIgnoreCase((String)request.getSession(true).getAttribute("ATTRIBUTE_NAME"))){ 
        	request.getSession().removeAttribute(ATTRIBUTE_NAME); 
        	return true; 
        }
        return false; 
    } 

}
