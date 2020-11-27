package handler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.SdkBaseException;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.http.HttpResponseHandler;

/**
 * 例外処理を行うためのResponseHandlerです
 */
public class ErrorResponseHandler implements HttpResponseHandler<SdkBaseException> {
    @Override
    public SdkBaseException handle(HttpResponse httpResponse) throws Exception {
        return new AmazonClientException("request fail."
                + " status code:[" + httpResponse.getStatusCode() + "]."
                + " status text:[" + httpResponse.getStatusText() + "].");
    }

    @Override
    public boolean needsConnectionLeftOpen() {
        return false;
    }
}
