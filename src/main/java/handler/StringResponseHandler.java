package handler;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.util.IOUtils;

import java.io.IOException;

/**
 * レスポンス情報を文字列に変換するためのResponseHandlerです
 */
public class StringResponseHandler implements HttpResponseHandler<AmazonWebServiceResponse<String>> {
    @Override
    public AmazonWebServiceResponse<String> handle(com.amazonaws.http.HttpResponse response) throws IOException {
        AmazonWebServiceResponse<String> awsResponse = new AmazonWebServiceResponse<>();
        awsResponse.setResult(IOUtils.toString(response.getContent()));
        return awsResponse;
    }

    @Override
    public boolean needsConnectionLeftOpen() {
        return false;
    }
}