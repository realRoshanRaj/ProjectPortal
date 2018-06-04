/**
 * Algorithms by realRoshanRaj
 */
package algorithm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import main.StartupWindow;

/**
 * @author Roshan
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Algorithms {
	private final static Algorithms instance = new Algorithms();

	public final String[] languages = new String[] { "Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian",
			"Azeerbaijani", "Basque", "Belarusian", "Bengali", "Bosnian", "Bulgarian", "Catalan", "Cebuano",
			"Chinese (Simplified)", "Chinese (Traditional)", "Corsican", "Croatian", "Czech", "Danish", "Dutch",
			"English", "Esperanto", "Estonian", "Finnish", "French", "Frisian", "Galician", "Georgian", "German",
			"Greek", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian", "Hebrew", "Hindi", "Hmong", "Hungarian",
			"Icelandic", "Igbo", "Indonesian", "Irish", "Italian", "Japanese", "Javanese", "Kannada", "Kazakh", "Khmer",
			"Korean", "Kurdish", "Kyrgyz", "Lao", "Latin", "Latvian", "Lithuanian", "Luxembourgish", "Macedonian",
			"Malagasy", "Malay", "Malayalam", "Maltese", "Maori", "Marathi", "Mongolian", "Myanmar (Burmese)", "Nepali",
			"Norwegian", "Nyanja (Chichewa)", "Pashto", "Persian", "Polish", "Portuguese (Portugal, Brazil)", "Punjabi",
			"Romanian", "Russian", "Samoan", "Scots Gaelic", "Serbian", "Sesotho", "Shona", "Sindhi",
			"Sinhala (Sinhalese)", "Slovak", "Slovenian", "Somali", "Spanish", "Sundanese", "Swahili", "Swedish",
			"Tagalog (Filipino)", "Tajik", "Tamil", "Telugu", "Thai", "Turkish", "Ukrainian", "Urdu", "Uzbek",
			"Vietnamese", "Welsh", "Xhosa", "Yiddish", "Yoruba", "Zulu" };
	public final String[] languageCode = new String[] { "af", "sq", "am", "ar", "hy", "az", "eu", "be", "bn", "bs",
			"bg", "ca", "ceb", "zh-CN", "zh-TW", "co", "hr", "cs", "da", "nl", "en", "eo", "et", "fi", "fr", "fy", "gl",
			"ka", "de", "el", "gu", "ht", "ha", "haw", "iw", "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja",
			"jw", "kn", "kk", "km", "ko", "ku", "ky", "lo", "la", "lv", "lt", "lb", "mk", "mg", "ms", "ml", "mt", "mi",
			"mr", "mn", "my", "ne", "no", "ny", "ps", "fa", "pl", "pt", "pa", "ro", "ru", "sm", "gd", "sr", "st", "sn",
			"sd", "si", "sk", "sl", "so", "es", "su", "sw", "sv", "tl", "tg", "ta", "te", "th", "tr", "uk", "ur", "uz",
			"vi", "cy", "xh", "yi", "yo", "zu" };

	public final Map nameToURL = new HashMap<String, String>();
	public final Map nameToCategory = new HashMap<String, String>();
	public String[][] allData;

	int encryptionNum;

	public Algorithms() {
	}

	public static enum Action {
		OPEN, SEARCH, WEB_ADDRESS;
	}

	public Action mainStringAlgorithm(String str) {
		str = str.trim();
		if (str.toLowerCase().endsWith(".com")) {
			return Action.WEB_ADDRESS;
		}
		if (str.toLowerCase().contains("open ")) {
			return Action.OPEN;
		}
		return Action.SEARCH;
	}

	public String getGeneratedURL(javax.swing.JList list, Action act, String str) {
		str = str.toLowerCase();
		if (act.equals(Action.SEARCH)) {
			return getSearchURL(str);
		} else if (act.equals(Action.OPEN)) {
			return getOpenURL(list, str);
		} else if (act.equals(Action.WEB_ADDRESS)) {
			return getWebURL(str);
		}
		return str;
	}

	private String getOpenURL(javax.swing.JList list, String str) {
		str = str.trim();
		String finalStr = "";
		str = str.toLowerCase();
		String[] split = str.split(" ");
		int index = find(split, "open");
		try {
			str = split[index + 1];
			list.setSelectedValue(toTitleCase(str), true);
			finalStr = nameToURL.get(toTitleCase(str)).toString();
		} catch (Exception e) {
			for (int x = index; x < split.length; x++) {
				finalStr += split[x] + " ";
			}
		}
		return finalStr;
	}

	public String getCategory(String str) {
		try {

			// if (nameToCategory.get(str).toString().equals("CUSTOM")) {
			// return str;
			// } else {
			str = nameToCategory.get(str).toString();
			// }
			System.out.println("reached");
		} catch (Exception e) {
			e.getMessage();
		}
		return str;
	}

	private String getSearchURL(String str) {
		String url = "https://www.google.com/search?q=" + str;
		return url;
	}

	private String getWebURL(String str) {
		str = str.trim();
		if (!str.startsWith("https://")) {
			str = "https://" + str;
		}
		return str;
	}

	public void updateTextFile() {
		try {
			if (!new File("websites.txt").exists()) {
				new File("websites.txt").createNewFile();
			}
			java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileWriter("websites.txt"));
			int size = nameToURL.size();
			out.println(size);
			for (int iterator = 0; iterator < size; iterator++) {
				for (int i = 0; i < allData[iterator].length; i++) {
					if (i == 6) {
						if (allData[iterator][2].equals("CUSTOM")) {
							out.print(instance.encryptText(allData[iterator][i]));
						}
					} else {
						out.print(allData[iterator][i]);
					}
					if (i != allData[iterator].length - 1) {
						out.print(",");
					}

				}
				out.println();
			}
			// out.print(nameToURL.keySet().toArray(new String[size])[iterator]);
			// out.print(",");
			// out.print(nameToURL.values().toArray(new String[size])[iterator]);
			// out.print(",");
			// out.print(nameToCategory.values().toArray(new String[size])[iterator]);
			//
			// switch (nameToCategory.values().toArray(new
			// String[size])[iterator].toString()) {
			// case "GOOGLE":
			// break;
			// case "CUSTOM":
			// case "CUSTOM WITH GOOGLE USER AND PASS":
			// for (int i = 0; i < allData[iterator].length; i++) {
			// out.print(allData[iterator][i]);
			// if(i != allData[iterator].length-1) {
			// out.print(",");
			// }
			// }
			// break;
			// }

			// out.println(nameToCategory.values().toArray(new String[size])[iterator]);

			out.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void updateDictionaryFromArray() {
		nameToURL.clear();
		nameToCategory.clear();
		for (String[] x : allData) {
			nameToCategory.put(x[0], x[2]);
			nameToURL.put(x[0], x[1]);
		}

	}

	public int find(Object[] objects, Object target) {
		// Arrays.asList(a).indexOf(target);
		for (int i = 0; i < objects.length; i++)
			if (target.equals(objects[i]))
				return i;

		return -1;
	}

	public int findRow(final Object[][] objects, final int n, final Object target) {
		for (int i = 0; i < Algorithms.getInstance().allData.length; i++) {
			if (Algorithms.getInstance().allData[i][n].equals(target)) {
				return i;
			}
		}
		return -1;

	}

	public String[][] removeRow(String[][] mat, int row) {
		String[][] realCopy = new String[mat.length - 1][mat[0].length];
		int count = 0;
		if (row == mat.length - 1) {
			for (int r = 0; r < mat.length - 1; r++) {
				for (int c = 0; c < mat[0].length; c++) {
					if (row == r)
						r++;
					realCopy[count][c] = mat[r][c];
				}
				count++;
			}
		} else {
			for (int r = 0; r < mat.length; r++) {
				for (int c = 0; c < mat[0].length; c++) {
					if (row == r)
						r++;
					realCopy[count][c] = mat[r][c];
				}
				count++;
			}
		}
		return realCopy;
	}

	public String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			} else {
				c = Character.toLowerCase(c);
			}

			titleCase.append(c);
		}

		return titleCase.toString();
	}

	public long getElapsedTime(long start, long end) {
		return (end - start);
	}

	// ENCRYPTION AND decrypt
	public void setEncryptionNumber(int num) {
		this.encryptionNum = num;
	}

	public String encryptText(final String str) {
		char[] charray = str.toCharArray();
		String finalStr = "";
		for (char i : charray) {
			finalStr += (((int) i) + StartupWindow.SHIFT);
			finalStr += (" ");
		}
		return finalStr;
	}

	public String decryptText(final String str) {
		String finalPass = "";
		String[] intForm = str.split(" ");
		for (String x : intForm) {
			finalPass += (char) (Integer.parseInt(x) - this.encryptionNum);
		}
		return finalPass;
	}

	public int getEncryptionNumber() {
		return encryptionNum;
	}

	public static Algorithms getInstance() {
		return instance;
	}

	// public static void main(String[] a) {
	// Algorithms.getInstance().setEncryptionNumber(129993);
	// String x = Algorithms.getInstance().decryptText("130073 130058 130076 130076
	// 130080 130072 130075 130061 ");
	// System.out.println(x);
	// }
}
