package sds.easywrite.config;

import com.google.common.base.Strings;
import java.util.Base64;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        int userID = 0;
        if (Strings.isNullOrEmpty(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString())) {
            return Optional.of(0);
        }
        String[] chunks = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString().split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject json = null;

        try {
            json = new JSONObject(payload);
            userID = Integer.parseInt(json.get("id").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(userID);
    }
}
