package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import entities.*;
import io.restassured.response.Response;

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
        System.out.println(gson.toJson(jsonElement));
        return gson.toJson(jsonElement);
    }

    public static String createSessionCreationBody (String token){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(token);
        return gson.toJson(jsonElement);
    }

    public static Employee[] responseToEmployeeArray(Response response){
        return gson.fromJson(response.body().print(), Employee[].class);
    }

    public static ResponseData responteToJson (Response response){
        return gson.fromJson(response.getBody().print(), ResponseData.class);
    }
    public static RequestToken responseRequestTokenToJson (Response response){
        return gson.fromJson(response.getBody().print(), RequestToken.class);
    }

    public static GuestSessionToken guestSessionToken (Response response){
        return gson.fromJson(response.getBody().print(), GuestSessionToken.class);
    }

    public static ResponseData[] responseToResponseArray(Response response){
        return gson.fromJson(response.body().print(), ResponseData[].class);
    }

    public static SessionData sessionToResponse(Response response) {
        return gson.fromJson(response.getBody().print(), SessionData.class);
    }

    public static Lists responseToList(Response response) {
        return gson.fromJson(response.getBody().print(), Lists.class);

    }

    public static ListCreation responseToCreationList(Response response) {
        return gson.fromJson(response.getBody().print(), ListCreation.class);
    }

    public static ResponseBody responsetoListResponse(Response response) {
        return gson.fromJson(response.getBody().print(), ResponseBody.class);

    }
}

