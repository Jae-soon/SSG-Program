package com.ll.SSG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rq {
    String url;
    String cmd;
    Map<String, String> params;

    public Rq(String url) {
        this.url = url;

        String[] cmdAndParam = url.split("\\?", 2);
        this.cmd = cmdAndParam[0];

        params = new HashMap<>();

        if(cmdAndParam.length == 2) {
            String params_str = cmdAndParam[1];

            String[] param = params_str.split("&");

            for(String paramBit : param) {
                String[] keyAndValue = paramBit.split("=", 2);

                if (keyAndValue.length == 1) {
                    continue;
                }

                String key = keyAndValue[0].trim();
                String value = keyAndValue[1].trim();

                params.put(key, value);
            }
        }
    }

    public int getIntParam(String paramName, int defaultValue) {
        if(!params.containsKey(paramName)) {
            return defaultValue;
        }

        String value = params.get(paramName);

        if(value.length() == 0) {
            return defaultValue;
        }

        return Integer.parseInt(value);
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
