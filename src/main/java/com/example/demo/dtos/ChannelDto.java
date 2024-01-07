package com.example.demo.dtos;

public class ChannelDto {
    private String channelName;
    private Boolean accessibility;
    private Boolean visible;

    public ChannelDto(){

    }

    public ChannelDto(String channelName, Boolean accessibility, Boolean visible) {
        this.channelName = channelName;
        this.accessibility = accessibility;
        this.visible = visible;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Boolean accessibility) {
        this.accessibility = accessibility;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
