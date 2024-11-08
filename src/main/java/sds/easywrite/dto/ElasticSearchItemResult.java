package sds.easywrite.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ElasticSearchItemResult<T> implements Serializable {
    private String id;
    private T source;
    private float score;
    private Map<String, List<String>> highlights;

    public ElasticSearchItemResult() {
    }

    public ElasticSearchItemResult(String id, T source, float score, Map<String, List<String>> highlights) {
        this.id = id;
        this.source = source;
        this.score = score;
        this.highlights = highlights;
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Map<String, List<String>> getHighlights() {
        return highlights;
    }

    public void setHighlights(Map<String, List<String>> highlights) {
        this.highlights = highlights;
    }
}
