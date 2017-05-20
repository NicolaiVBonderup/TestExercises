package entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "geoNameId",
    "name",
    "asciiName",
    "alternateNames",
    "latitude",
    "longitude",
    "population"
})
public class City {

    public City(String geoNameId, String name, String asciiName, String[] alternateNames, String latitude, String longitude, String population) {
        this.geoNameId = geoNameId;
        this.name = name;
        this.asciiName = asciiName;
        this.alternateNames = alternateNames;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public City() {
    }

    @JsonProperty("geoNameId")
    private String geoNameId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("asciiName")
    private String asciiName;
    @JsonProperty("alternateNames")
    private String[] alternateNames;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("population")
    private String population;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("geoNameId")
    public String getGeoNameId() {
        return geoNameId;
    }

    @JsonProperty("geoNameId")
    public void setGeoNameId(String geoNameId) {
        this.geoNameId = geoNameId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("asciiName")
    public String getAsciiName() {
        return asciiName;
    }

    @JsonProperty("asciiName")
    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    @JsonProperty("alternateNames")
    public String[] getAlternateNames() {
        return alternateNames;
    }

    @JsonProperty("alternateNames")
    public void setAlternateNames(String[] alternateNames) {
        this.alternateNames = alternateNames;
    }

    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("population")
    public String getPopulation() {
        return population;
    }

    @JsonProperty("population")
    public void setPopulation(String population) {
        this.population = population;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
