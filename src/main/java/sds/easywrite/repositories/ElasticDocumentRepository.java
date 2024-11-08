package sds.easywrite.repositories;

import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import sds.easywrite.domain.elastic.ElasticDocument;

import java.util.List;

@Repository
public interface ElasticDocumentRepository extends ElasticsearchRepository<ElasticDocument, String> {
    @Highlight(fields = {
        @HighlightField(name = "type"),
        @HighlightField(name = "title"),
        @HighlightField(name = "content")
    })
    SearchHits<ElasticDocument> findByTypeOrTitleOrContent(String type, String title, String content);
}
