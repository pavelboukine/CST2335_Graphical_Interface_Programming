package algonquin.cst2335.cst2335graphicalinterfaceprogramming.recipesearch;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
 public class SpecificRecipe {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id")
    int id;
    @ColumnInfo(name = "sourceUrl")
    String sourceUrl;
    @ColumnInfo(name = "summary")
    String summary;

    @Ignore
    Bitmap image;



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public SpecificRecipe() {

    }


    public SpecificRecipe(int id, String source, String summary) {
        this.setId(id);
        this.sourceUrl = source;
        this.summary = summary;

    }


    public String getSourceURL() {
        return sourceUrl;
    }


    public void setSourceURL(String source) {
        this.sourceUrl = source;
    }



    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }
}