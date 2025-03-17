package vn.edu.hou.mttha.demosqlite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
    private static volatile StudentDatabase INSTANCE;
    public static StudentDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (StudentDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase.class,"student_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
