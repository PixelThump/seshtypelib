package com.pixelthump.seshtype.service.model;
import lombok.Data;

@Data
public class SeshInfo {

    private String seshType;
    private String seshCode;

    public SeshInfo() {

    }

    public SeshInfo(String seshType, String seshCode) {

        this.seshType = seshType;
        this.seshCode = seshCode;
    }
}
