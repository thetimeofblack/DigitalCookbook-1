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

	/**
	 * Judge whether the input string is numeric(consists only in numbers 0-9).
	 * 
	 * @param str
	 *            the input string.
	 * @return whether the input String is numeric.
	 */
	public static boolean isNumeric(String str) {
		if (str != null && !"".equals(str.trim())) {
			return str.matches("^[0-9]*$");
		} else {
			return false;
		}

	}

	/**
	 * Judge whether the input string is letter or digit(consists only in numbers
	 * 0-9,a-z,A-Z).
	 * 
	 * @param str
	 *            the input string.
	 * @return whether the input String is letter or digit.
	 */
	public static boolean isLetterOrDigit(String str) {
		boolean flag = false;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetterOrDigit(str.charAt(i))) {
				flag = true;
			} else {
				flag = false;
				return flag;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		System.out.println(isLetterOrDigit("@123asd"));
		System.out.println(isLetterOrDigit("1d|"));
		System.out.println(isLetterOrDigit("as)+="));
		System.out.println(isLetterOrDigit("as@#$%"));
		System.out.println(isLetterOrDigit("1asd2"));
		System.out.println(isLetterOrDigit("      "));
	}

}
