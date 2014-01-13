package de.goeuro.javatest.solution.jsonprovider;

/**
 * This POJO class is a bean representation of the following JSON format of a location.
 * 
 * {"id":376217, "value":"Berlin, Germany", "lat":52.52437, "lng":13.41053, "country":"germany",
 * "isInEurope":true, "score":0.7814222, "type":"location" }
 */
public class LocationSuggestion {

    private String  id;
    private String  value;
    private Long    lat;
    private Long    lng;
    private String  country;
    private Boolean isInEurope;
    private String  score;
    private String  type;

    /**
     * Sets the value/name of this location.
     * 
     * @param value
     *            The value/name.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sets the unique ID for this location.
     * 
     * @param id
     *            The ID for this location.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the latitude.
     * 
     * @param lat
     *            The latitude.
     */
    public void setLat(Long lat) {
        this.lat = lat;
    }

    /**
     * Sets the longitude.
     * 
     * @param lng
     *            The longitude.
     */
    public void setLng(Long lng) {
        this.lng = lng;
    }

    /**
     * Sets the country of this location.
     * 
     * @param country
     *            The country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets a flag for whether this location is in Europe or not.
     * 
     * @param isInEurope
     *            Whether this location is in Europe or not.
     */
    public void setIsInEurope(Boolean isInEurope) {
        this.isInEurope = isInEurope;
    }

    /**
     * Sets the relevancy score of this location towards the queried parameter.
     * 
     * @param score
     *            The relevancy score.
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * Sets the type of this location.
     * 
     * @param type
     *            The type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the value/name of this location.
     * 
     * @return The value/name of this location.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the unique ID of this location.
     * 
     * @return The ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the latitude of this location.
     * 
     * @return The latitude of this location.
     */
    public Long getLat() {
        return this.lat;
    }

    /**
     * Gets the longitude of this location.
     * 
     * @return The longitude of this location.
     */
    public Long getLng() {
        return this.lng;
    }

    /**
     * Gets the country of this location.
     * 
     * @return The country.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Gets the flag for whether this location is in Europe.
     * 
     * @return The flag for whether this location is in Europe.
     */
    public Boolean getIsInEurope() {
        return this.isInEurope;
    }

    /**
     * Gets the relevancy score of this location towards the queried parameter.
     * 
     * @return The relevancy score.
     */
    public String getScore() {
        return this.score;
    }

    /**
     * Gets the type of this location..
     * 
     * @return The type of this location.
     */
    public String getType() {
        return this.type;
    }

}
