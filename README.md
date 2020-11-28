# java-aws-signature
Java で AWS SDK を用いてリクエストを署名するサンプル

## Feature
- Java 8
- AWS SDK for Java バージョン1

## Note
- AWS SDK for Java を利用して IAMで保護された API Gateway に対してリクエストを送信するサンプルです。
- 下記、設定情報を適宜、実行環境に合わせて書き換えて下さい。

``` java
// IAM認証用のクレデンシャルを生成します
String accessKey = "xxx";
String secretKey = "yyyyyy";
```

```java
// API Gatewayを呼び出すためのリクエスト情報を生成します
URI endpoint = URI.create("https://xxxxxxxxxx.execute-api.ap-northeast-1.amazonaws.com");
String path = "/prod";
```

# Reference
サンプル実装に当たり、参考にさせていただきました。
- https://dev.classmethod.jp/articles/create-sigv4-with-aws-sdk-for-java-v1/
