package de.goeuro.javatest.test;

import java.util.ArrayList;
import java.util.List;

import de.goeuro.javatest.solution.LocationSuggestionProvider;
import de.goeuro.javatest.solution.jsonprovider.LocationSuggestion;

/**
 * The mock suggestion provider will deliver static data for test proposals.
 */
public class MockSuggestionProvider implements LocationSuggestionProvider {

    private static final List<LocationSuggestion> MOCK_LIST = new ArrayList<LocationSuggestion>();

    static {
        MOCK_LIST.add(createSuggestion("Brasil", "1", false, 10L, 20L, "1", "location",
                "Campinas, Brasil"));
        MOCK_LIST.add(createSuggestion("Brasil", "2", false, 11L, 19L, "1", "location",
                "Campina Grande, Brasil"));
        MOCK_LIST.add(createSuggestion("Brasil", "3", false, 12L, 18L, "1", "location",
                "Campinhos, Brasil"));
        MOCK_LIST.add(createSuggestion("Brasil", "4", false, 13L, 17L, "1", "location",
                "Campinal, Brasil"));
        MOCK_LIST.add(createSuggestion("Brasil", "5", false, 14L, 16L, "1", "location",
                "Camoios, Brasil"));
    }

    @Override
    public List<LocationSuggestion> suggest(String queryParam) {
        return MOCK_LIST;
    }

    /**
     * Creates a new location entry.
     * 
     * @param country
     *            The location country.
     * @param id
     *            The location ID.
     * @param isInEurope
     *            A flag whether this location is in Europe or not.
     * @param lat
     *            The location latitude.
     * @param lng
     *            The location Longitude
     * @param score
     *            The relevancy score of the suggestion.
     * @param type
     *            The location type.
     * @param value
     *            The location value.
     * @return A new location suggestion.
     */
    private static LocationSuggestion createSuggestion(String country, String id,
            Boolean isInEurope, Long lat, Long lng, String score, String type, String value) {
        LocationSuggestion suggestion = new LocationSuggestion();
        suggestion.setCountry(country);
        suggestion.setId(id);
        suggestion.setIsInEurope(isInEurope);
        suggestion.setLat(lat);
        suggestion.setLng(lng);
        suggestion.setScore(score);
        suggestion.setType(type);
        suggestion.setValue(value);
        return suggestion;
    }
}
