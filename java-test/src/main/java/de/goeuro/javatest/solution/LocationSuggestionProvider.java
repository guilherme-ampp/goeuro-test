package de.goeuro.javatest.solution;

import java.util.List;

import de.goeuro.javatest.solution.jsonprovider.LocationSuggestion;


/**
 * A suggestion provider will query for location names based on a given parameter.
 */
public interface LocationSuggestionProvider {

    /**
     * Suggests names of locations based on the query parameter.
     * 
     * @param queryParam
     *            The query parameter must be a string with part or the whole name of a location.
     * @return A list of suggestions returned by the targeted location service.
     */
    List<LocationSuggestion> suggest(String queryParam);

}
