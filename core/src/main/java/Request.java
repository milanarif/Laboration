
public class Request {

    private RequestType requestType;
    private String url;
    private String body;

    public Request(RequestType requestType, String url, String body) {
        this.requestType = requestType;
        this.url = url;
        this.body = body;
    }

    public Request(RequestType requestType, String url) {
        this.requestType = requestType;
        this.url = url;
        this.body = null;
    }

    public String getBody() {
        return this.body;
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + requestType +
                ", url='" + url + '\'' +
                '}';
    }
}
