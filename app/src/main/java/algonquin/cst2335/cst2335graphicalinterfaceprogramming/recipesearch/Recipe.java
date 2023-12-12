package algonquin.cst2335.cst2335graphicalinterfaceprogramming.recipesearch;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * The Word class represents a word along with its definitions.
 */
@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id")
    int id;
    @ColumnInfo(name = "title")
    String recipe;
    @ColumnInfo(name = "image")
    String imgURL;

    @Ignore
    Bitmap img;



    /**
     * Getter method to retrieve the unique identifier of the {@code Definition} entity.
     *
     * @return The unique identifier (uid) of the entity.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method to set the unique identifier of the Definition entity.
     *
     * @param id The unique identifier to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Default constructor for the Definition class.
     */
    public Recipe() {

    }

    /**
     * Parameterized constructor for the {@code Definition} class.
     *
     * @param recipe         The word to be defined.
     * @param summary  The definition of the word.
     * @param url The part of speech of the word.
     * @param img   The Bitmap image of the recipe
     */
    public Recipe(int id, String recipe, String image) {
        this.setId(id);
        this.recipe = recipe;
        this.imgURL = image;

    }

    /**
     * Getter method to retrieve the word associated with the Definition.
     *
     * @return The word associated with the definition.
     */
    public String getRecipe() {
        return recipe;
    }

    /**
     * Setter method to set the word associated with the Definition.
     *
     * @param recipe The word to be set.
     */
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }



    /**
     * Getter method to retrieve the part of speech of the word.
     *
     * @return The part of speech of the word.
     */

    public String getImgURL() {
        return imgURL;
    }

    /**
     * Setter method to set the part of speech of the word.
     *
     * @param Img The part of speech to be set.
     */
    public void setImgURL(String img) {
        this.imgURL = img;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
