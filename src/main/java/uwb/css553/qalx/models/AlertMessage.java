package uwb.css553.qalx.models;

/**
 * This class is the message payload for notification service.
 * @author Trang Quang
 */
public class AlertMessage {
    private int code;

    private String message;
    public AlertMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
