package sosnowsm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GreetingServer extends Thread {
	private ServerSocket serverSocket;

	ImageIcon icon = null;
	JButton ekran;
	Socket server;

	public GreetingServer(int port) throws IOException, SQLException, ClassNotFoundException, Exception {
		serverSocket = new ServerSocket(port);
		//serverSocket.setSoTimeout(180000);
	}

	public void go() throws Exception {

	}

	public void run() {
		BufferedImage img;
		ImageIcon obr;
		JFrame frame = new JFrame();
		icon = new ImageIcon("images/initial.jpg");
		JButton ekran = new JButton(icon);
		frame.getContentPane().add(ekran);
		frame.pack();
		frame.setVisible(true);

		try {
			server = serverSocket.accept();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int i = 0;
		while (true) {
			try {
				while (true) {
					i++;
					System.out.println("BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));"+ i);
					img = null;
					Thread.sleep(1000);
					img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
					
					if (img != null) {


					obr = new ImageIcon(img.getScaledInstance(683, 384, 1));
					ekran.setIcon(obr);}
					i=i+1000;

				}

			} catch (SocketTimeoutException st) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, Exception {
		// new GreetingServer(6066).go();
		Thread t = new GreetingServer(6066);
		t.start();
	}
}