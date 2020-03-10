package builders;

import helpers.PropertiesHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlBuilder {
    private  String baseUrl;
    private List<String> path;

    public UrlBuilder(){
        path = new ArrayList<>();
    }

    public UrlBuilder addDomain(){
        this.baseUrl = "https://api.themoviedb.org/3";
        return this;
    }

    public UrlBuilder addPathStep(String step){
        path.add(step);
        return this;
    }

    public URL build(){
        try {
            return new URL(baseUrl +"/"+String.join("/", path));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
