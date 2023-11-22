package documentMangement.tools;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import documentMangement.constants.Constants;

public class TextTools {

	public static void replaceValue(String filesNameIn, String fileNameOut, String searchFor, String replaceWith) {
		try (BufferedWriter writer = Files
				.newBufferedWriter(Paths.get(Constants.RESOURCES_OUTPUT_PATH, fileNameOut));) {

			Files.lines(Paths.get(Constants.RESOURCES_INPUT_PATH, filesNameIn)).forEach(line -> {
				writeline(writer, line.replace(searchFor, replaceWith));

			});

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static void writeline(BufferedWriter writer, String replace) {
		try {
			writer.write(replace);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
