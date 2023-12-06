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
    /**
     * Getter method to retrieve the unique identifier of the {@code Definition} entity.
     *
     * @return The unique identifier (uid) of the entity.
     */
    public int getUid() {
        return uid;
    }

    /**
     * Setter method to set the unique identifier of the Definition entity.
     *
     * @param uid The unique identifier to be set.
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Default constructor for the Definition class.
     */
    public Definition() {

    }

    /**
     * Parameterized constructor for the {@code Definition} class.
     *
     * @param word           The word to be defined.
     * @param definition     The definition of the word.
     * @param partOfSpeech   The part of speech of the word.
     */
    public Definition(String word, String definition, String partOfSpeech) {
        this.word = word;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
    }

    /**
     * Getter method to retrieve the word associated with the Definition.
     *
     * @return The word associated with the definition.
     */
    public String getWord() {
        return word;
    }

    /**
     * Setter method to set the word associated with the Definition.
     *
     * @param word The word to be set.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Getter method to retrieve the definition of the word.
     *
     * @return The definition of the word.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Setter method to set the definition of the word.
     *
     * @param definition The definition to be set.
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Getter method to retrieve the part of speech of the word.
     *
     * @return The part of speech of the word.
     */
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * Setter method to set the part of speech of the word.
     *
     * @param partOfSpeech The part of speech to be set.
     */
    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
