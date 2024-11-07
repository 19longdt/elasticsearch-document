package sds.easywrite.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import sds.easywrite.constants.CommonConstants;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstants.ZONED_DATE_TIME_FORMAT).withZone(ZoneId.systemDefault());
        return ZonedDateTime.parse(jsonParser.getText(), formatter);
    }
}
