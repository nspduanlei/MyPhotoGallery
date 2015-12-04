
package com.duanlei.simplenet.requests;

import com.duanlei.simplenet.base.Request;
import com.duanlei.simplenet.base.Response;

public class StringRequest extends Request<String> {

    public StringRequest(HttpMethod method, String url, RequestListener<String> listener) {
        super(method, url, listener);
    }

    @Override
    public String parseResponse(Response response) {
        return new String(response.getRawData());
    }

}
