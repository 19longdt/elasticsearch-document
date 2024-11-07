package sds.easywrite.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sds.easywrite.constants.messages.ExceptionMessages;
import sds.easywrite.dto.ResultDTO;
import sds.easywrite.dto.errors.*;

@ControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerCustom.class);

    @ExceptionHandler({ InternalServerException.class })
    public ResponseEntity<Object> handleInternalServerException(InternalServerException ex, WebRequest request) {
        log.error(ex.getMessage());
        ResultDTO dto = new ResultDTO(ex.getErrorKey(), ex.getTitle(), Boolean.FALSE);
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleErrorException(Exception ex, WebRequest request) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        log.error(ex.getMessage());
        ex.printStackTrace(pw);
        ResultDTO dto = new ResultDTO(ExceptionMessages.EXCEPTION_ERROR, ExceptionMessages.EXCEPTION_ERROR_VI);
        return new ResponseEntity<>(dto, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({ BadRequestAlertException.class })
    public ResponseEntity<Object> handleBadRequestAlertException(BadRequestAlertException ex, WebRequest request) {
        ResultDTO dto = new ResultDTO(List.of(new ResultExceptionDTO(ex.getErrorKey(), ex.getTitle())), ex.getTitle());
        return new ResponseEntity<>(dto, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AuthenticateException.class })
    public ResponseEntity<Object> handleAuthenticateException(AuthenticateException ex, WebRequest request) {
        ResultDTO dto = new ResultDTO(List.of(new ResultExceptionDTO(ex.getErrorKey(), ex.getTitle())), ex.getTitle());
        return new ResponseEntity<>(dto, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ RequestAlertException.class })
    public ResponseEntity<Object> handleRequestAlertException(RequestAlertException ex, WebRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        ResultExceptionDTO[] data = null;
        try {
            data = mapper.readValue("[" + ex.getData().toString() + "]", ResultExceptionDTO[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResultDTO dto = new ResultDTO(data, ex.getTitle());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
