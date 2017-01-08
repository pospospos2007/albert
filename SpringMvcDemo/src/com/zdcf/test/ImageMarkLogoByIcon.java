package com.zdcf.test;

import java.awt.AlphaComposite;   
import java.awt.Graphics2D;   
import java.awt.Image;   
import java.awt.RenderingHints;   
import java.awt.image.BufferedImage;   
import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileOutputStream;   
import java.io.InputStream;   
import java.io.OutputStream;   
  

import javax.imageio.ImageIO;   
import javax.swing.ImageIcon;   
  

import com.sun.image.codec.jpeg.JPEGCodec;   
import com.sun.image.codec.jpeg.JPEGImageDecoder;   
import com.sun.image.codec.jpeg.JPEGImageEncoder;   
  
/**  
 * 给图片加水印  
 * 
 */  
public class ImageMarkLogoByIcon {   
  
    /**  
     * Test
     * @param args  
     */  
    public static void main(String[] args) {   
        String srcImgPath = "D:/images/B1.JPG";   
        String iconPath = "D:/images/zhang.png";   
        String targerPath = "D:/images/index_ban2.jpg";   
        String targerPath2 = "d:/test/michael/B1.JPG";   
        // 给图片添加水印   
//      ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath);   
        // 给图片添加水印,水印旋转-45   
//      ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath2,   
//                -45);   
        pressImage(iconPath,srcImgPath,5,0.5f);      
  
    }   
  
    /**  
     * 给图片添加水印  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath) {   
        markImageByIcon(iconPath, srcImgPath, targerPath, null);   
    }   
  
    /**  
     * 给图片添加水印、可设置水印图片旋转角度  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     * @param degree 水印图片旋转角度  
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath, Integer degree) {   
        OutputStream os = null;   
        try {   
            Image srcImg = ImageIO.read(new File(srcImgPath));   
  
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),   
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
  
            // 得到画笔对象   
            // Graphics g= buffImg.getGraphics();   
            Graphics2D g = buffImg.createGraphics();   
  
            // 设置对线段的锯齿状边缘处理   
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
  
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg   
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);   
  
            if (null != degree) {   
                // 设置水印旋转   
                g.rotate(Math.toRadians(degree),   
                        (double) buffImg.getWidth() / 2, (double) buffImg   
                                .getHeight() / 2);   
            }   
  
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);   
  
            // 得到Image对象。   
            Image img = imgIcon.getImage();  
            int width =img.getWidth(null);
            int height =img.getHeight(null)/2-img.getHeight(null)/4-img.getHeight(null)/4;
            
            float alpha = 0.5f; // 透明度   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
                    alpha));   
  
            // 表示水印图片的位置   
            g.drawImage(img, width, height, null);   
  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
  
            g.dispose();   
  
            os = new FileOutputStream(targerPath);   
  
            // 生成图片   
            ImageIO.write(buffImg, "JPG", os);   
  
            System.out.println("图片完成添加Icon印章。。。。。。");   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
    }
    
    /**
    * 图片水印
    * @param pressimg 水印图片
    * @param targetimg 目标图片
    * @param location 位置：1、左上角，2、右上角，3、左下角，4、右下角，5、正中间
    * @param alpha 透明度
    */
    public static void pressImage(String pressimg, String targetimg, int location, float alpha) {
	    try {
		    //读取目标图片
		    File img = new File(targetimg);
		    Image src = ImageIO.read(img);
		    int width = src.getWidth(null);
		    int height = src.getHeight(null);
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    Graphics2D g = image.createGraphics();
		    g.drawImage(src, 0, 0, width, height, null);
		    //水印文件
		    Image src_biao = ImageIO.read(new File(pressimg));
		    int width_biao = src_biao.getWidth(null);
		    int height_biao = src_biao.getHeight(null);
		    //如果水印图片高或者宽大于目标图片是做的处理,使其水印宽或高等于目标图片的宽高，并且等比例缩放
		    int new_width_biao = width_biao;
		    int new_height_biao = height_biao;
		    if(width_biao > width){
		    new_width_biao = width;
		    new_height_biao = (int) ((double)new_width_biao/width_biao*height);
		    }
		    if(new_height_biao > height){
		    new_height_biao = height;
		    new_width_biao = (int) ((double)new_height_biao/height_biao*new_width_biao);
		    }
		    //根据位置参数确定坐标位置
		    int x = 0;
		    int y = 0;
		    switch(location)
		    {
		    case 1:
		    break;
		    case 2:
		    	x = width - new_width_biao;
		    break;
		    case 3:
		    y = height - new_height_biao;
		    break;
		    case 4:
		    	x = width - new_width_biao;
		    	y = height -new_height_biao;
		    break;
		    case 5:
		    	x = (width - new_width_biao)/2;
		    	y = (height - new_height_biao)/2;
		    break;
		    default:
		    break;
		    }
		    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		    g.drawImage(src_biao, x, y, new_width_biao, new_height_biao, null);
		    //水印文件结束
		    g.dispose();
		    ImageIO.write( image, "png", img);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    }
    
}  