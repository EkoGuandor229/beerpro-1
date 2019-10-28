package ch.beerpro.domain.models;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class User implements Entity {
    public static final String COLLECTION = "users";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PHOTO = "photo";

    @Exclude
    private String id;
    private String name;
    private String photo;

    public User() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$photo = this.getPhoto();
        final Object other$photo = other.getPhoto();
        return this$photo == null ? other$photo == null : this$photo.equals(other$photo);
    }

    private boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $photo = this.getPhoto();
        result = result * PRIME + ($photo == null ? 43 : $photo.hashCode());
        return result;
    }

    @NonNull
    public String toString() {
        return "User(id=" + this.getId() + ", name=" + this.getName() + ", photo=" + this.getPhoto() + ")";
    }
}
