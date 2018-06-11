package popups;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

public class SystemTray {
	public SystemTray(String first, String second, MessageType x) throws AWTException, MalformedURLException {
		// Obtain only one instance of the SystemTray object
		java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
		// If the icon is a file
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png"));
		// Alternative (if the icon is on the classpath):
		// Image image =
		// Toolkit.getDefaultToolkit().createImage(getClass().getResource("frogForce.png"));

		TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
		// Let the system resize the image if needed
		trayIcon.setImageAutoSize(true);
		// Set tooltip text for the tray icon
		trayIcon.setToolTip(first);
		tray.add(trayIcon);

		trayIcon.displayMessage(first, second, x);

	}

	public static void main(String[] a) {
		try {
			new SystemTray("Does it", "work", MessageType.NONE);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
