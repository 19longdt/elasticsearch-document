package sds.easywrite.config;

import co.elastic.apm.attach.ElasticApmAttacher;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ApmConfiguration {
    private static final String SERVER_URL_KEY = "server_url";
    private String serverUrl;

    private static final String SERVICE_NAME_KEY = "service_name";
    private String serviceName;

    private static final String ENVIRONMENT_KEY = "environment";
    private String environment;

    private static final String LOG_LEVEL_KEY = "log_level";
    private String logLevel;

    private static final String RECORDING = "recording";
    private String recording;

    private static final String ENABLE_SPAN = "enable_span_stack_trace";
    private Boolean enableSpanStackTrace;

    private static final String CAPTURE_BODY = "capture_body";
    private String captureBody;


    @PostConstruct
    public void init() {

        Map<String, String> apmProps = new HashMap<>(7);
        apmProps.put(SERVER_URL_KEY, serverUrl);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(LOG_LEVEL_KEY, logLevel);
        apmProps.put(RECORDING, recording);
        apmProps.put(ENABLE_SPAN, String.valueOf(enableSpanStackTrace));
        apmProps.put(CAPTURE_BODY, captureBody);

        ElasticApmAttacher.attach(apmProps);
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public Boolean getEnableSpanStackTrace() {
        return enableSpanStackTrace;
    }

    public void setEnableSpanStackTrace(Boolean enableSpanStackTrace) {
        this.enableSpanStackTrace = enableSpanStackTrace;
    }

    public String getCaptureBody() {
        return captureBody;
    }

    public void setCaptureBody(String captureBody) {
        this.captureBody = captureBody;
    }
}
