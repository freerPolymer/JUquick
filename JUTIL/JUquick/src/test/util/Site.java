package test.util;

public class Site {

    private String id;
    private String name;
    private String url;
    private String dateTime;

    public Site() {
    }

    public Site(String id, String name, String url, String dateTime) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDateTime() {
        return dateTime;
    }
}
