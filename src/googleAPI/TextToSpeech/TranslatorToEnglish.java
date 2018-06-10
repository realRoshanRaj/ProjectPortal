package googleAPI.TextToSpeech;

import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class TranslatorToEnglish {
	String languageCode;
	String language;
	String text;
	final String[] langToCode = algorithm.Algorithms.getInstance().languageCode;

	public TranslatorToEnglish(String text) throws IOException {
		this.text = text;
		this.languageCode = GoogleTranslate.detectLanguage(text);
		this.language = GoogleTranslate.getDisplayLanguage(this.languageCode);
	}

	public TranslatorToEnglish(String text, String lang, Type type, int index) throws IOException {
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

	// Read this ma bro :)
	// When you go on google translate website and you translate from English to
	// Igbo for example
	// you can see the url is :
	// https://translate.google.com/#en/ig/How%20are%20you
	// so the code for IGBO is "ig"
	// see my examples below , i will make tutorial on youtube don't worry

	// try {
	// System.out.println(new TranslatorToEnglish);

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
		return GoogleTranslate.translate(languageCode, "en", text);
	}

}
