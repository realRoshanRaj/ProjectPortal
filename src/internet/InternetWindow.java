package internet;

import org.openqa.selenium.WebDriver;

public class InternetWindow implements Runnable {
	private WebDriver driver;
	private String url;

	public InternetWindow(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
	}

	@Override
	public void run() {
		driver.manage().window().setPosition(new org.openqa.selenium.Point(0, 0));
		driver.switchTo().defaultContent();
		driver.manage().window().maximize();
		driver.get(url);
	}

}
