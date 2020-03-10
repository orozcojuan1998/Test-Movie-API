package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import entities.*;
import io.restassured.response.Response;
import org.json.JSONObject;

public class JsonHelper {

    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static String objectToJson(Object object)
    {
        return gson.toJson(object, object.getClass());
    }

    public static String createSessionBody (User user,String token){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(user);
        jsonElement.getAsJsonObject().addProperty("request_token", token);
        return gson.toJson(jsonElement);
    }

    public static RequestToken responseRequestTokenToJson (Response response){
        return gson.fromJson(response.getBody().asString(), RequestToken.class);
    }

    public static GuestSessionToken guestSessionToken (Response response){
        return gson.fromJson(response.getBody().asString(), GuestSessionToken.class);
    }

    public static SessionData sessionToResponse(Response response) {
        return gson.fromJson(response.getBody().asString(), SessionData.class);
    }

    public static Lists responseToList(Response response) {
        return gson.fromJson(response.getBody().asString(), Lists.class);

    }

    public static ListCreation responseToCreationList(Response response) {
        return gson.fromJson(response.getBody().asString(), ListCreation.class);
    }

    public static ResponseBody responsetoListResponse(Response response) {
        return gson.fromJson(response.getBody().asString(), ResponseBody.class);

    }

    public static ResponseStatus responseStatusToListResponse(Response response) {
        return gson.fromJson(response.getBody().asString(), ResponseStatus.class);
    }

    public static Review responseToReview(Response response) {
        return gson.fromJson(response.getBody().asString(), Review.class);
    }

    public static JSONObject setRequestParam(String requestToken){
        JSONObject token= new JSONObject();
        token.put("request_token",requestToken);
        return token;
    }

    public static JSONObject setSessionParam(String session_id) {
        JSONObject session= new JSONObject();
        session.put("session_id",session_id);
        return session;
    }

    public static String setMovieParam(Integer value) {
        JSONObject movie= new JSONObject();
        movie.put("media_id",value);
        return movie.toString();

    }

    public static String setValueParam(Double value) {
        JSONObject valeEpisode= new JSONObject();
        valeEpisode.put("value",value);
        return valeEpisode.toString();
    }
}

