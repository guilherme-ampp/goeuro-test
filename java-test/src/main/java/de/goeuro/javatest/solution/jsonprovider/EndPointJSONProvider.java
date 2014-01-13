package de.goeuro.javatest.solution.jsonprovider;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import de.goeuro.javatest.solution.LocationSuggestionProvider;

/**
 * The end-point suggestion provider will access the GoEuro Location API to query for suggestions.
 */
public class EndPointJSONProvider implements LocationSuggestionProvider {

    // The end-point given for the test didn't work as of 2014 January 11th:
    // "http://pre.dev.goeuro.de:12345/api/v1/suggest/position/en/name/";

    // This is the current suggestion end-point in production at GoEuro.com
    public static final String END_POINT_ADDRESS = "http://www.goeuro.com/GoEuroAPI/rest/suggest/en?term=";

    /**
     * Suggests names of locations based on the query parameter.
     * 
     * @param queryParam
     *            The query parameter must be a string with part or the whole name of a location.
     * @return A list of suggestions returned by the end-point.
     */
    public List<LocationSuggestion> suggest(String queryParam) {
        if (queryParam == null || queryParam.isEmpty()) {
            return new ArrayList<LocationSuggestion>();
        }

        ClientConfig config = new ClientConfig();
        config.register(JacksonJsonProvider.class);
        Client client = ClientBuilder.newClient(config);
        try {
            return client.target(END_POINT_ADDRESS + queryParam)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<LocationSuggestion>>() {
                    });
        } finally {
            client.close();
        }
    }
}
