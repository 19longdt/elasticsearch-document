package sds.easywrite.services.impl;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import co.elastic.clients.elasticsearch._types.SortOrder;
import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightFieldParameters;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightParameters;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sds.easywrite.domain.Document;
import sds.easywrite.domain.elastic.ElasticDocument;
import sds.easywrite.dto.ElasticSearchItemResult;
import sds.easywrite.dto.ResultDTO;
import sds.easywrite.dto.errors.InternalServerException;
import sds.easywrite.repositories.DocumentRepository;
import sds.easywrite.repositories.ElasticDocumentRepository;
import sds.easywrite.services.DocumentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static sds.easywrite.constants.messages.ExceptionMessages.NOT_FOUND;
import static sds.easywrite.constants.messages.ResultMessages.SUCCESS;
import static sds.easywrite.constants.messages.ResultMessages.SUCCESS_GET_LIST;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ElasticDocumentRepository elasticDocumentRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public DocumentServiceImpl(DocumentRepository documentRepository, ElasticDocumentRepository elasticDocumentRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.documentRepository = documentRepository;
        this.elasticDocumentRepository = elasticDocumentRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public ResultDTO save(ElasticDocument request) {
//        System.out.println(request.toString());
        if (request.getId() == null) {
            ElasticDocument elasticDocument = new ElasticDocument();
            Document document = new Document();

            BeanUtils.copyProperties(request, elasticDocument);
            elasticDocument.setId(null);
            ElasticDocument savedDocument = elasticDocumentRepository.save(elasticDocument);
            String elasticId = savedDocument.getId();
            BeanUtils.copyProperties(request, document);
            document.setId(null);
            document.setRefElasticId(elasticId);
            documentRepository.save(document);
        } else {
            ElasticDocument elasticDocument = elasticDocumentRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));

            Document documentDb = documentRepository.findOneByRefElasticId(request.getId())
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
            Integer documentId = documentDb.getId();

            BeanUtils.copyProperties(request, documentDb);
            BeanUtils.copyProperties(request, elasticDocument);
            documentDb.setId(documentId);

            documentRepository.save(documentDb);
            elasticDocumentRepository.save(elasticDocument);
        }
        return new ResultDTO(SUCCESS, SUCCESS_GET_LIST, true, null);
    }

    @Override
    @Transactional(readOnly = true)
    @CaptureTransaction(value = "TransactionName")
    public ResultDTO getAll(String keyword) {
        Object result;
        if (!Strings.isNullOrEmpty(keyword)) {
            result = getDataFromElastic(keyword);
        } else {
            result = documentRepository.findAll();
        }
        return new ResultDTO(SUCCESS, SUCCESS_GET_LIST, true, result);
    }

    @CaptureSpan(value = "spanName", type = "ext", subtype = "http")
    private Object getDataFromElastic(String keyword) {
        HighlightParameters parameters = HighlightParameters
            .builder()
            .withPreTags("<span class=\"highlight\">")
            .withPostTags("</span>")
            .withFragmentSize(50)
            .withNumberOfFragments(3)
            .build();


        HighlightQuery highlightQuery = new HighlightQuery(
            new Highlight(
                parameters,
                List.of(
                    new HighlightField("type"),
                    new HighlightField("title"),
                    new HighlightField("content")
                )
            ),
            String.class
        );
        Query query = NativeQuery.builder()
            .withQuery(q -> q
                .multiMatch(m -> m
                    .fields("title", "type", "content")
                    .query(keyword)
                )
            )
            .withHighlightQuery(highlightQuery)
//                .withSort(Sort.by("type").descending().and(Sort.by("title").ascending()))
            .build();

        SearchHits<ElasticDocument> searchHits = elasticsearchTemplate.search(query, ElasticDocument.class);
        List<ElasticSearchItemResult<ElasticDocument>> response = new ArrayList<>();
        for (SearchHit<ElasticDocument> hit : searchHits) {
            ElasticDocument document = hit.getContent();
            float score = hit.getScore();
            String id = hit.getId();
            Map<String, List<String>> highlights = hit.getHighlightFields();

            response.add(new ElasticSearchItemResult<>(id, document, score, highlights));
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public ResultDTO getDetail(Integer id) {
        Document result = documentRepository.findById(id)
            .orElseThrow(() -> new InternalServerException(NOT_FOUND, "document", NOT_FOUND));
        return new ResultDTO(SUCCESS, SUCCESS_GET_LIST, true, result);
    }
}
