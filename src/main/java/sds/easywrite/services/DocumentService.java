package sds.easywrite.services;

import sds.easywrite.domain.elastic.ElasticDocument;
import sds.easywrite.dto.ResultDTO;

public interface DocumentService {
    ResultDTO save(ElasticDocument document);

    ResultDTO getAll(String keyword);

    ResultDTO getDetail(Integer id);
}
