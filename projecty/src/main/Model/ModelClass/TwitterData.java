package ModelClass;

import java.util.Date;

public class TwitterData {
    String message;
    User user;
    String createdAt;

    public TwitterData(String message, String twitterHandle, String name, String profileImageUrl, String createdAt) {
        this.message = message;
        this.user = new User(twitterHandle,name,profileImageUrl);
        this.createdAt = createdAt;
    }

    public TwitterData() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return createdAt;
    }
}