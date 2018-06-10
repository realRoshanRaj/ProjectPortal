package googleAPI.TextToSpeech;

import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class TranslatorFromEnglish {
	String languageCode;
	String language;
	String text;
	String[] langToCode = algorithm.Algorithms.getInstance().languageCode;

	public TranslatorFromEnglish(String text) throws IOException {
		this.text = text;
		this.languageCode = GoogleTranslate.detectLanguage(text);
		this.language = GoogleTranslate.getDisplayLanguage(this.languageCode);
	}

	public TranslatorFromEnglish(String text, String lang, Type type, int index) throws IOException {
		this.text = text;
		if (type.equals(Type.LANGUAGE)) {
			this.language = lang;
			this.languageCode = langToCode[index];
		} else {
			this.languageCode = lang;
			this.language = GoogleTranslate.getDisplayLanguage(languageCode);
		}

	}

	public static enum Type {
		LANGUAGE_CODE, LANGUAGE;
	}

	// public static void main(String[] args) {

	// try {
	// System.out.println(new TranslatorFromEnglish);

	// English to IGBO
	// System.out.println(GoogleTranslate.translate("ig", "how are you"));
	//
	// //English to GREEK
	// System.out.println(GoogleTranslate.translate("el", "how are you"));
	//
	// //English to HAUSA
	// System.out.println(GoogleTranslate.translate("ha", "how are you"));
	//
	// //English to Yoruba
	// System.out.println(GoogleTranslate.translate("yo", "how are you"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

	// }

	public String getTranslatedText() throws IOException {
		return GoogleTranslate.translate("en", languageCode, text);
	}

}
