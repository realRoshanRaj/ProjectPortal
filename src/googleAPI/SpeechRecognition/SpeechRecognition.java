package googleAPI.SpeechRecognition;

import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class SpeechRecognition {
	String finalMessage = "";
	boolean isFinished = false;
	public final Microphone mic = new Microphone(FLACFileWriter.FLAC);

	public GSpeechDuplex duplex;;
	String langCode = "en";
	public double autoStopTimeThreshold = 4.5000000000;
	private long start;
	public boolean autoEndThreadFinished = false;
	private String API = "AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw";
	private final String[] stopPhrases = { "cancel", "exit", "stop" };

	private String prevMsg = "";

	public SpeechRecognition(String API, String langCode) {
		// Don't use the below google api key , make your own !!! :)
		this.langCode = langCode;
		duplex = new GSpeechDuplex(this.API);
		duplex.setLanguage(this.langCode);
		 run();
	}

	public SpeechRecognition() {
		duplex = new GSpeechDuplex(this.API);
		duplex.setLanguage(this.langCode);
		 run();
	}

	public SpeechRecognition(String API) {
		duplex = new GSpeechDuplex(this.API);
		duplex.setLanguage(this.langCode);
		 run();
	}

	public void autoEnd() {
		start = System.nanoTime();
		autoEndThreadFinished = false;
		new Thread(() -> {
			while (true) {
				double elapsedTime = TimeUnit.SECONDS.convert(
						algorithm.Algorithms.getInstance().getElapsedTime(start, System.nanoTime()),
						TimeUnit.NANOSECONDS);
				// System.out.println(elapsedTime);
				if (elapsedTime >= autoStopTimeThreshold) {
					autoEndThreadFinished = true;
					System.out.println("AUTO STOPPING " + autoEndThreadFinished);
					
					 this.duplex.stopSpeechRecognition();
					 this.mic.close();
					break;
				}
			}

		}).start();

	}

	private void run() {

		new Thread(() -> {
			try {
				duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}).start();

		// duplexListener();

	}

	public void duplexListener(JTextArea response) {
		duplex.addResponseListener(new GSpeechResponseListener() {
			String old_text = "";

			public void onResponse(GoogleResponse gr) {
				prevMsg = finalMessage;
				String output = "";
				output = gr.getResponse();
				boolean cont = true;
				for (String x : stopPhrases) {
					if (output.contains(x)) {
						cont = false;
					}
				}
//				System.out.println(cont);
				if (cont) {
					if (gr.getResponse() == null) {
						this.old_text = response.getText();
						if (this.old_text.contains("(")) {
							this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
						}
						System.out.println("Paragraph Line Added");
						this.old_text = (response.getText() + "\n");
						this.old_text = this.old_text.replace(")", "").replace("( ", "");
						response.setText(this.old_text);
						return;
					}
					if (output.contains("(")) {
						output = output.substring(0, output.indexOf('('));
					}
					if (!gr.getOtherPossibleResponses().isEmpty()) {
						output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
					}
					System.out.println(getMessage());
					response.setText("");
					response.append(this.old_text);
					response.append(output);
					finalMessage = response.getText();
					start = System.nanoTime();
				}
				// isFinished = true;
				// System.out.println("IS FIN" + isFinished());
			}

		});
	}

	public void duplexListener() {
		duplex.addResponseListener(new GSpeechResponseListener() {
			String old_text = "";

			public void onResponse(GoogleResponse gr) {
				prevMsg = finalMessage;
				String output = "";
				output = gr.getResponse();
				boolean cont = true;
				for (String x : stopPhrases) {
					if (output.contains(x)) {
						cont = false;
					}
				}
				System.out.println(cont);
				if (cont) {
					if (gr.getResponse() == null) {
						// this.old_text = response.getText();
						if (this.old_text.contains("(")) {
							this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
						}
						System.out.println("Paragraph Line Added");
						// this.old_text = ("\n" );
						// this.old_text = this.old_text.replace(")", "").replace("( ", "");
						return;
					}
					if (output.contains("(")) {
						output = output.substring(0, output.indexOf('('));
					}
					if (!gr.getOtherPossibleResponses().isEmpty()) {
						output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
					}
					// response.setText("");
					// response.append(this.old_text);
					// response.append(output);

					finalMessage = output;
					System.out.println("MSG: " + getMessage());
				}
				// isFinished = true;
				// System.out.println("IS FIN" + isFinished());
				// mic.close();
				// duplex.stopSpeechRecognition();
			}
		});
	}

	public String getMessage() {
		
		if (finalMessage.isEmpty()) {
			return prevMsg;
		}
		for(String x : stopPhrases) {
			if(finalMessage.contains(x)) {
				return prevMsg;
			}
		}
		return finalMessage;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public static void main(String[] a) {
		new SpeechRecognition("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
	}
}
