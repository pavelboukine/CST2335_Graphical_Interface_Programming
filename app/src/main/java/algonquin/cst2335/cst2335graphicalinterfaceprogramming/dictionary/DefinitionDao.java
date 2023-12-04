package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DefinitionDao {
    @Query("SELECT distinct word FROM Definition")
    List<String> getAllWords();

    @Query("SELECT * FROM Definition WHERE word =:word")
    List<Definition> getDefinitionsForWord(String word);

    @Insert
    void insertAll(List<Definition> defs);

    @Query("DELETE FROM Definition WHERE word =:word")
    void deleteDefinitionsForWord(String word);

}
