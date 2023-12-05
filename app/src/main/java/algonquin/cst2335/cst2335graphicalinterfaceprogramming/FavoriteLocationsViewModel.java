package algonquin.cst2335.cst2335graphicalinterfaceprogramming;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import algonquin.cst2335.data.FavoriteLocation;

/**
 * ViewModel class for managing the data related to favorite locations
 * This class extends the Android ViewModel and utilizes MutableLiveData
 * to observe changes in the data
 *
 * @author Hisham Khraibah
 * @version 1.0
 */
public class FavoriteLocationsViewModel extends ViewModel{
    /**
     * MutableLiveData representing the list of favorite locations
     * Observers can be notified of changes in this data
     */
    public MutableLiveData<ArrayList<FavoriteLocation>> favorite_location_array = new MutableLiveData<>();
}