//package sds.easywrite.controllers;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreaker;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
//import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import sds.easywrite.config.circuitBreaker.Resilience4jConfig;
//import sds.easywrite.dto.ResultDTO;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.function.Supplier;
//
//@RestController
//public class TestController {
//    private final Resilience4jConfig resilience4jConfig;
//    private final RestTemplate restTemplate;
//    private final CircuitBreakerRegistry circuitBreakerRegistry;
//
//    public TestController(Resilience4jConfig resilience4jConfig, RestTemplate restTemplate, CircuitBreakerRegistry circuitBreakerRegistry) {
//        this.resilience4jConfig = resilience4jConfig;
//        this.restTemplate = restTemplate;
//        this.circuitBreakerRegistry = circuitBreakerRegistry;
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<String> running() {
//        return new ResponseEntity<>("EASY_KYC_RUNNING", HttpStatus.OK);
//    }
//
//    @GetMapping("/api/p/testClientBackend")
//    public ResponseEntity<ResultDTO> testClientBackend() {
//        return new ResponseEntity<>(new ResultDTO("SUCCESS", "SUCCESS", true), HttpStatus.OK);
//    }
//
//    @GetMapping("/api/testClientBackend")
//    public ResponseEntity<ResultDTO> testClientBackend1() {
//        return new ResponseEntity<>(new ResultDTO("SUCCESS", "SUCCESS", true), HttpStatus.OK);
//    }
//
//    @TimeLimiter(name = "default", fallbackMethod = "fallback")
//    @GetMapping("/api/p/testCircuitBreaker")
//    public CompletableFuture<ResponseEntity<String>> testCircuitBreaker(@RequestParam String domain, @RequestParam String url) {
//        CircuitBreaker circuitBreaker = resilience4jConfig.getCircuitBreaker(domain, circuitBreakerRegistry);
//        Supplier<ResponseEntity<String>> supplier = () -> {
//            String response = restTemplate.getForObject(url, String.class);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        };
//        return CompletableFuture.supplyAsync(CircuitBreaker.decorateSupplier(circuitBreaker, supplier))
//                .exceptionally(throwable -> fallback(throwable).join());
//    }
//
//    private CompletableFuture<ResponseEntity<String>> fallback(Throwable t) {
//        System.err.println("Fallback response due to error: " + t.getMessage());
//        return CompletableFuture.completedFuture(ResponseEntity.status(500).body("Fallback response: " + t.getMessage()));
//    }
//}
