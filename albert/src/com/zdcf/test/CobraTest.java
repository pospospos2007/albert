package com.zdcf.test;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.lobobrowser.html.test.SimpleUserAgentContext;

public class CobraTest {
	public static void main(String[] args) throws Exception {
		JFrame window = new JFrame();
		HtmlPanel panel = new HtmlPanel();
		window.getContentPane().add(panel);
		window.setSize(600, 400);
		window.setVisible(true);
		new SimpleHtmlRendererContext(panel, new SimpleUserAgentContext())
				.navigate("http://jobs.zhaopin.com/377931819252715.htm?ssidkey=y&ss=201&ff=03");
		System.out.println("10");
		Thread.sleep(10000);
		BufferedImage image = new BufferedImage(panel.getWidth(),
				panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// paint the editor onto the image
		SwingUtilities.paintComponent(image.createGraphics(), panel,
				new JPanel(), 0, 0, image.getWidth(), image.getHeight());
		// save the image to file
		ImageIO.write((RenderedImage) image, "png", new File("html3.png"));
		System.out.println("www");
	}
}
