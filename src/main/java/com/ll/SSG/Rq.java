package com.ll.SSG;

import java.util.List;

public class Rq {
    String url;
    String cmd;
    String params;

    public Rq(String url) {
        this.url = url;


        String[] cmdAndParam = url.split("\\?", 2);
        this.cmd = cmdAndParam[0];

        if(cmdAndParam.length == 2) {
            this.params = cmdAndParam[1];
        }
    }

    public int getIntParam(String paramName, int defaultValue) {
        String[] param = params.split("&");

        for(String paramBit : param) {
            String[] keyAndValue = paramBit.split("=", 2);
            String key = keyAndValue[0];
            String value = keyAndValue[1];

            if(paramName.equals(key)) {
                return Integer.parseInt(value);
            }
        }

        return defaultValue;
    }

    public String getCmd() {
        return cmd;
    }

    public WiseSay findById(int id, List<WiseSay> wiseSays) {
        for(WiseSay wiseSay : wiseSays) {
            if(wiseSay.id == id) {
                return wiseSay;
            }
        }
        return null;
    }
}
