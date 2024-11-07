package sds.easywrite.dto.resilience4jConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CircuitBreakerDTO implements Serializable {

    @JsonProperty("slidingWindowSize")
    private int slidingWindowSize;

    @JsonProperty("slidingWindowType")
    private String slidingWindowType;

    @JsonProperty("permittedNumberOfCallsInHalfOpenState")
    private int permittedNumberOfCallsInHalfOpenState;

    @JsonProperty("waitDurationInOpenState")
    private int waitDurationInOpenState;

    @JsonProperty("slowCallDurationThreshold")
    private int slowCallDurationThreshold;

    @JsonProperty("recordedExceptionPredicate")
    private List<String> recordedExceptionPredicate;

    public int getSlidingWindowSize() {
        return slidingWindowSize;
    }

    public void setSlidingWindowSize(int slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public String getSlidingWindowType() {
        return slidingWindowType;
    }

    public void setSlidingWindowType(String slidingWindowType) {
        this.slidingWindowType = slidingWindowType;
    }

    public int getPermittedNumberOfCallsInHalfOpenState() {
        return permittedNumberOfCallsInHalfOpenState;
    }

    public void setPermittedNumberOfCallsInHalfOpenState(int permittedNumberOfCallsInHalfOpenState) {
        this.permittedNumberOfCallsInHalfOpenState = permittedNumberOfCallsInHalfOpenState;
    }

    public int getWaitDurationInOpenState() {
        return waitDurationInOpenState;
    }

    public void setWaitDurationInOpenState(int waitDurationInOpenState) {
        this.waitDurationInOpenState = waitDurationInOpenState;
    }

    public int getSlowCallDurationThreshold() {
        return slowCallDurationThreshold;
    }

    public void setSlowCallDurationThreshold(int slowCallDurationThreshold) {
        this.slowCallDurationThreshold = slowCallDurationThreshold;
    }

    public List<String> getRecordedExceptionPredicate() {
        return recordedExceptionPredicate;
    }

    public void setRecordedExceptionPredicate(List<String> recordedExceptionPredicate) {
        this.recordedExceptionPredicate = recordedExceptionPredicate;
    }
}
