package myAlbums;

public class Album {
    private String name;
    private String band;
    private int year;
    private int rating;
    private int id;

    public Album(int id) {
        this.id = id;
    }

    public Album(String name, String band, int year, int rating) {
        this.name = name;
        this.band = band;
        this.year = year;
        this.rating = rating;
    }

    public Album(String name, String band, int year, int rating, int id) {
        this.name = name;
        this.band = band;
        this.year = year;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBand() {
        return band;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public String toJson() {
        String name = getName();
        String band = getBand();
        int year = getYear();
        int rating = getRating();
        int id = getId();
        return "{\"id\": \"" + id + "\", \"name\": \"" + name + "\", \"band\": \"" + band + "\", \"year\": \"" + year + "\", \"rating\": \"" + rating + "\"}";
    }
}