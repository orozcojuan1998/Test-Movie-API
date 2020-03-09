package helpers;

import builders.UrlBuilder;
import entities.ListCreation;

import java.net.URL;

public class DirectorUrl {

    public DirectorUrl(){}

    public URL buildAuthToken (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.token")).
                addPathStep(PropertiesHelper.getValueByKey("url.new")).
                build();
    }

    public URL buildAuthSessionToken (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.token")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.login")).
                build();
    }

    public URL buildAuthSession (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.session")).
                addPathStep(PropertiesHelper.getValueByKey("url.new")).
                build();
    }

    public URL buildAuthSessionDelete (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.session")).
                build();
    }

    public URL buildListDetail (String listId){
        return new UrlBuilder().addDomain().
                addPathStep("list").
                addPathStep(listId).
                build();
    }

    public URL buildListCreate (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                build();
    }

    public URL buildListDelete (ListCreation listCreation){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listCreation.getList_id())).
                build();
    }

    public URL buildAddMovie (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.add")).
                build();
    }

    public URL buildDeleteMovie (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.remove")).
                build();
    }

    public URL buildClearList (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.clear")).
                build();
    }

    public URL buildCheckItemStatus (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.item")).
                build();

    }

    public URL buildAuthSessionGuest (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.guest")).
                addPathStep(PropertiesHelper.getValueByKey("url.new")).
                build();
    }
    public URL buildRateMovie (String movieId){
        return new UrlBuilder().addDomain().
                addPathStep("movie").
                addPathStep(movieId).
                addPathStep("rating").
                build();
    }
    public URL buildRateTvShow(String showId){
        return new UrlBuilder().addDomain().
                addPathStep("tv").
                addPathStep(showId).
                addPathStep("rating").
                build();
    }
    public URL buildRateTvShowEpisode(String showId, String season,String episodeNumber){
        return new UrlBuilder().addDomain().
                addPathStep("tv").
                addPathStep(showId).
                addPathStep("season").
                addPathStep(season).
                addPathStep("episode").
                addPathStep(episodeNumber).
                addPathStep("rating").
                build();
    }

    public URL buildReview(String idReview) {
        return new UrlBuilder().addDomain().
                addPathStep("review").
                addPathStep(idReview).
                build();
    }
}
