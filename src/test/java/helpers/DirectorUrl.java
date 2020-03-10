package helpers;

import builders.UrlBuilder;

import java.net.URL;

public class DirectorUrl {

    private final String auth = "authentication";
    private final String token = "token";
    private final String newCreation = "new";
    private final String authLogin = "validate_with_login";
    private final String authSession = "session";
    private final String authGuest = "guest_session";
    private final String list = "list";
    private final String addItem = "add_item";
    private final String removeItem = "remove_item";
    private final String clear = "clear";
    private final String itemStatus = "item_status";
    private final String tv = "tv";
    private final String season = "season";
    private final String episode = "episode";
    private final String rating = "rating";
    private final String review = "review";
    private final String movie = "movie";

    public DirectorUrl(){}

    public URL buildAuthToken (){
        return new UrlBuilder().addDomain().
                addPathStep(auth).
                addPathStep(token).
                addPathStep(newCreation).
                build();
    }

    public URL buildAuthSessionToken (){
        return new UrlBuilder().addDomain().
                addPathStep(auth).
                addPathStep(token).
                addPathStep(authLogin).
                build();
    }

    public URL buildAuthSession (){
        return new UrlBuilder().addDomain().
                addPathStep(auth).
                addPathStep(authSession).
                addPathStep(newCreation).
                build();
    }

    public URL buildAuthSessionDelete (){
        return new UrlBuilder().addDomain().
                addPathStep(auth).
                addPathStep(authSession).
                build();
    }

    public URL buildListDetail (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                addPathStep(listId).
                build();
    }

    public URL buildListCreate (){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                build();
    }

    public URL buildListDelete (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                addPathStep(String.valueOf(listId)).
                build();
    }

    public URL buildAddMovie (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                addPathStep(String.valueOf(listId)).
                addPathStep(addItem).
                build();
    }

    public URL buildDeleteMovie (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                addPathStep(String.valueOf(listId)).
                addPathStep(removeItem).
                build();
    }

    public URL buildClearList (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                addPathStep(String.valueOf(listId)).
                addPathStep(clear).
                build();
    }

    public URL buildCheckItemStatus (String listId){
        return new UrlBuilder().addDomain().
                addPathStep(list).
                addPathStep(String.valueOf(listId)).
                addPathStep(itemStatus).
                build();

    }

    public URL buildAuthSessionGuest (){
        return new UrlBuilder().addDomain().
                addPathStep(auth).
                addPathStep(authGuest).
                addPathStep(newCreation).
                build();
    }
    public URL buildRateMovie (String movieId){
        return new UrlBuilder().addDomain().
                addPathStep(movie).
                addPathStep(movieId).
                addPathStep(rating).
                build();
    }
    public URL buildRateTvShow(String showId){
        return new UrlBuilder().addDomain().
                addPathStep(tv).
                addPathStep(showId).
                addPathStep(rating).
                build();
    }
    public URL buildRateTvShowEpisode(String showId, String season,String episodeNumber){
        return new UrlBuilder().addDomain().
                addPathStep(tv).
                addPathStep(showId).
                addPathStep(this.season).
                addPathStep(season).
                addPathStep(episode).
                addPathStep(episodeNumber).
                addPathStep(rating).
                build();
    }

    public URL buildReview(String idReview) {
        return new UrlBuilder().addDomain().
                addPathStep(review).
                addPathStep(idReview).
                build();
    }
}
