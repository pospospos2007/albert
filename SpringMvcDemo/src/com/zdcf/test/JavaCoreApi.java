package com.zdcf.test;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 原理就是在现在的awt或者swing上显示网页然后将内容保存为一个图片
 * 没办法控制延迟啊。
 * @author zlqiao
 *
 */
public class JavaCoreApi {
	public static void main(String[] args) throws Exception {
		//load the webpage into the editor
		//JEditorPane ed = new JEditorPane(new URL("http://www.google.com"));
		JEditorPane ed = new JEditorPane(new URL("http://www.baidu.com"));
		System.out.println("10");
		Thread.sleep(10000);
		ed.setSize(1000,1000);

		//create a new image
		BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),
		                                        BufferedImage.TYPE_INT_ARGB);

		//paint the editor onto the image
		SwingUtilities.paintComponent(image.createGraphics(), 
		                              ed, 
		                              new JPanel(), 
		                              0, 0, image.getWidth(), image.getHeight());
		//save the image to file
		ImageIO.write((RenderedImage)image, "png", new File("html1.png"));
			System.out.println("ok");
	
	}
}