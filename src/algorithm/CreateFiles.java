package algorithm;

import java.io.IOException;

public class CreateFiles {

	final String path = System.getProperty("user.dir") + java.io.File.separator;

	public static enum File {
		WEBSITE, ACCOUNT_INFO, BOTH;
	}

	public CreateFiles(File en) throws IOException {
		switch (en) {
		case WEBSITE:
			createWebsite();
			break;
		case ACCOUNT_INFO:
			createAccountInfo();
			break;
		case BOTH:
			createAccountInfo();
			createWebsite();
			break;
		}
	}

	private void createWebsite() throws IOException {
		java.io.File file = new java.io.File(path + "websites.txt");

		if (!file.exists()) {
			file.createNewFile();
			java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileWriter(path + "websites.txt"));
			out.println(0);
			out.close();
		}
	}

	private void createAccountInfo() throws IOException {
		java.io.File file = new java.io.File(path + "accountInfo.txt");
		if (!file.exists()) {
			file.createNewFile();
			java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileWriter(path + "accountInfo.txt"));
			out.println();
			out.close();
		}
	}

	public static void main(String[] a) {
		try {
			new CreateFiles(CreateFiles.File.WEBSITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
