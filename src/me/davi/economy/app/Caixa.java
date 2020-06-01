package me.davi.economy.app;

import java.util.ArrayList;

public class Caixa {

	public static void box(final String title, final String... paragraph) {
		final ArrayList<String> buffer = new ArrayList<String>();
		String at = "";
		final int side1 = (int) Math.round(25.0 - (title.length() + 4) / 2.0);
		final int side2 = (int) (26.0 - (title.length() + 4) / 2.0);
		at = String.valueOf(String.valueOf(at)) + '+';
		for (int t = 0; t < side1; ++t) {
			at = String.valueOf(String.valueOf(at)) + '-';
		}
		at = String.valueOf(String.valueOf(at)) + "{ " + title + " }";
		for (int t = 0; t < side2; ++t) {
			at = String.valueOf(String.valueOf(at)) + '-';
		}
		at = String.valueOf(String.valueOf(at)) + '+';
		buffer.add(at);
		at = "";
		buffer.add("|                                                   |");
		for (final String s : paragraph) {
			at = String.valueOf(String.valueOf(at)) + "| ";
			int left = 49;
			for (int t2 = 0; t2 < s.length(); ++t2) {
				at = String.valueOf(String.valueOf(at)) + s.charAt(t2);
				if (--left == 0) {
					at = String.valueOf(String.valueOf(at)) + " |";
					buffer.add(at);
					at = "";
					at = String.valueOf(String.valueOf(at)) + "| ";
					left = 49;
				}
			}
			while (left-- > 0) {
				at = String.valueOf(String.valueOf(at)) + ' ';
			}
			at = String.valueOf(String.valueOf(at)) + " |";
			buffer.add(at);
			at = "";
		}
		buffer.add("|                                                   |");
		buffer.add("+---------------------------------------------------+");
		System.out.println(" ");
		String[] array;
		for (int length2 = (array = buffer.toArray(new String[buffer.size()])).length, j = 0; j < length2; ++j) {
			final String line = array[j];
			System.out.println(line);
		}
		System.out.println(" ");
	}
}
