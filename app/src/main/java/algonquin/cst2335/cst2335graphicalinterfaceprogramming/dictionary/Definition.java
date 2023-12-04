package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
 public class Definition {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "word")
    String word;
    @ColumnInfo(name = "definition")
    String definition;
    @ColumnInfo(name = "partOfSpeech")
    String partOfSpeech;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Definition() {

    }

    public Definition(String word, String definition, String partOfSpeech) {
        this.word = word;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
