package de.goeuro.javatest.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.goeuro.javatest.solution.CSVConverter;
import de.goeuro.javatest.solution.LocationSuggestionProvider;
import de.goeuro.javatest.solution.jsonprovider.LocationSuggestion;

/**
 * Tests conversion of data from a location suggestion provider into a CSV file.
 */
public class CSVConvertionTests {

    /**
     * Tests the CSV transformation from suggestion POJO to a CSV file.
     */
    @Test
    public void testCSVConvertion() {
        LocationSuggestionProvider provider = new MockSuggestionProvider();
        List<LocationSuggestion> suggestions = provider.suggest("cam");

        try {
            File file = new CSVConverter().transform(suggestions, ".");

            try (BufferedReader buffered = new BufferedReader(new FileReader(file))) {
                String headerLine = buffered.readLine();
                this.checkHeaderLine(headerLine);

                int linePosition = 1;
                String line;
                while ((line = buffered.readLine()) != null) {
                    checkWrittenLine(line, suggestions.get(linePosition++));
                }

            }
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Checks the read file line against the location suggestion.
     * 
     * @param line
     *            A line from the CSV file.
     * @param locationSuggestion
     *            A location suggestion.
     */
    private void checkWrittenLine(String line, LocationSuggestion locationSuggestion) {
        String[] readLineValues = line.split(",");

        // header for the values: _type, _id, name, type, latitude, longitude
        Assert.assertEquals(readLineValues[0], locationSuggestion.getType());
        Assert.assertEquals(readLineValues[1], locationSuggestion.getId());
        Assert.assertEquals(readLineValues[2], locationSuggestion.getValue());
        Assert.assertEquals(readLineValues[3], locationSuggestion.getType());
        Assert.assertEquals(readLineValues[4], locationSuggestion.getLat());
        Assert.assertEquals(readLineValues[5], locationSuggestion.getLng());
    }

    /**
     * Checks the header written to file.
     * 
     * @param readLine
     *            The header read from file.
     */
    private void checkHeaderLine(String readLine) {
        String[] headers = CSVConverter.FILE_HEADERS.split(",");
        String[] readLineValues = readLine.split(",");

        for (int i = 0; i < headers.length; i++) {
            Assert.assertEquals("Error checking file header values", headers[i], readLineValues[i]);
        }
    }
}
