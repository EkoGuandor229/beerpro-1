package ch.beerpro.domain.models;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public class FridgeItem implements Entity{
    public static final String COLLECTION = "fridge";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_BEER_ID = "beerId";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_CREATION_DATE = "creationDate";

    @Exclude
    private String id;
    private String userId;
    private String beerId;
    private Integer amount;
    private Date creationDate;

    public FridgeItem(String id, String userId, String beerId, Integer amount, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.beerId = beerId;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public FridgeItem() {}

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getBeerId() {
        return this.beerId;
    }

    public Integer getAmount() {
        return this.amount;
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

    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }

    public static String generateId(String userId, String beerId) {
        return String.format("%s_%s", userId, beerId);
    }
}
