package entities;


public class RequestToken {

    public boolean success;
    public String expires_at;
    public String request_token;

    public RequestToken(){

    }

    public RequestToken(boolean success, String expiresAt, String requestToken) {
        this.success = success;
        this.expires_at = expiresAt;
        this.request_token = requestToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExpiresAt() {
        return expires_at;
    }

    public void setExpiresAt(String expiresAt) {
        this.expires_at = expiresAt;
    }

    public String getRequestToken() {
        return request_token;
    }

    public void setRequestToken(String requestToken) {
        this.request_token = requestToken;
    }
}
