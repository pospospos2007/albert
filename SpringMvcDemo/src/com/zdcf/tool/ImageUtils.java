package com.zdcf.tool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description：图片通用处理工具类
 * @author 懂得
 * @version 创建时间：2016年8月19日  上午11:17:00
 */
public class ImageUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	
	/**
     * 使用ImageReader获取图片尺寸
     * 
     * @param src 源图片路径
     */
    public void getImageSizeByImageReader(String src) {
        long beginTime = new Date().getTime();
        File file = new File(src);
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            reader.setInput(iis, true);
            System.out.println("width:" + reader.getWidth(0));
            System.out.println("height:" + reader.getHeight(0));
        } catch (IOException e) {
        	logger.error("ImageUtils.getImageSizeByImageReader", e);
        }
        long endTime = new Date().getTime();
        System.out.println("使用[ImageReader]获取图片尺寸耗时：[" + (endTime - beginTime)+"]ms");
    }

    /**
     * 使用BufferedImage获取图片尺寸
     * 
     * @param src 源图片路径
     */
    public void getImageSizeByBufferedImage(String src) {
        long beginTime = new Date().getTime();
        File file = new File(src);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
        	logger.error("ImageUtils.getImageSizeByImageReader", e);
        }
        BufferedImage sourceImg = null;
        try {
            sourceImg = javax.imageio.ImageIO.read(is);
            System.out.println("width:" + sourceImg.getWidth());
            System.out.println("height:" + sourceImg.getHeight());
        } catch (IOException e1) {
        	logger.error("ImageUtils.getImageSizeByImageReader", e1);
        }
        long endTime = new Date().getTime();
        System.out.println("使用[BufferedImage]获取图片尺寸耗时：[" + (endTime - beginTime)+"]ms");
    }

    /**
     * 获取图片的宽
     * @param srcImageFile 源图片路径
     * @return
     */
    public static int  getImageWidth(String srcImageFile){
         try {
        	 File file = new File(srcImageFile);
             Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
             ImageReader reader = (ImageReader) readers.next();
             ImageInputStream iis = ImageIO.createImageInputStream(file);
             reader.setInput(iis, true);
             return reader.getWidth(0);
         } catch (IOException e) {
             e.printStackTrace();
             return 0;
         }
    }
    
    /***
     * 获取图片的高
     * @param srcImageFile 源图片路径
     * @return
     * @date   2016-3-30下午9:02:19
     */
    public static int getImageHeight(String srcImageFile){
    	File file = new File(srcImageFile);
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            reader.setInput(iis, true);
           
            return reader.getHeight(0);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
       
	 /**
	  * 图片旋转指定角度 
	  * @param src 文件流:源图像地址
	  * @param angel 旋转度
	  * @return
	  */
	 public static BufferedImage Rotate(Image src, int angel) {  
	        int src_width = src.getWidth(null);   // 源图宽度  
	        int src_height = src.getHeight(null);   // 源图高度  
	        angel=angel>0?angel:(360+angel); 
	        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(  
	                src_width, src_height)), angel);  
	  
	        BufferedImage res = null;  
	        res = new BufferedImage(rect_des.width, rect_des.height,  
	                BufferedImage.TYPE_INT_RGB);  
	        Graphics2D g2 = res.createGraphics();  
	        g2.setPaint(Color.WHITE);
	        g2.fillRect(0, 0, rect_des.width,  rect_des.height);
	        // transform  
	        g2.translate((rect_des.width - src_width) / 2,  
	                (rect_des.height - src_height) / 2);  
	        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);  
	  
	        g2.drawImage(src, null, null);  
	        return res;  
	 }
	 public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
         // if angel is greater than 90 degree, we need to do some conversion  
         if (angel >= 90) {  
             if(angel / 90 % 2 == 1){  
                 int temp = src.height;  
                 src.height = src.width;  
                 src.width = temp;  
             }  
             angel = angel % 90;  
         }  
         double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
         double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
         double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
         double angel_dalta_width = Math.atan((double) src.height / src.width);  
         double angel_dalta_height = Math.atan((double) src.width / src.height);  
   
         int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha  
                 - angel_dalta_width));  
         int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha  
                 - angel_dalta_height));  
         int des_width = src.width + len_dalta_width * 2;  
         int des_height = src.height + len_dalta_height * 2;  
         return new java.awt.Rectangle(new Dimension(des_width, des_height));  
    }
    
	/***
	 * 剪裁图片
	 * @param srcImageFile
	 * @param dest
	 * @param x  起点横坐标
	 * @param y  纵坐标
	 * @param w  长
	 * @param h  高
	 * @throws IOException
	 * @date   2016-3-30下午12:19:10
	 */
	 public static boolean cutImage(InputStream srcImageFile,File dest,int x,int y,int w,int h) {   
         try{
        	 BufferedImage src = ImageIO.read(srcImageFile); // 读入文件
			 Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");   
	         ImageReader reader = (ImageReader)iterator.next();  
	         
	        //获取图片流   
	         ByteArrayOutputStream bs = new ByteArrayOutputStream();  
	         ImageOutputStream ios = ImageIO.createImageOutputStream(bs);  
	         ImageIO.write(src, "jpg", ios);
	         InputStream in=new  ByteArrayInputStream(bs.toByteArray());
	         ImageInputStream iis = ImageIO.createImageInputStream(in);   
	         reader.setInput(iis, true); 
	         ImageReadParam param = reader.getDefaultReadParam(); 
	         x=(x>0?x:0);
	         y=(y>0?y:0);
	         Rectangle rect = new Rectangle(x, y, w,h);    
	         param.setSourceRegion(rect);   
	         BufferedImage bi = reader.read(0,param);     
	         ImageIO.write(bi, "jpg", dest);    
	         return true;
         }catch (Exception e) {
        	 e.printStackTrace();
        	 return false;
		}
    }
    
	/**
	 * 剪裁图片(x,y,w,h,r)    
	 * @param srcImageFile 文件流:源图像地址
	 * @param dest   新图像
	 * @param x  目标切片起点x坐标 
	 * @param y  目标切片起点y坐标 
	 * @param w  目标切片宽度
	 * @param h  目标切片高度 
	 * @param r  目标切片旋转度
	 * @return
	 */
	public static boolean cutAndRotateImage(InputStream srcImageFile,File dest,int x,int y,int w,int h,int r){
		try{
			/*
			 * BufferedImage是Image的一个子类，BufferedImage生成的图片在内存里有一个图像缓冲区，
			 * 利用这个缓冲区我们可以很方便的操作这个图片，通常用来做图片修改操作如大小变换、图片变灰、设置图片透明或不透明等。
			 */
			BufferedImage src = ImageIO.read(srcImageFile); // 读入文件
	
	        //图片旋转指定角度 
			BufferedImage tag= Rotate(src, r);
			
			//读取图片文件 
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");   
	        ImageReader reader = (ImageReader)iterator.next(); 
	        
	        //获取图片流   
	        ByteArrayOutputStream bs = new ByteArrayOutputStream();  
	        ImageOutputStream ios = ImageIO.createImageOutputStream(bs);  
	        ImageIO.write(tag, "jpg", ios);
	        InputStream in=new  ByteArrayInputStream(bs.toByteArray());
	        ImageInputStream iis=ImageIO.createImageInputStream(in);
	        reader.setInput(iis, true);   
	        ImageReadParam param = reader.getDefaultReadParam(); 
	        x=(x>0?x:0);
	        y=(y>0?y:0);
	        
	        //定义一个矩形 
	        Rectangle rect = new Rectangle(x, y, w,h);    
	        
	        //提供一个 BufferedImage，将其用作解码像素数据的目标。   
	        param.setSourceRegion(rect);   
	        BufferedImage bi = reader.read(0,param);    
	        
	        //保存新图片  
	        ImageIO.write(bi, "jpg", dest);  
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	 /***
	  * 按照比例缩放
	  * @param srcImageFile
	  * @param result  
	  * @param scale 缩放比例
	  * @param flag 缩放选择:true 放大; false 缩小;
	  * @return
	  * @date   2016-3-30下午2:39:44
	  */
	 public  static boolean scale(String srcImageFile, String result,int scale, boolean flag) {
	        try {
	        	File file = new File(srcImageFile);
	            BufferedImage src = ImageIO.read(file); // 读入文件
	             Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
	             ImageReader reader = (ImageReader) readers.next();
	             ImageInputStream iis = ImageIO.createImageInputStream(file);
	             reader.setInput(iis, true);
	            int width = reader.getWidth(0); // 得到源图宽
	            int height = reader.getHeight(0); // 得到源图长
	            if (flag) {// 放大
	                width = width * scale;
	                height = height * scale;
	            } else {// 缩小
	                width = width / scale;
	                height = height / scale;
	            }
	            Image image = src.getScaledInstance(width, height,
	                    Image.SCALE_DEFAULT);
	            BufferedImage tag = new BufferedImage(width, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics g = tag.getGraphics();
	            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
	            g.dispose();
	            ImageIO.write(tag, "jpg", new File(result));// 输出到文件流
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	/***
	 * 缩放图像，按照长宽缩放
	 * @param srcImageFile
	 * @param result
	 * @param height  变换后的高度
	 * @param width   变换后的长度
	 * @param bb      比例不对称时，是否补白，true 补白;false 不补白
	 * @return
	 * @date   2016-3-30下午2:44:37
	 */
	public static boolean scale2(String srcImageFile, String result, int height, int width, boolean bb) {
	    try {
	    	
	        double ratio = 0.0; // 缩放比例
	        File file = new File(srcImageFile);
	        BufferedImage bi = ImageIO.read(file); // 读入文件
	        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
	        ImageReader reader = (ImageReader) readers.next();
	        ImageInputStream iis = ImageIO.createImageInputStream(file);
	        reader.setInput(iis, true);
	        int width1 = reader.getWidth(0); // 得到源图宽
	        int height1 = reader.getHeight(0); // 得到源图长
	        @SuppressWarnings("static-access")
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
	        // 计算比例
	        if ((height1 > height) || (width1 > width)) {
	            if (height1 > width1) {
	                ratio = (new Integer(height)).doubleValue()
	                        / height1;
	            } else {
	                ratio = (new Integer(width)).doubleValue() / width1;
	            }
	            AffineTransformOp op = new AffineTransformOp(AffineTransform
	                    .getScaleInstance(ratio, ratio), null);
	            itemp = op.filter(bi, null);
	        }
	        if (bb) {//补白
	            BufferedImage image = new BufferedImage(width, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics2D g = image.createGraphics();
	            g.setColor(Color.white);
	            g.fillRect(0, 0, width, height);
	            if (width == itemp.getWidth(null))
	                g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
	                        itemp.getWidth(null), itemp.getHeight(null),
	                        Color.white, null);
	            else
	                g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
	                        itemp.getWidth(null), itemp.getHeight(null),
	                        Color.white, null);
	            g.dispose();
	            itemp = image;
	        }
	        ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	 
	 /***
	  * 转换图像格式
	  * @param srcImageFile 原图像地址
	  * @param formatName   转换类型
	  * @param destImageFile 转换后的地址
	  * @return
	  * @author roychenyi
	  * @date   2016-3-30下午2:49:15
	  */
	public  static boolean convert(String srcImageFile, String formatName, String destImageFile) {
	    try {
	        File f = new File(srcImageFile);
	        f.canRead();
	        f.canWrite();
	        BufferedImage src = ImageIO.read(f);
	        ImageIO.write(src, formatName, new File(destImageFile));
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	    
	/**
	 * 彩色转为黑白 
	 * @param srcImageFile 源图像地址
	 * @param destImageFile 目标图像地址
	 */
	public final static void gray(String srcImageFile, String destImageFile) {
	    try {
	        BufferedImage src = ImageIO.read(new File(srcImageFile));
	        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	        ColorConvertOp op = new ColorConvertOp(cs, null);
	        src = op.filter(src, null);
	        ImageIO.write(src, "jpg", new File(destImageFile));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	    
	/**
	 * 给图片添加图片水印
	 * @param pressImg 水印图片
	 * @param srcImageFile 源图像地址
	 * @param destImageFile 目标图像地址
	 * @param x 修正值。 默认在中间
	 * @param y 修正值。 默认在中间
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public  static boolean pressImage(String pressImg, String srcImageFile,String destImageFile,
	        int x, int y, float alpha) {
	    try {
	    	File file = new File(srcImageFile);
	        BufferedImage src = ImageIO.read(file); // 读入文件
	         Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
	         ImageReader reader = (ImageReader) readers.next();
	         ImageInputStream iis = ImageIO.createImageInputStream(file);
	         reader.setInput(iis, true);
	        int width = reader.getWidth(0); // 得到源图宽
	        int height = reader.getHeight(0); // 得到源图长
	        BufferedImage image = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = image.createGraphics();
	        g.drawImage(src, 0, 0, width, height, null);
	        // 水印文件
	        Image src_biao = ImageIO.read(new File(pressImg));
	        int wideth_biao = src_biao.getWidth(null);
	        int height_biao = src_biao.getHeight(null);
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
	                alpha));
	        g.drawImage(src_biao, (width-wideth_biao-x) ,
	                (height-height_biao-y) , wideth_biao, height_biao, null);
	        // 水印文件结束
	        g.dispose();
	        ImageIO.write((BufferedImage) image,  "jpg", new File(destImageFile));
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	    
	/**
	 * 给图片添加文字水印
	 * @param pressText 水印文字
	 * @param srcImageFile 源图像地址
	 * @param destImageFile 目标图像地址
	 * @param fontName 水印的字体名称
	 * @param fontStyle 水印的字体样式
	 * @param color 水印的字体颜色
	 * @param fontSize 水印的字体大小
	 * @param x 修正值
	 * @param y 修正值
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public  static boolean pressText(String pressText,
	        String srcImageFile, String destImageFile, String fontName,
	        int fontStyle, Color color, int fontSize,int x,
	        int y, float alpha) {
	    try {
	    	File file = new File(srcImageFile);
	        BufferedImage src = ImageIO.read(file); // 读入文件
	         Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
	         ImageReader reader = (ImageReader) readers.next();
	         ImageInputStream iis = ImageIO.createImageInputStream(file);
	         reader.setInput(iis, true);
	        int width = reader.getWidth(0); // 得到源图宽
	        int height = reader.getHeight(0); // 得到源图长
	        BufferedImage image = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = image.createGraphics();
	        g.drawImage(src, 0, 0, width, height, null);
	        g.setColor(color);
	        g.setFont(new Font(fontName, fontStyle, fontSize));
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
	                alpha));
	        // 在指定坐标绘制水印文字
	        g.drawString(pressText, (width - (getLength(pressText) * fontSize))
	                / 2 + x, (height - fontSize) / 2 + y);
	        g.dispose();
	        ImageIO.write(image, "jpg", new File(destImageFile));// 输出到文件流
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	    
	/**
	 * 计算text的长度（一个中文算两个字符）
	 * @param text
	 * @return
	 */
	public  static int getLength(String text) {
	    int length = 0;
	    for (int i = 0; i < text.length(); i++) {
	        if (new String(text.charAt(i) + "").getBytes().length > 1) {
	            length += 2;
	        } else {
	            length += 1;
	        }
	    }
	    return length / 2;
	}
}
