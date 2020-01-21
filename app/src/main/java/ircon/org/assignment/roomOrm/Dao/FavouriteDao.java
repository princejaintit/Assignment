package ircon.org.assignment.roomOrm.Dao;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import io.reactivex.Flowable;
import ircon.org.assignment.ui.home.model.CarModel;
import ircon.org.assignment.ui.home.model.CelebrityModel;

@Dao
public interface FavouriteDao {

    @Insert
    void addCelebrityList(List<CelebrityModel> favouriteModel);

    @Query("select * from CelebrityModel")
    Flowable<List<CelebrityModel>> getCelebrityList();

    @Insert
    void addCarList(List<CarModel> favouriteModel);

    @Query("select * from CarModel")
    Flowable<List<CarModel>> getCarList();
}
