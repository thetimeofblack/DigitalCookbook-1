package de.fhluebeck.group3.util;

/**
 * 
 * @author Eason.Hua on 2018/06/02
 */
public final class StringUtil {

	/**
	 * Handle the text before output. This function will make the text more
	 * beautiful and readable and set the text suitable for presentation.
	 * 
	 * @return formatted string.
	 */
	public static String textProcessingBeforeOutput(String rawString, int lineWidth, int maxWidth) {

		StringBuilder stringBuilder = new StringBuilder();
		try {

			char[] text = rawString.toCharArray();

			int textSize = text.length;

			for (int i = 0; i < textSize; i++) {

				stringBuilder.append(text[i]);

				if (i % lineWidth == 0 && i != 0) {

					stringBuilder.append("-\n");

				}

				if (i > maxWidth) {

					stringBuilder.append("...");

					break;

				}

			}

		} catch (NullPointerException exception) {

		}

		return stringBuilder.toString();

	}

}
