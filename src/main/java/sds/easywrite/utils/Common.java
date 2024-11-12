package sds.easywrite.utils;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import sds.easywrite.constants.messages.ExceptionMessages;
import sds.easywrite.dto.errors.RequestAlertException;

public class Common {

    public static void validateInput(Validator customValidator, String entityName, Object... objects) {
        Set<String> violationSet = new HashSet<>();
        for (Object o : objects) {
            Set<ConstraintViolation<Object>> violations = customValidator.validate(o);
            if (violations.isEmpty()) continue;
            String messages = violations.stream().reduce("", (acc, ele) -> acc + ", " + ele.getMessage(), String::concat);
            violationSet.add(messages);
        }
        if (!violationSet.isEmpty()) {
            String errorMessage = String.join(",", violationSet);
            errorMessage = errorMessage.substring(2);
            throw new RequestAlertException(
                ExceptionMessages.BAD_REQUEST_VI,
                entityName,
                ExceptionMessages.BAD_REQUEST_MULTIPLE,
                errorMessage
            );
        }
    }
}
