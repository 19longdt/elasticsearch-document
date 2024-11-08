package sds.easywrite.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"new"})
@Entity
@Table(name = "document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "ref_elastic_id")
    private String refElasticId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRefElasticId() {
        return refElasticId;
    }

    public void setRefElasticId(String refElasticId) {
        this.refElasticId = refElasticId;
    }
}
