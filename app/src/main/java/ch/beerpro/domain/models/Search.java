package ch.beerpro.domain.models;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public class Search implements Entity {
    public static final String COLLECTION = "searches";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_CREATION_DATE = "creationDate";

    @Exclude
    private String id;

    private String userId;

    private String term;

    private Date creationDate = new Date();

    public Search(String userId, String term) {
        this.userId = userId;
        this.term = term;
    }

    public Search() {
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getTerm() {
        return this.term;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Search)) return false;
        final Search other = (Search) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId))
            return false;
        final Object this$term = this.getTerm();
        final Object other$term = other.getTerm();
        if (this$term == null ? other$term != null : !this$term.equals(other$term)) return false;
        final Object this$creationDate = this.getCreationDate();
        final Object other$creationDate = other.getCreationDate();
        return this$creationDate == null ? other$creationDate == null : this$creationDate.equals(other$creationDate);
    }

    private boolean canEqual(final Object other) {
        return other instanceof Search;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $term = this.getTerm();
        result = result * PRIME + ($term == null ? 43 : $term.hashCode());
        final Object $creationDate = this.getCreationDate();
        result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
        return result;
    }

    @NonNull
    public String toString() {
        return "Search(id=" + this.getId() + ", userId=" + this.getUserId() + ", term=" + this.getTerm() + ", creationDate=" + this.getCreationDate() + ")";
    }
}
