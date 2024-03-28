package org.example;

public class MessageType {

    private String timestamp;
    private int device_id;
    private float measurement_value;
    public MessageType(float value, String timeStamp, int deviceId) {
        this.measurement_value = value;
        timestamp = timeStamp;
        device_id = deviceId;
    }

    public float getValue() {
        return measurement_value;
    }

    public void setValue(float value) {
        this.measurement_value = value;
    }

    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(String timeStamp) {
        timestamp = timeStamp;
    }

    public int getDeviceId() {
        return device_id;
    }

    public void setDeviceId(int deviceId) {
        device_id = deviceId;
    }
}
