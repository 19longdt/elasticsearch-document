package sds.easywrite.dto.resilience4jConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resilience4jDTO implements Serializable {

    @JsonProperty("circuitBreaker")
    private CircuitBreakerDTO circuitBreakerDTO;

    @JsonProperty("state")
    private String state;

    @JsonProperty("tags")
    private Map<String, Object> tags;

    @JsonProperty("timestampUnit")
    private String timestampUnit;

    @JsonProperty("eventPublisher")
    private Map<String, Object> eventPublisher;

    public CircuitBreakerDTO getCircuitBreakerDTO() {
        return circuitBreakerDTO;
    }

    public void setCircuitBreakerDTO(CircuitBreakerDTO circuitBreakerDTO) {
        this.circuitBreakerDTO = circuitBreakerDTO;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public String getTimestampUnit() {
        return timestampUnit;
    }

    public void setTimestampUnit(String timestampUnit) {
        this.timestampUnit = timestampUnit;
    }

    public Map<String, Object> getEventPublisher() {
        return eventPublisher;
    }

    public void setEventPublisher(Map<String, Object> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}
