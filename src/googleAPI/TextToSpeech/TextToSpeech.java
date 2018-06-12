package googleAPI.TextToSpeech;

import java.io.IOException;

import com.darkprograms.speech.synthesiser.SynthesiserV2;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * @author Roshan
 *
 */
public class TextToSpeech {

	// Create a Synthesizer instance
	SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
	boolean isFinished = false;

	public TextToSpeech(String text) {
		// synthesizer.setPitch(1);
		synthesizer.setSpeed(0.8);

		speak(text);

		// Let's speak in English
		// speak("Hello Dear Friend !");

	}

	private void speak(String text) {
		System.out.println(text);

		Thread thread = new Thread(() -> {
			try {

				// Create a JLayer instance
				AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
				player.play();

				isFinished = true;
				System.out.println("Successfully got back synthesizer data");

			} catch (IOException | JavaLayerException e) {

				e.printStackTrace();

			}
		});

		thread.start();

	}

	public boolean isFinished() {
		return isFinished;
	}

	public static void main(String[] args) {
		new TextToSpeech("Hello, how are you?");
	}

}
