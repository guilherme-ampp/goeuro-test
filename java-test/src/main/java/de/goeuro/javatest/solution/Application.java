package de.goeuro.javatest.solution;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import de.goeuro.javatest.solution.jsonprovider.EndPointJSONProvider;
import de.goeuro.javatest.solution.jsonprovider.LocationSuggestion;

/**
 * This is the main entry point for the application.
 */
public abstract class Application {

    private static final Logger LOG = Logger.getLogger(Application.class);

    /**
     * The main method accesses a suggestion provider to query for suggestions taking arguments from
     * the user.
     * 
     * @param args
     *            The array of arguments from the user.
     */
    public static void main(String[] args) {
        Application.checkUserArguments(args);

        LocationSuggestionProvider provider = new EndPointJSONProvider();
        List<LocationSuggestion> results = provider.suggest(args[0]);

        CSVConverter converter = new CSVConverter();
        try {
            File file = converter.transform(results, ".");
            LOG.info("File created: " + file.getCanonicalPath());
        } catch (IOException ioException) {
            LOG.error("Error writing to CSV file: " + ioException.getMessage());
        }
    }

    /**
     * Checks validity of the array of arguments.
     * 
     * @param args
     *            The array of arguments from the user.
     */
    private static void checkUserArguments(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("This application does not "
                    + "take 0 parameters. Please enter your string query.");
        }
        if (args.length > 1) {
            throw new IllegalArgumentException("This application takes 1 parameter, "
                    + "all additional parameters will be ignored.");
        }
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("This application does not "
                    + "take 0 parameters. Please enter your string query");
        }
    }

}
