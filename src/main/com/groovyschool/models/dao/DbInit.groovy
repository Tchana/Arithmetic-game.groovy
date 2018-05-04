package models.dao

public class DbInit {

    public static initDb(db){
        String createScoresTable = "CREATE TABLE IF NOT EXISTS scores (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " points INTEGER," +
                " date NUMERIC)"

        db.execute(createScoresTable)
    }
}