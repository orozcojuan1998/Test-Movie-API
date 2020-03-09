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

    public URL buildListDetail (){
        return new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(PropertiesHelper.getValueByKey("url.list.id")).
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
}
