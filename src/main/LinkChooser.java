package main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.KeyStroke;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import algorithm.Algorithms;
import algorithm.Algorithms.Action;
import googleAPI.SpeechRecognition.SpeechRecognition;
import internet.InternetWindow;
import popups.ErrorPopup;

/**
 *
 * @author Roshan
 */
@SuppressWarnings({ "rawtypes", "serial", "unused" })
public class LinkChooser extends javax.swing.JFrame {

	/**
	 * Creates new form LinkChooser
	 */
	private String email, password;
	private Object[] options;
	private WebDriver driver;
	private Robot bot;
	private SpeechRecognition normalSpeechRec, confirm;
	Browser browser = Browser.CHROME;

	public LinkChooser(String email, char[] password) {
		this.email = email;
		this.password = new String(password);
		// initDictionary();
		initComponents();
	}

	public LinkChooser(String email, String password) {
		this.email = email;
		this.password = password;
		// initDictionary();
		initComponents();
	}

	private void initDictionary() {
	}

	public enum Browser {
		CHROME;
	}

	public void updateModel() {
		options = algorithm.Algorithms.getInstance().nameToURL.keySet().toArray();
		Arrays.sort(options);
		jList1.setModel(new javax.swing.AbstractListModel<Object>() {
			Object[] strings = options;

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		try {
			bot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<>();
		titleLabel = new javax.swing.JLabel();
		nextBttn = new javax.swing.JButton();
		closeBttn = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		commandArea = new javax.swing.JTextArea();
		startBttn = new javax.swing.JButton();
		stopBttn = new javax.swing.JButton();
		addBttn = new javax.swing.JButton();
		deleteBttn = new javax.swing.JButton();
		updateBttn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		updateModel();
		jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jScrollPane1.setViewportView(jList1);
		jList1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					// int index = list.locationToIndex(evt.getPoint());
					// System.out.println("double click");
					nextBttnActionPerformed();
				} else if (evt.getClickCount() == 3) {

					// Triple-click detected
					// int index = list.locationToIndex(evt.getPoint());
					// System.out.println("triple click");
				}
			}
		});

		titleLabel.setFont(new java.awt.Font("sansserif", 0, 28)); // NOI18N
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titleLabel.setText("Enter/Say a Command Below");

		nextBttn.setText("Next");
		nextBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextBttnActionPerformed();
			}
		});
		nextBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		nextBttn.registerKeyboardAction(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextBttnActionPerformed();
			}

		}, KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		closeBttn.setText("Close");
		closeBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeBttnActionPerformed(evt);
			}
		});
		closeBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		commandArea.setColumns(20);
		commandArea.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
		commandArea.setRows(5);
		jScrollPane2.setViewportView(commandArea);

		startBttn.setText("START");
		startBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startBttnActionPerformed(evt);
			}
		});

		stopBttn.setText("STOP");
		stopBttn.setEnabled(false);
		stopBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopBttnActionPerformed(evt);
			}
		});

		// darkTheme();

		addBttn.setText("Add");
		addBttn.setActionCommand("Add");
		addBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBttnActionPerformed(evt);
			}
		});

		deleteBttn.setText("Delete");
		deleteBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteBttnActionPerformed(evt);
			}
		});

		updateBttn.setText("Update");
		updateBttn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBttnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jScrollPane2)
						.addGroup(layout.createSequentialGroup()
								.addComponent(startBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 176,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177,
										Short.MAX_VALUE)
								.addComponent(stopBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 176,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(nextBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 176,
												Short.MAX_VALUE)
										.addComponent(addBttn, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(updateBttn, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(closeBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 176,
												Short.MAX_VALUE)
										.addComponent(deleteBttn, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(startBttn).addComponent(stopBttn))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(addBttn).addComponent(deleteBttn).addComponent(updateBttn))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(nextBttn).addComponent(closeBttn))
						.addGap(11, 11, 11)));
		startBttn.doClick();
		pack();
	}// </editor-fold>

	private void nextBttnActionPerformed() {

		if (stopBttn.isEnabled()) {
			stopBttn.doClick();
		}
		switch (browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--log-level=3");
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			break;
		default:
			break;
		}
		// System.setProperty("webdriver.chrome.args", "--disable-logging");
		// System.setProperty("webdriver.chrome.silentOutput", "true");
		// System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("--log-level=3");
		// options.addArguments("--incognito");

		if (!jList1.isSelectionEmpty()) {
			commandArea.setText("open " + jList1.getSelectedValue());
		}
		Action action = Algorithms.getInstance().mainStringAlgorithm(commandArea.getText().toLowerCase());
		String str = Algorithms.getInstance().getGeneratedURL(jList1, action, commandArea.getText().toLowerCase());
		(new InternetWindow(driver, str)).run();

		if (jList1.getSelectedValue() != null) {
			doAction(jList1.getSelectedValue().toString());
		}
		jList1.clearSelection();

	}

	private void closeBttnActionPerformed(java.awt.event.ActionEvent evt) {

		this.dispose();
	}

	private void addBttnActionPerformed(java.awt.event.ActionEvent evt) {

		new NewSiteWindow(false).setVisible(true);
	}

	private void updateBttnActionPerformed(ActionEvent evt) {
		if (!jList1.isSelectionEmpty()) {
			String str = jList1.getSelectedValue().toString();
			new NewSiteWindow(str, true).setVisible(true);
		} else {
			new ErrorPopup("Please select a value to update").setVisible(true);
		}
	}

	private void deleteBttnActionPerformed(java.awt.event.ActionEvent evt) {
		// Algorithms.getInstance().nameToURL.remove(jList1.getSelectedValue());
		if (!jList1.isSelectionEmpty()) {
			int row = Algorithms.getInstance().findRow(Algorithms.getInstance().allData, 0, jList1.getSelectedValue());
			String[][] finalArray = Algorithms.getInstance().removeRow(Algorithms.getInstance().allData, row);
			Algorithms.getInstance().allData = finalArray;
			Algorithms.getInstance().updateDictionaryFromArray();
			updateModel();
			Algorithms.getInstance().updateTextFile();
		} else {
			new ErrorPopup("Select an item to delete.").setVisible(true);
		}
	}

	private void startBttnActionPerformed(java.awt.event.ActionEvent evt) {

		normalSpeechRec = new SpeechRecognition();
		normalSpeechRec.duplexListener(commandArea);
		startBttn.setEnabled(false);
		stopBttn.setEnabled(true);
	}

	private void stopBttnActionPerformed(java.awt.event.ActionEvent evt) {
		startBttn.setEnabled(true);
		stopBttn.setEnabled(false);
		normalSpeechRec.mic.close();
		normalSpeechRec.duplex.stopSpeechRecognition();
		// TextToSpeech confirmationMsg = new TextToSpeech("Did you say " +
		// commandArea.getText());
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		//
		// e.printStackTrace();
		// }
		// confirm = new SpeechRecognition();
		// confirm.duplexListener();
		// confirm.autoStopTimeThreshold = 6.00000;
		// confirm.autoEnd();
		// String str = confirm.getMessage();
		// System.out.println("THE conf FINALLLLL: " + str);

	}

	private void doAction(String str) {
		int index = Algorithms.getInstance().findRow(Algorithms.getInstance().allData, 0, str);
		String[] options = { "Mistar" };
		String finalStr = str;
		for (String x : options) {
			if (finalStr.equals(x)) {
				break;
			} else {
				finalStr = algorithm.Algorithms.getInstance().getCategory(finalStr);
			}
		}
		switch (finalStr) {
		case "Mistar":
			driver.findElement(By.id("Pin")).sendKeys(this.email);
			driver.findElement(By.id("Password")).sendKeys(this.password);
			driver.findElement(By.id("LoginButton")).click();
			while (driver.getCurrentUrl().equals("https://mistar.oakland.k12.mi.us/novi/StudentPortal/")) {

			}
			driver.findElement(By.id("stuBannerContainerTable")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String[] tableData = null;
			try {
				tableData = driver.findElement(By.id("AssignmentsTable")).getText().split("\n");
			} catch (Exception e) {
				driver.findElement(By.id("SPDynamic_361")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				tableData = driver.findElement(By.id("AssignmentsTable")).getText().split("\n");
			}
			ArrayList<String> finalGradeArray = new ArrayList<String>();
			for (String i : tableData) {
				if (i.startsWith("Per: ")) {
					if (i.contains("(")) {
						String thing = "";
						char[] x = i.toCharArray();
						for (Character y : x) {
							if (y.equals('(')) {
								break;
							}
							thing += y;
						}
						String grade = (tableData[Algorithms.getInstance().find(tableData, i) + 1]).split("        ")[0]
								.split(":")[1].trim();
						finalGradeArray.add(thing + grade);
					} else {
						String grade = (tableData[Algorithms.getInstance().find(tableData, i) + 1]).split("        ")[0]
								.split(":")[1].trim();
						finalGradeArray.add(i.split("17/18")[0] + grade);
					}
				}
			}
			String everythang = "<html>";
			for (String i : finalGradeArray) {
				everythang += i;
				everythang += "<br>";
			}
			everythang += "</html>";
			new ErrorPopup(everythang).setVisible(true);
			break;
		// case "Facebook":
		// driver.findElement(By.id("email")).sendKeys(this.email);
		// driver.findElement(By.id("pass")).sendKeys(this.password);
		// driver.findElement(By.id("u_0_2")).click();
		// break;
		case "GOOGLE":
			googleSignin(this.email, this.password);
			break;
		case "CUSTOM WITH GOOGLE USER AND PASS":
			driver.findElement(By.id(algorithm.Algorithms.getInstance().allData[index][3])).sendKeys(this.email);
			driver.findElement(By.id(algorithm.Algorithms.getInstance().allData[index][4])).sendKeys(this.password);
			driver.findElement(By.id(algorithm.Algorithms.getInstance().allData[index][5])).click();
			break;
		case "CUSTOM":
			String userID = Algorithms.getInstance().allData[index][3];
			String passwordID = Algorithms.getInstance().allData[index][4];
			String username = Algorithms.getInstance().allData[index][5];
			String password = Algorithms.getInstance().allData[index][6];
			String loginBttnID = Algorithms.getInstance().allData[index][7];
			String old = null;
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			try {
				old = (String) (clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor));
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			if (userID.toUpperCase().contains("DEFAULT/NONE") || userID.isEmpty()) {
				clipboard.setContents(new StringSelection(username), new StringSelection(username));
				bot.keyPress(KeyEvent.VK_CONTROL);
				bot.keyPress(KeyEvent.VK_V);
				bot.keyRelease(KeyEvent.VK_V);
				bot.keyRelease(KeyEvent.VK_CONTROL);
			} else {
				driver.findElement(By.id(userID)).sendKeys(username);
			}
			if (passwordID.toUpperCase().contains("DEFAULT/NONE") || passwordID.isEmpty()) {
				bot.keyPress(KeyEvent.VK_TAB);
				bot.keyRelease(KeyEvent.VK_TAB);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clipboard.setContents(new StringSelection(password), new StringSelection(password));
				bot.keyPress(KeyEvent.VK_CONTROL);
				bot.keyPress(KeyEvent.VK_V);
				bot.keyRelease(KeyEvent.VK_V);
				bot.keyRelease(KeyEvent.VK_CONTROL);
			} else {
				driver.findElement(By.id(passwordID)).sendKeys(password);
			}
			if (loginBttnID.toUpperCase().contains("DEFAULT/NONE") || loginBttnID.isEmpty()) {
				bot.keyPress(KeyEvent.VK_ENTER);
				bot.keyRelease(KeyEvent.VK_ENTER);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clipboard.setContents(new StringSelection(old), new StringSelection(old));
			} else {
				driver.findElement(By.id(loginBttnID)).click();
			}
			break;
		default:
			defaultLogin();
			break;
		}
	}

	private void googleSignin(String email, String password) {
		driver.findElement(By.id("identifierId")).sendKeys(email);
		String uri = driver.getCurrentUrl();
		driver.findElement(By.id("identifierNext")).click();
		while (driver.getCurrentUrl().equals(uri)) {
		}
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("passwordNext")).click();
	}

	private void defaultLogin() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String old = null;
		try {
			old = (String) (clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor));
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}

		clipboard.setContents(new StringSelection(this.email), new StringSelection(this.email));
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_TAB);
		bot.keyRelease(KeyEvent.VK_TAB);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clipboard.setContents(new StringSelection(this.password), new StringSelection(this.password));
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clipboard.setContents(new StringSelection(old), new StringSelection(old));
	}

	private void darkTheme() {
		Color bg = Color.BLACK;
		Color fg = Color.WHITE;
		getContentPane().setBackground(bg);
		commandArea.setBackground(bg);
		commandArea.setForeground(fg);
		closeBttn.setBackground(bg);
		closeBttn.setForeground(fg);
		nextBttn.setBackground(bg);
		nextBttn.setForeground(fg);
		startBttn.setBackground(bg);
		startBttn.setForeground(fg);
		stopBttn.setBackground(bg);
		stopBttn.setForeground(fg);
		titleLabel.setForeground(fg);
		jList1.setForeground(fg);
		jList1.setBackground(bg);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	// public static void main(String args[]) {
	// /* Set the Nimbus look and feel */
	// // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
	// // (optional) ">
	// /*
	// * If Nimbus (introduced in Java SE 6) is not available, stay with the default
	// * look and feel. For details see
	// * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	// */
	// try {
	// for (javax.swing.UIManager.LookAndFeelInfo info :
	// javax.swing.UIManager.getInstalledLookAndFeels()) {
	// if ("Nimbus".equals(info.getName())) {
	// javax.swing.UIManager.setLookAndFeel(info.getClassName());
	// break;
	// }
	// }
	// } catch (ClassNotFoundException ex) {
	// java.util.logging.Logger.getLogger(LinkChooser.class.getName()).log(java.util.logging.Level.SEVERE,
	// null,
	// ex);
	// } catch (InstantiationException ex) {
	// java.util.logging.Logger.getLogger(LinkChooser.class.getName()).log(java.util.logging.Level.SEVERE,
	// null,
	// ex);
	// } catch (IllegalAccessException ex) {
	// java.util.logging.Logger.getLogger(LinkChooser.class.getName()).log(java.util.logging.Level.SEVERE,
	// null,
	// ex);
	// } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	// java.util.logging.Logger.getLogger(LinkChooser.class.getName()).log(java.util.logging.Level.SEVERE,
	// null,
	// ex);
	// }
	// // </editor-fold>
	//
	// /* Create and display the form */
	// java.awt.EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// new LinkChooser("", "").setVisible(true);
	// }
	// });
	// }

	// Variables declaration - do not modify
	private javax.swing.JButton addBttn;
	private javax.swing.JButton closeBttn;
	private javax.swing.JButton updateBttn;
	private javax.swing.JButton deleteBttn;
	private javax.swing.JList<Object> jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea commandArea;
	private javax.swing.JButton nextBttn;
	private javax.swing.JButton startBttn;
	private javax.swing.JButton stopBttn;
	private javax.swing.JLabel titleLabel;
	// End of variables declaration
}
