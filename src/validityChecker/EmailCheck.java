package validityChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheck {
	String email;

	public EmailCheck(String email) {
		this.email = email;
	}
	
	public boolean isValidEmail() {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher mat = pattern.matcher(email);
		if (mat.matches()) {

			System.out.println("Valid email address");
		} else {

			System.out.println("Not a valid email address");
		}
		
		return mat.matches();
		
	}
	
	public static void main(String[] args) {

		String email = "vivek.mitra@gmail.com";
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher mat = pattern.matcher(email);

		if (mat.matches()) {

			System.out.println("Valid email address");
		} else {

			System.out.println("Not a valid email address");
		}
	}
}
