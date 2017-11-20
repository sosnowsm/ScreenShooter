package sosnowsm;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;

public class GreetingClient {
	static //Image newimg;
	BufferedImage bimg;
	//byte[] bytes;

	public static void main(String[] args) throws InterruptedException {
		String serverName = "192.168.0.180";
		int port = 6066;
		try {

			Socket client = new Socket(serverName, port);
			Robot bot;
			bot = new Robot();
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rect = new Rectangle(dimension);
			int i = 0;

			while (true) {
				System.out.println("Client 1 " + i);
				bimg = bot.createScreenCapture(rect);
				if (bimg != null) {
				ImageIO.write(bimg, "PNG", client.getOutputStream());
				Thread.sleep(1000);
				i=i+1000;}
				i++;

			}

		} catch (IOException | AWTException e) {
			e.printStackTrace();
		}
	}
}
