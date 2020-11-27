import com.amazonaws.*;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.http.AmazonHttpClient;
import com.amazonaws.http.HttpMethodName;
import handler.ErrorResponseHandler;
import handler.StringResponseHandler;

import java.net.URI;
import java.util.Date;

public class App {
    public static void main(String[] args) {

        // IAM認証用のクレデンシャルを生成します
        String accessKey = "xxx";
        String secretKey = "yyyyyy";
        BasicAWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);

        // API Gatewayを呼び出すためのリクエスト情報を生成します
        URI endpoint = URI.create("https://xxxxxxxxxx.execute-api.ap-northeast-1.amazonaws.com");
        String path = "/prod";

        DefaultRequest<Void> req = new DefaultRequest<>("execute-api");
        req.setEndpoint(endpoint);
        req.setResourcePath(path);
        req.setHttpMethod(HttpMethodName.GET);
        req.addHeader("Content-Type", "application/json");

        // リクエストに署名します
        AWS4Signer signer = new AWS4Signer();
        signer.setOverrideDate(new Date());
        signer.setRegionName("ap-northeast-1");
        signer.setServiceName("execute-api");
        signer.sign(req, cred);

        // AwsHttpClientを生成します
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        AmazonHttpClient client = new AmazonHttpClient(clientConfiguration);

        try {
            // HTTPリクエストを実行します
            Response<AmazonWebServiceResponse<String>> res = client.requestExecutionBuilder()
                    .request(req)
                    .errorResponseHandler(new ErrorResponseHandler())
                    .execute(new StringResponseHandler());

            System.out.println("request success.");
            System.out.println("result: [" + res.getAwsResponse().getResult() + "]");
        } catch (AmazonClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
