package com.pkweb.backend1.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

// 创建一个嵌入式ID类
@Embeddable
class ContactId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "contact_id", nullable = false)
    private int contactId;

    // 默认构造函数
    public ContactId() {}

    // 带参数的构造函数
    public ContactId(int userId, int contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactId that = (ContactId) o;

        if (userId != that.userId) return false;
        return contactId == that.contactId;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + contactId;
        return result;
    }
}

@Entity
@Table(name = "[contacts]")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact {

    @EmbeddedId
    private ContactId id;

    // Getter and Setter for the embedded ID
    public ContactId getId() {
        return id;
    }

    public void setId(ContactId id) {
        this.id = id;
    }

    // Convenience methods to get/set individual fields of the embedded ID
    @Transient
    public int getUserId() {
        return this.id.getUserId();
    }

    public void setUserId(int userId) {
        if (this.id == null) {
            this.id = new ContactId();
        }
        this.id.setUserId(userId);
    }

    @Transient
    public int getContactId() {
        return this.id.getContactId();
    }

    public void setContactId(int contactId) {
        if (this.id == null) {
            this.id = new ContactId();
        }
        this.id.setContactId(contactId);
    }
}
