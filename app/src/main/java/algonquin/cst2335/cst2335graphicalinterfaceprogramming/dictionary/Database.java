package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;


import androidx.room.RoomDatabase;

/**
 * The Database class serves as the main access point to the Room database.
 * It extends RoomDatabase, providing a concrete implementation for the database operations.
 *
 * @Database annotation is used to declare the entities that belong to this database
 * and specify the database version.
 */
@androidx.room.Database(entities = {Definition.class}, version = 1)
public abstract class Database extends RoomDatabase {
    /**
     * Returns an instance of the DefinitionDao, which serves as the Data Access Object
     * for interacting with the "definition" table in the database.
     *
     * @return DefinitionDao instance for performing database operations related to Definition entities.
     */
    public abstract DefinitionDao definitionDao();
}
