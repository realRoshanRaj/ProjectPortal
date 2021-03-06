package main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.darkprograms.speech.translator.GoogleTranslate;

import algorithm.Algorithms;
import algorithm.CreateFiles;
import googleAPI.TextToSpeech.TextToSpeech;
import googleAPI.TextToSpeech.TranslatorFromEnglish;
import googleAPI.TextToSpeech.TranslatorToEnglish;
import popups.ErrorPopup;
import validityChecker.EmailCheck;

/**
 *
 * @author Roshan
 */
@SuppressWarnings("serial")
public class StartupWindow extends javax.swing.JFrame {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(StartupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(StartupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(StartupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(StartupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Thread(() -> {
					String targetLang = System.getProperty("user.language");
					String translatedTxt = "Hello and welcome to projekt portal";
					try {
						translatedTxt = GoogleTranslate.translate("en", targetLang, "Welcome to projekt portal!");
					} catch (IOException e) {
						e.printStackTrace();
					}
					welcomeMsg = new TextToSpeech(translatedTxt);
				}).start();
				new StartupWindow().setVisible(true);
			}
		});
	}

	public static LinkChooser linkChooser;

	private javax.swing.JButton clearAll;
	private javax.swing.JButton continueBttn;

	@SuppressWarnings("unused")
	private static TextToSpeech welcomeMsg;

	private javax.swing.JTextField emailField;

	private javax.swing.JLabel emailLabel;

	private javax.swing.JButton exitBttn;

	final String[] lang = Algorithms.getInstance().languages;

	private javax.swing.JComboBox<String> langComboBox;

	private javax.swing.JLabel langLabel;

	private javax.swing.JPasswordField passwordField;

	private javax.swing.JLabel passwordLabel;

	String prevLang = "English", currentLang = "English";

	private javax.swing.JCheckBox rememberMeCheck;

	private javax.swing.JButton showPasswordButton;

	private javax.swing.JLabel title;

	public final static int SHIFT = Math.abs(new Random().nextInt());

	public StartupWindow() {
		// readLangFile();
		try {
			new CreateFiles(CreateFiles.File.BOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initComponents();
	}

	private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {
		passwordField.setText("");
		emailField.setText("");
		rememberMeCheck.setSelected(false);
		rememberMeCheckActionPerformed();
		showPasswordButton.setVisible(true);
	}

	private void continueBttnActionPerformed(java.awt.event.ActionEvent evt) {
		boolean error = false;
		if (passwordField.getPassword().length == 0 || emailField.getText().isEmpty()) {
			new ErrorPopup("Please fill in all fields.").setVisible(true);
		} else if (!new EmailCheck(emailField.getText()).isValidEmail()) {
			new ErrorPopup("Enter Valid Email").setVisible(true);
			error = true;
		} else {
			if (rememberMeCheck.isSelected()) {
				try {
					java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileWriter(
							System.getProperty("user.dir") + java.io.File.separator + "accountInfo.txt"));
					if (!error) {
						// Algorithms.getInstance().setEncryptionNumber(SHIFT);
						String shiftChar = new Integer(SHIFT).toString();
						for (char x : shiftChar.toCharArray()) {
							out.print((char) Integer.parseInt(String.valueOf(x)));
							out.print(" ");
						}
						out.println();
						// out.println(SHIFT);
						out.println(emailField.getText());

						char[] passwordChar = passwordField.getPassword();
						// out.println(passwordChar.length);
						for (char i : passwordChar) {
							out.print(((int) i) + SHIFT);
							out.print(" ");
						}
						out.println();
					}
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				if (new File("accountInfo.txt").exists()) {
					try {
						java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileWriter("accountInfo.txt"));
						out.println();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			this.setVisible(false);
			Algorithms.getInstance().updateTextFile();
			linkChooser = new LinkChooser(emailField.getText(), passwordField.getPassword());
			linkChooser.setVisible(true);
		}
	}

	private void exitBttnActionPerformed(java.awt.event.ActionEvent evt) {
		dispatchEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING));
	}

	private void initComponents() {
		/* GUI */
		title = new javax.swing.JLabel();
		emailLabel = new javax.swing.JLabel();
		passwordLabel = new javax.swing.JLabel();
		showPasswordButton = new javax.swing.JButton();
		emailField = new javax.swing.JTextField();
		passwordField = new javax.swing.JPasswordField();
		continueBttn = new javax.swing.JButton();
		exitBttn = new javax.swing.JButton();
		rememberMeCheck = new javax.swing.JCheckBox();
		clearAll = new javax.swing.JButton();
		langLabel = new javax.swing.JLabel();
		langComboBox = new javax.swing.JComboBox<>();

		setTitle("Project Portal");

		readAccountInfo();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		darkTheme();

		// title.setjava.awt.Font(new java.awt.java.awt.Font("Times New Roman", 0, 24));
		// // NOI18N
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setText("Welcome to Project Portal");
		emailLabel.setText("Email:");

		passwordLabel.setText("Password: ");

		showPasswordButton.setText("Show Password");
		showPasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				showPasswordButtonMousePressed(evt);
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				showPasswordButtonMouseReleased(evt);
			}
		});
		showPasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		continueBttn.setText("Continue");
		continueBttn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				continueBttnActionPerformed(evt);
			}
		});
		continueBttn.registerKeyboardAction(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueBttnActionPerformed(e);
			}

		}, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0),
				javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
		continueBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		exitBttn.setText("Exit");
		exitBttn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitBttnActionPerformed(evt);
			}
		});
		exitBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		rememberMeCheck.setText("Remember Me");
		rememberMeCheck.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Thread(() -> {
					rememberMeCheckActionPerformed();
				}).run();
			}
		});

		clearAll.setText("Clear All");
		clearAll.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearAllActionPerformed(evt);
			}
		});
		clearAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		langLabel.setText("Select Language:");

		langComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(lang));
		langComboBox.setSelectedItem("English");
		langComboBox.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prevLang = currentLang;
				currentLang = langComboBox.getSelectedItem().toString();
				updateALLLangFields();
			}

		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(2, 2, 2).addComponent(rememberMeCheck)
										.addGap(374, 374, 374))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(continueBttn,
														javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
														.addGap(12, 12, 12)
														.addComponent(clearAll, javax.swing.GroupLayout.PREFERRED_SIZE,
																150, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(exitBttn, javax.swing.GroupLayout.PREFERRED_SIZE,
																150, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(langLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
																138, Short.MAX_VALUE)
														.addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(
																passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(passwordField)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(showPasswordButton))
																.addComponent(emailField).addComponent(langComboBox, 0,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))))
										.addGap(18, 18, 18)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(17, 17, 17)
				.addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(langLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(langComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(emailLabel).addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(passwordLabel).addComponent(showPasswordButton).addComponent(passwordField,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(rememberMeCheck)
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(continueBttn)
						.addComponent(exitBttn, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(clearAll))
				.addContainerGap()));

		pack();

		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));

		emailField.requestFocus();
		// setResizable(false);
		// setUndecorated(true);
		setLocationRelativeTo(null);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// while (welcomeMsg.isFinished() == false) {
		// System.out.print(" ");
		//
		// }

		// if (emailField.getText().isEmpty()) {
		// new TextToSpeech("Start by entering your google email and password! Then
		// click continue.");
		// }

		dispatchEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_GAINED_FOCUS));

	}

	private void darkTheme() {
		java.awt.Color fg = java.awt.Color.white;
		java.awt.Color bg = new java.awt.Color(77, 77, 77);
		java.awt.Color sideLabelFG = new java.awt.Color(13, 218, 224);
		java.awt.Color buttonBG = new java.awt.Color(0, 128, 129);
		getContentPane().setBackground(bg);
		title.setForeground(fg);
		title.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24));
		javax.swing.JLabel[] label = { emailLabel, passwordLabel, langLabel };
		for (javax.swing.JLabel x : label) {
			x.setForeground(sideLabelFG);
			x.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
		}
		rememberMeCheck.setForeground(sideLabelFG);
		rememberMeCheck.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
		javax.swing.JButton[] button = { clearAll, continueBttn, exitBttn, showPasswordButton };
		for (javax.swing.JButton x : button) {
			x.setBackground(buttonBG);
			x.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
		}
		langComboBox.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
		emailField.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
	}

	private void readAccountInfo() {
		try {
			@SuppressWarnings("resource")
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader("accountInfo.txt"));
			int shift = 0;
			String[] shiftArray = in.readLine().split(" ");
			String finalShift = "";
			for (String s : shiftArray) {
				finalShift += (int) (s.toCharArray()[0]);
			}

			shift = Integer.parseInt(finalShift);
			Algorithms.getInstance().setEncryptionNumber(shift);

			// Algorithms.getInstance().updateDictionaryFromArray();

			String email = in.readLine();
			emailField.setText(email);

			String finalPass = "";
			String[] intForm = in.readLine().split(" ");
			for (String x : intForm) {
				finalPass += (char) (Integer.parseInt(x) - shift);
			}
			passwordField.setText(finalPass);
			rememberMeCheck.setSelected(true);
			showPasswordButton.setVisible(false);
		} catch (Exception e) {
			System.out.println("error");
		}
		Algorithms.getInstance().readWebsiteItems();
	}

	private void rememberMeCheckActionPerformed() {
	}

	private void showPasswordButtonMousePressed(java.awt.event.MouseEvent evt) {

		passwordField.setEchoChar((char) 0);
		try {
			showPasswordButton.setText(new TranslatorFromEnglish("Hide Password",
					langComboBox.getSelectedItem().toString(), TranslatorFromEnglish.Type.LANGUAGE,
					Algorithms.getInstance().find(lang, langComboBox.getSelectedItem().toString()))
							.getTranslatedText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showPasswordButtonMouseReleased(java.awt.event.MouseEvent evt) {
		passwordField.setEchoChar('*');
		try {
			showPasswordButton.setText(new TranslatorFromEnglish("Show Password",
					langComboBox.getSelectedItem().toString(), TranslatorFromEnglish.Type.LANGUAGE,
					Algorithms.getInstance().find(lang, langComboBox.getSelectedItem().toString()))
							.getTranslatedText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		passwordField.requestFocus();
	}

	private void updateALLLangFields() {
		javax.swing.JLabel[] label = { emailLabel, passwordLabel, langLabel, title };
		for (javax.swing.JLabel x : label) {
			Thread t = new Thread(() -> {
				updateLanguageField(x, prevLang, langComboBox.getSelectedItem().toString());
			});
			t.setDaemon(false);
			t.start();
		}
		javax.swing.JButton[] button = { clearAll, continueBttn, exitBttn, showPasswordButton };
		for (javax.swing.JButton x : button) {
			Thread t = new Thread(() -> {
				updateLanguageField(x, prevLang, langComboBox.getSelectedItem().toString());
			});
			t.setDaemon(false);
			t.start();
		}
		javax.swing.JCheckBox[] check = { rememberMeCheck };
		for (javax.swing.JCheckBox x : check) {
			new Thread(() -> {
				updateLanguageField(x, prevLang, langComboBox.getSelectedItem().toString());
			}).start();
		}
		setSize(langComboBox.getWidth() + 186, getHeight());
	}

	private void updateLanguageField(javax.swing.JButton label, String startLang, String endLang) {
		String fromEng, toEng;
		try {
			toEng = new TranslatorToEnglish(label.getText(), GoogleTranslate.detectLanguage(label.getText()),
					TranslatorToEnglish.Type.LANGUAGE_CODE, 0).getTranslatedText();
			fromEng = new TranslatorFromEnglish(toEng, endLang, TranslatorFromEnglish.Type.LANGUAGE,
					Algorithms.getInstance().find(lang, endLang)).getTranslatedText();
			label.setText(fromEng);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void updateLanguageField(javax.swing.JCheckBox label, String startLang, String endLang) {
		String fromEng, toEng;
		try {
			toEng = new TranslatorToEnglish(label.getText(), GoogleTranslate.detectLanguage(label.getText()),
					TranslatorToEnglish.Type.LANGUAGE_CODE, 0).getTranslatedText();
			fromEng = new TranslatorFromEnglish(toEng, endLang, TranslatorFromEnglish.Type.LANGUAGE,
					Algorithms.getInstance().find(lang, endLang)).getTranslatedText();

			label.setText(fromEng);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateLanguageField(javax.swing.JLabel label, String startLang, String endLang) {
		String fromEng, toEng;
		try {
			toEng = new TranslatorToEnglish(label.getText(), GoogleTranslate.detectLanguage(label.getText()),
					TranslatorToEnglish.Type.LANGUAGE_CODE, 0).getTranslatedText();
			fromEng = new TranslatorFromEnglish(toEng, endLang, TranslatorFromEnglish.Type.LANGUAGE,
					Algorithms.getInstance().find(lang, endLang)).getTranslatedText();
			label.setText(fromEng);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
