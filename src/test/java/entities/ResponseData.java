package entities;

public class ResponseData {

    private int fieldCount;
    private int affectedRows;
    private int insertId;
    private int serverStatus;
    private int warningCount;
    private String message;
    private boolean protocol141;
    private int changedRows;


    public ResponseData(){}

    public ResponseData(int fieldCount, int affectedRows, int insertId, int serverStatus, int warningCount, String message, boolean protocol141, int changedRows) {
        this.fieldCount = fieldCount;
        this.affectedRows = affectedRows;
        this.insertId = insertId;
        this.serverStatus = serverStatus;
        this.warningCount = warningCount;
        this.message = message;
        this.protocol141 = protocol141;
        this.changedRows = changedRows;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(int affectedRows) {
        this.affectedRows = affectedRows;
    }

    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isProtocol141() {
        return protocol141;
    }

    public void setProtocol141(boolean protocol141) {
        this.protocol141 = protocol141;
    }

    public int getChangedRows() {
        return changedRows;
    }

    public void setChangedRows(int changedRows) {
        this.changedRows = changedRows;
    }

    @Override
    public String toString() {
        return "Response{" +
                "fieldCount=" + fieldCount +
                ", affectedRows=" + affectedRows +
                ", insertId=" + insertId +
                ", serverStatus=" + serverStatus +
                ", warningCount=" + warningCount +
                ", message='" + message + '\'' +
                ", protocol141=" + protocol141 +
                ", changedRows=" + changedRows +
                '}';
    }
}