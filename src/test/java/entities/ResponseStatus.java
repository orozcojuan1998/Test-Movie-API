package entities;

public class ResponseStatus {

    private String id;
    private boolean item_present;

    public ResponseStatus(){

    }

    public ResponseStatus(String id, boolean item_present) {
        this.id = id;
        this.item_present = item_present;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isItem_present() {
        return item_present;
    }

    public void setItem_present(boolean item_present) {
        this.item_present = item_present;
    }
}
