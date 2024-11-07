package sds.easywrite.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import sds.easywrite.config.ZonedDateTimeDeserializer;
import sds.easywrite.constants.CommonConstants;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@MappedSuperclass
//@Audited
//@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "creator", updatable = false)
    private Integer creator;

    @JsonFormat(pattern = CommonConstants.ZONED_DATE_TIME_FORMAT)
    @Column(name = "create_time", updatable = false)
    private ZonedDateTime createTime = ZonedDateTime.now();

    @LastModifiedBy
    @Column(name = "updater", length = 50)
    private Integer updater;

    @LastModifiedDate
    @JsonFormat(pattern = CommonConstants.ZONED_DATE_TIME_FORMAT)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @Column(name = "update_time")
    private ZonedDateTime updateTime = ZonedDateTime.now();

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
