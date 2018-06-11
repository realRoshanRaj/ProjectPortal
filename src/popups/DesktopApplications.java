package popups;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class DesktopApplications {

	public DesktopApplications(String pathname) {
		try {
			Desktop.getDesktop().open(new File(pathname));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new ErrorPopup("Application does NOT exist in given path.").setVisible(true);
		}
	}

	public static void main(String[] a) throws IOException {
		new DesktopApplications("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	}
}
