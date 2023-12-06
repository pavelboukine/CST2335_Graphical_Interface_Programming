package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * The Word class represents a word along with its definitions.
 */
public class Word {
    String word;
    List <Definition> definitions;

    /**
     * Default constructor. Initializes an empty list of definitions.
     */
    public Word() {
        this.definitions = new ArrayList<>();
    }

    /**
     * Parameterized constructor to create a Word with a specified word.
     *
     * @param word The actual word to be represented.
     */
    public Word(String word) {
        this();
        this.word = word;
    }

    /**
     * Retrieves the word represented by this instance.
     *
     * @return The word represented by this instance.
     */
    public String getWord() {

        return word;
    }

    /**
     * Sets the word to be represented by this instance.
     *
     * @param word The new word to be set.
     */
    public void setWord(String word) {

        this.word = word;
    }

    /**
     * Retrieves the list of definitions associated with the word.
     *
     * @return A list of definitions associated with the word.
     */
    public List<Definition> getDefinitions() {

        return definitions;
    }

    /**
     * Sets the list of definitions associated with the word.
     *
     * @param definitions The new list of definitions to be set.
     */
    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    /**
     * Adds a new definition to the list of definitions associated with the word.
     *
     * @param d The definition to be added.
     */
    public void addDefinition(Definition d) {
        this.definitions.add(d);
    }
}
