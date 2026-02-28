package com.TejaCompany.handlers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class QueryParamUtil {

    private QueryParamUtil() {
        // utility class – object create cheyyakudadu
    }

    public static Map<String, String> parse(URI uri) {

        Map<String, String> params = new HashMap<>();

        String query = uri.getQuery();   // id=1&name=Teja&age=22

        if (query == null || query.isEmpty()) {
            return params;
        }

        String[] pairs = query.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }

        return params;
    }
}
