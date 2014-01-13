package de.goeuro.javatest.test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import de.goeuro.javatest.solution.jsonprovider.EndPointJSONProvider;
import de.goeuro.javatest.solution.jsonprovider.LocationSuggestion;

/**
 * Tests covering the end-point connection.
 */
public class EndPointConnectionTest {

    /**
     * Tests a random return form the GoEuro end-point.
     */
    @Test
    public void testGoEuroEndPoint() {
        EndPointJSONProvider provider = new EndPointJSONProvider();
        List<LocationSuggestion> suggestions = provider.suggest("test");

        Assert.assertNotNull("Suggestions from the JSON provider were null", suggestions);
        Assert.assertTrue("No suggestions returned from the end-point JSON provider",
                suggestions.size() > 0);
    }

    /**
     * Tests empty parameters sent to the end-point.
     */
    @Test
    public void testGoEuroEndPointWithEmptyParameters() {
        EndPointJSONProvider provider = new EndPointJSONProvider();

        List<LocationSuggestion> suggestions = provider.suggest("");
        Assert.assertTrue("An empty argument should return an empty list", suggestions.isEmpty());

        suggestions = provider.suggest(null);
        Assert.assertTrue("A null argument should return an empty list", suggestions.isEmpty());
    }

    /**
     * Tests empty parameters sent to the end-point.
     */
    @Test
    public void testGoEuroEndPointJSONFormat() {
        Client client = ClientBuilder.newClient();
        try {
            Response resp = client.target(EndPointJSONProvider.END_POINT_ADDRESS + "test")
                    .request(MediaType.APPLICATION_JSON).get();

            String data = resp.readEntity(String.class);
            // example of a valid entry:
            // {"id":444422,"value":"Testico, Italy","lat":44.00563,"lng":8.02925,
            // "country":"italy","isInEurope":true,"score":0.733724,"type":"location"}
            String jsonFormatRegex =
                    "\\{\"id\":[0-9]+," +
                    "\"value\":\"[a-zA-Z,\\s]+\"," +
                    "\"lat\":[0-9\\.]+,"+
                    "\"lng\":[0-9\\.]+,"+
                    "\"country\":\"[a-zA-Z]+\","+
                    "\"isInEurope\":(true|false)," +
                    "\"score\":[0-9\\.]+," +
                    "\"type\":\"[a-zA-Z]+\"\\}";

            Pattern p = Pattern.compile(jsonFormatRegex);
            Matcher m = p.matcher(data);
            Assert.assertTrue("Could not match a known format to the JSON received from end-point",
                    m.find());

        } finally {
            client.close();
        }
    }

}
