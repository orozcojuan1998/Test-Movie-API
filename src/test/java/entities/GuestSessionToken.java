package entities;

public class GuestSessionToken {
    public boolean success;
    public String guest_session_id;
    public String expires_at;

    public GuestSessionToken(){
    }

    public GuestSessionToken(boolean success, String expires_at, String guest_session_id) {
        this.success = success;
        this.guest_session_id = guest_session_id;
        this.expires_at = expires_at;

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getGuest_session_id() {
        return guest_session_id;
    }

    public void setGuest_session_id(String guest_session_id) {
        this.guest_session_id = guest_session_id;
    }
}
