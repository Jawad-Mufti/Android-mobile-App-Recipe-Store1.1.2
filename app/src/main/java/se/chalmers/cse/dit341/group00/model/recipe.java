package se.chalmers.cse.dit341.group00.model;
import java.net.URL;

public class recipe {
    public String title;
    public String image;
    public String description;
    public String _id;


    recipe(String title, String image, String description, String _id) {
        this._id = _id;
        this.title=title;
        this.image=image;
        this.description=description;





        }
    public String getId() {
        return this._id;
    }


    }


