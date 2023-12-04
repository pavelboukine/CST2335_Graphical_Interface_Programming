package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Word {
    String word;
    List <Definition> definitions;

    public Word() {
        this.definitions = new ArrayList<>();
    }

    public Word(String word) {
        this();
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public void addDefinition(Definition d) {
        this.definitions.add(d);
    }
}
