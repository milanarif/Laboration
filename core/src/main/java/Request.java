
public class Request {

    private RequestType requestType;
    private String url;

    public Request(RequestType requestType, String url) {
        this.requestType = requestType;
        this.url = url;
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public String getUrl() {
        return this.url;
    }

}
