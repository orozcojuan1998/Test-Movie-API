package entities;


public class SessionData {

        private boolean success;
        private String session_id;

    public SessionData(){
    }

    public SessionData(boolean success, String session_id) {
        this.success = success;
        this.session_id = session_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRequestToken() {
        return session_id;
    }

    public void setRequestToken(String session_id) {
        this.session_id = session_id;
    }
}


