package sds.easywrite.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sds.easywrite.domain.elastic.ElasticDocument;
import sds.easywrite.dto.ResultDTO;
import sds.easywrite.services.DocumentService;

@RestController
@RequestMapping("/api/document/")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("save")
    public ResponseEntity<ResultDTO> save(@RequestBody ElasticDocument document) {
        return ResponseEntity.ok(documentService.save(document));
    }

    @GetMapping("get-all")
    public ResponseEntity<ResultDTO> getAll(@RequestParam String keyword) {
        return ResponseEntity.ok(documentService.getAll(keyword));
    }

    @GetMapping("get-detail")
    public ResponseEntity<ResultDTO> getDetail(@RequestParam Integer id) {
        return ResponseEntity.ok(documentService.getDetail(id));
    }
}
