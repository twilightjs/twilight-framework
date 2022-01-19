import com.twilight.server.io.components.builder.Http;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.components.builder.ResponseBuilderImplementation;

public class TestParser {
    public static void main(String[] args) {
        final String regex = "^\\n.+";
        final String string = "POST / HTTP/1.1\n"
                + "Authorization: Bearer Boss of the Gym\n"
                + "User-Agent: PostmanRuntime/7.28.4\n"
                + "Accept: */*\n"
                + "Postman-Token: 370ba63c-1e60-4a29-8d16-d992e2c37168\n"
                + "Host: localhost\n"
                + "Accept-Encoding: gzip, deflate, br\n"
                + "Connection: keep-alive\n"
                + "Content-Type: multipart/form-data; boundary=--------------------------598121600281625372163801\n"
                + "Content-Length: 278\n\n"
                + "----------------------------598121600281625372163801\n"
                + "Content-Disposition: form-data; name=\"Login\"\n\n"
                + "SeRzZzJ\n"
                + "----------------------------598121600281625372163801\n"
                + "Content-Disposition: form-data; name=\"Password\"\n\n"
                + "1223\n"
                + "----------------------------598121600281625372163801--\n\n"
                + "\\S+: .+";
        String req = "GET /download/doc HTTP/1.1\n" +
                "Host: localhost\n" +
                "Connection: keep-alive\n" +
                "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Sec-Fetch-Site: none\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Sec-Fetch-User: ?1\n" +
                "Sec-Fetch-Dest: document\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: en-US,en;q=0.9,ru;q=0.8";


        ResponseBuilder responseBuilder = new ResponseBuilderImplementation();
        responseBuilder.setStatusCode(Http.HttpCodes.OK, "OK")
                .setHeader("ContentType","application")
                .setHeader("ContentType","application")
                .setHeader("ContentType","application")
                .setHeader("ContentType","application")
                .setHeader("ContentType","application")
                .build();
        System.out.println(responseBuilder.getResponse());

//        ByteBuffer byteBuffer = ByteBuffer.wrap(req.getBytes(StandardCharsets.UTF_8));
//        System.out.println(req.getBytes(StandardCharsets.UTF_8).length);
//        System.out.println(req.getBytes(StandardCharsets.UTF_8)[658]);
//        int i = 0;
//        byte[] bytes = new byte[req.getBytes(StandardCharsets.UTF_8).length];
//        while (i <= req.getBytes(StandardCharsets.UTF_8).length){
//            bytes[i] = byteBuffer.get(i);
//            i++;
//        }
//        System.out.println(bytes[658]);

//        HttpParser parser = HttpMessageParser.getHttpParser(req);
//        System.out.println(parser.getURI().getPath());
//        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
//        final Matcher matcher = pattern.matcher(string);
//
//        while (matcher.find()) {
//            System.out.println("Full match: " + matcher.group(0));
//
//            for (int i = 1; i <= matcher.groupCount(); i++) {
//                System.out.println("Group " + i + ": " + matcher.group(i));
//            }
//        }

        //        String request = "GET /about.html HTTP/1.1\n" +
//                "Host: example.org\n" +
//                "User-Agent: MyLonelyBrowser/5.0";
//        String response = "HTTP/1.x 301 Moved Permanently\n" +
//                "Location: http://example.com/about.html#contacts\n" +
//                "Date: Thu, 19 Feb 2009 11:08:01 GMT\n" +
//                "Server: Apache/2.2.4\n" +
//                "Content-Type: text/html; charset=windows-1251\n" +
//                "Content-Length: 110";
//        System.out.println(Arrays.toString(Arrays.toString(request.split("\n")).split(": ")));
        ///addScore/auth.html?login=user&exc=psh&count=21#hash
//        String req = "GET /calculator HTTP/1.1\n" +
//                "Host: localhost\n" +
//                "Connection: keep-alive\n" +
//                "Cache-Control: max-age=0\n" +
//                "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"\n" +
//                "sec-ch-ua-mobile: ?0\n" +
//                "sec-ch-ua-platform: \"Windows\"\n" +
//                "Upgrade-Insecure-Requests: 1\n" +
//                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36\n" +
//                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
//                "Sec-Fetch-Site: none\n" +
//                "Sec-Fetch-Mode: navigate\n" +
//                "Sec-Fetch-User: ?1\n" +
//                "Sec-Fetch-Dest: document\n" +
//                "Accept-Encoding: gzip, deflate, br\n" +
//                "Accept-Language: en-US,en;q=0.9,ru;q=0.8";
//        String path = "/calculator";
//        HttpParser httpParser = HttpMessageParser.getHttpParser(req);
//        URI uri = httpParser.getURI();
//        System.out.println(uri.getPath());
//        System.out.println(uri.getPath().
//
//                equals(path));
//        StringBuilder result = new StringBuilder();
//        Matcher matcherPath = Pattern.compile(".+/[a-zA-Z]+[.][a-zA-Z]+|/[a-zA-Z]+").matcher(req);
//        if (matcherPath.find()) {
//            result.append(matcherPath.group().replaceFirst("[^/]+", ""));
//            System.out.println(result);
//        }
//        Matcher matcherParam = Pattern.compile("&[a-zA-Z0-9]+=[a-zA-Z0-9]+|\\?[a-zA-Z0-9]+=[a-zA-Z0-9]+").matcher(req);
//        while (matcherParam.find()) {
//            result.append(matcherParam.group());
//            System.out.println(result);
//        }
//        Matcher matcherHash = Pattern.compile("#.+").matcher(req);
//        if (matcherHash.find()) {
//            result.append(matcherHash.group().replaceFirst(" [A-Z]+/[0-9].[0-9]|[A-Z]+/[0-9].[0-9]", ""));
//            System.out.println(result);
//        }
//        System.out.println(result);
    }
}
