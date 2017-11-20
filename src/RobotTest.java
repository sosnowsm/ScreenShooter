import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


import javax.imageio.ImageIO;

public class RobotTest {
	Robot robot;

	public RobotTest() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.err.println("Co ten robot wyprawia?!");
			e.printStackTrace();
		}
	}

	/**
	 * Metoda robi screenshot ekranu i zapisuje go na dysku
	 * @throws InterruptedException 
	 */
	public void screenCapture() throws InterruptedException {
		// pobieramy rozmiar ekranu i tworzymy Rectangle
		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		Rectangle screenRect = new Rectangle(0, 0, 0, 0);
		for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
			screenRect = screenRect.union(gd.getDefaultConfiguration().getBounds());
		}
		for (int i=0; i<1000; i++) {
		Date date = new Date();
		String name = (date.toString().replace(" ", "-").replace(":", "-"));
		BufferedImage screen = robot.createScreenCapture(screenRect);
		Thread.sleep(2000);
		// Rectangle rectangle = new Rectangle(dimension);
		// robimy screenshot z utworzonego obszaru
		// BufferedImage screen = robot.createScreenCapture(rectangle);
		try {
			ImageIO.write(screen, "jpg", new File(name + "screenshot.jpg"));
		} catch (IOException e) {
			System.err.println("B³¹d zapisu obrazu");
			e.printStackTrace();
		}
	}
	}
	/**
	 * Testujemy czy dzia³a
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		RobotTest test = new RobotTest();
		//test.robot.delay(3000);

		test.screenCapture();
	}

}