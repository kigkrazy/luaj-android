package com.reizx.luaj.bean.event;

/**
 * ip状态通知事件
 */
public class IpStatusEvent {
    private String timestamp;
    private String ipistatus;

    public IpStatusEvent(String timestamp, String ipistatus) {
        this.timestamp = timestamp;
        this.ipistatus = ipistatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIpistatus() {
        return ipistatus;
    }

    public void setIpistatus(String ipistatus) {
        this.ipistatus = ipistatus;
    }
}
