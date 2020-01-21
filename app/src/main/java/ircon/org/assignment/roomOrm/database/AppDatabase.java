package ircon.org.assignment.roomOrm.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ircon.org.assignment.roomOrm.Dao.FavouriteDao;
import ircon.org.assignment.ui.home.model.CarModel;
import ircon.org.assignment.ui.home.model.CelebrityModel;


@Database(entities = {CelebrityModel.class, CarModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract FavouriteDao favouriteDao();



    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "findTemple")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                          //  .addMigrations(MIGRATION_3_4)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
