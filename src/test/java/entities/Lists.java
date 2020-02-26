package entities;

import java.util.List;

public class Lists {
    private String created_by;
    private String description;
    private int favorite_count;
    private String id;
    private List<Item> items;
    private int item_count;
    private String iso_639_1;
    private String name;
    private String poster_path;

    public Lists() {
    }

    public Lists(String created_by, String description, int favorite_count, String id, List<Item> items, int item_count, String iso_639_1, String name, String poster_path) {
        this.created_by = created_by;
        this.description = description;
        this.favorite_count = favorite_count;
        this.id = id;
        this.items = items;
        this.item_count = item_count;
        this.iso_639_1 = iso_639_1;
        this.name = name;
        this.poster_path = poster_path;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
