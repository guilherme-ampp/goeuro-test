package de.goeuro.javatest.solution;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.List;

import de.goeuro.javatest.solution.jsonprovider.LocationSuggestion;

/**
 * The CSV converter takes a list of suggestions and transforms each entry into a comma-separated
 * values format.<br>
 * 
 * Headers and their associated values will adhere to the following format:<br>
 * _type, _id, name, type, latitude, longitude
 */
public class CSVConverter {

    public static final String  FILE_HEADERS = "_type, _id, name, type, latitude, longitude";

    private static final String SEPARATOR    = ",";
    private static final String NEW_LINE     = "\r\n";

    /**
     * Transforms the list of suggestions into comma-separated values and write them to a file.
     * 
     * @param results
     *            The list of suggestions.
     * @param csvFilePath
     *            The file path where the new CSV file will be created.
     * @throws IOException
     *             Thrown if the file path could not be accessed or written to.
     * @return The created CSV file.
     */
    public File transform(List<LocationSuggestion> results, String csvFilePath) throws IOException {
        if (this.fileIsPathNotValid(csvFilePath)) {
            throw new IllegalArgumentException("The file path is not valid: " + csvFilePath);
        }

        File file = createFile(csvFilePath);
        this.appendData(file, results);
        return file;
    }

    /**
     * Appends data from the list of suggestions into the given file in a comma-separated values
     * fashion.
     * 
     * @param file
     *            The CSV file currently at work.
     * @param results
     *            The list of suggestions being transformed and transfered to file.
     * @throws IOException
     *             Thrown if the given file could not be opened with write privileges.
     */
    private void appendData(File file, List<LocationSuggestion> results) throws IOException {
        try (FileChannel channel = FileChannel.open(file.toPath(), StandardOpenOption.WRITE)) {
            this.appendHeaders(channel);
            for (LocationSuggestion result : results) {
                StringBuilder builder = new StringBuilder();
                builder.append(result.getType()).append(SEPARATOR).append(result.getId())
                        .append(SEPARATOR).append(result.getValue().replace(",", ""))
                        .append(SEPARATOR).append(result.getType()).append(SEPARATOR)
                        .append(result.getLat()).append(SEPARATOR).append(result.getLng())
                        .append(NEW_LINE);
                channel.write(ByteBuffer.wrap(builder.toString().getBytes()));
            }
        }
    }

    /**
     * Append headers data to the given file channel.
     * 
     * @param channel
     *            The open channel to the target file.
     * @throws IOException
     *             Thrown if the file channel could not be used properly.
     */
    private void appendHeaders(FileChannel channel) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(FILE_HEADERS).append(NEW_LINE);
        channel.write(ByteBuffer.wrap(builder.toString().getBytes()));
    }

    /**
     * Creates a new CSV file in the given path.
     * 
     * @param csvFilePath
     *            The CSV file path.
     * @return The newly created file.
     * @throws IOException
     *             Thrown if the given file path could not be written to.
     */
    private File createFile(String csvFilePath) throws IOException {
        File file = new File(csvFilePath + File.separatorChar + "query_suggestions_"
                + System.currentTimeMillis() + ".csv");
        file.createNewFile();
        return file;
    }

    /**
     * Checks if the given path is a valid path with write permissions.
     * 
     * @param filePath
     *            The file path to be checked,
     * @return True if the path is OK to be used, false otherwise.
     */
    private boolean fileIsPathNotValid(String filePath) {
        return !new File(filePath).exists() || !new File(filePath).canWrite();
    }

}
