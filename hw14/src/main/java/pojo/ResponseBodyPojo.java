package pojo;

import java.util.Objects;

public class ResponseBodyPojo {
    private int code;
    private String type;
    private String message;

    public ResponseBodyPojo(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public ResponseBodyPojo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseBodyPojo that = (ResponseBodyPojo) o;
        return code == that.code && Objects.equals(type, that.type) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, message);
    }

    @Override
    public String toString() {
        return "ResponseBodyPojo{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
