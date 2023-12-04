package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;


import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Definition.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DefinitionDao definitionDao();
}
