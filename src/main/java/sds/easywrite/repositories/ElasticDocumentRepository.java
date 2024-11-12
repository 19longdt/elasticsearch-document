package sds.easywrite.repositories;

import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import sds.easywrite.domain.elastic.ElasticDocument;

import java.util.List;

@Repository
public interface ElasticDocumentRepository extends ElasticsearchRepository<ElasticDocument, String> {
    @Query("{ \"query\": { " +
        "    \"bool\": { " +
        "      \"must\": [ " +
        "        { \"multi_match\": { " +
        "          \"query\": \"?0\", " +
        "          \"fields\": [\"type\", \"title\", \"content\"] " +
        "        }} " +
        "      ] " +
        "    } " +
        "  }, " +
        "  \"highlight\": { " +
        "    \"fields\": { " +
        "      \"title\": {}, " +
        "      \"content\": {} " +
        "    }, " +
        "    \"number_of_fragments\": 3, " +
        "    \"fragment_size\": 50, " +
        "    \"pre_tags\": [\"<span class='highlight'>\"], " +
        "    \"post_tags\": [\"</span>\"] " +
        "  } " +
        "}")
    SearchHits<ElasticDocument> findByTypeOrTitleOrContent(String keyword);


}
