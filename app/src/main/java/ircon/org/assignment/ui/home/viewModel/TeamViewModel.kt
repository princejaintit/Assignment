package irconcsr.ircon.org.irconcsrnew.ui.team.viewModel

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ircon.org.assignment.ui.home.model.CarModel

import ircon.org.assignment.ui.home.repository.Repository
import ircon.org.assignment.utils.ApiResponse
import ircon.org.assignment.ui.home.model.CelebrityModel
import io.reactivex.Flowable
import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import ircon.org.assignment.roomOrm.database.AppDatabase


class TeamViewModel(repository: Repository) : ViewModel() {
    init {
        //println("Initialized a new Person object with name = $name , age = $lastName" and salary = $salary)
    }

    private val repository: Repository? = repository
    private val disposables = CompositeDisposable()
    private val responseLiveData = MutableLiveData<ApiResponse>()

    private var modelList: MutableLiveData<ArrayList<CelebrityModel>>? = null

    private var carModelList: MutableLiveData<ArrayList<CarModel>>? = null

    fun loginResponse(): MutableLiveData<ApiResponse> {
        return responseLiveData
    }

   public fun CommiteeViewModel(){

    }

     var appDatabase: AppDatabase?=null

    fun getDattData(@NonNull application: Context) {
        appDatabase = AppDatabase.getAppDatabase(application)
    }

    fun getList(): Flowable<List<CelebrityModel>> {
        return appDatabase?.favouriteDao()!!.celebrityList
    }

    fun addData(list: List<CelebrityModel>) {
        return appDatabase?.favouriteDao()!!.addCelebrityList(list)
    }


    fun getCarList(): Flowable<List<CarModel>> {
        return appDatabase?.favouriteDao()!!.carList
    }

    fun addCarData(list: List<CarModel>) {
        return appDatabase?.favouriteDao()!!.addCarList(list)
    }

    /*
     * method to call normal login api with $(mobileNumber + password)
     * */
    fun hitCommitteeAPI(/*mobileNumber: String, password: String*/) {

        disposables.add(repository!!.executeTeam()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({ d -> responseLiveData.setValue(ApiResponse.loading()) })
                .subscribe(
                        { result -> responseLiveData.setValue(ApiResponse.success(result)) },
                        { throwable -> responseLiveData.setValue(ApiResponse.error(throwable)) }
                ))

    }




    fun data(response: JsonElement?, activity:Activity): MutableLiveData<ArrayList<CelebrityModel>>{

        if (modelList == null) {
            modelList = MutableLiveData()
            renderSuccessResponse(response,activity)
        }
        return modelList as MutableLiveData<ArrayList<CelebrityModel>>
    }

    private fun renderSuccessResponse(response: JsonElement?,activity:Activity) {
        if (response != null) {
            if (!response.isJsonNull) {
                Log.d("response=", response.toString())
                var json: JsonObject = response.asJsonObject

                var commiteeList=ArrayList<CelebrityModel>()


                var celebrities: JsonObject = json.getAsJsonObject("celebrities")

                val keys = celebrities.keySet()

               var nn: Iterator<String> =  keys.iterator()

                while (nn.hasNext()) {
                    val key = nn.next()
                    if (celebrities.get(key) is JsonObject) {
                       var json= celebrities.getAsJsonObject(key)

                        Log.d("dddddd",json.toString())

                        val commiteeModel: CelebrityModel = CelebrityModel()
                   commiteeModel.setAge("age: "+json.get("age").asString)
                   commiteeModel.setPopularity("popularity: "+json.get("popularity").asString)
                   commiteeModel.setImageUrl(json.get("photo").asString)
                        commiteeModel.setName("Name: "+key)
                        commiteeModel.setId(Math.random().toInt())

                    commiteeList?.add(commiteeModel)



                    modelList!!.postValue(commiteeList)
                    }
                }

                addData(commiteeList)


            } else {
                Toast.makeText(activity, activity.resources.getString(ircon.org.assignment.R.string.errorString), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun dataCar(response: JsonElement?, activity:Activity): MutableLiveData<ArrayList<CarModel>>{

        if (carModelList == null) {
            carModelList = MutableLiveData()
            renderSuccessResponseCars(response,activity)
        }
        return carModelList as MutableLiveData<ArrayList<CarModel>>
    }

    private fun renderSuccessResponseCars(response: JsonElement?,activity:Activity) {
        if (response != null) {
            if (!response.isJsonNull) {
                Log.d("response=", response.toString())
                var json: JsonObject = response.asJsonObject

                var carList=ArrayList<CarModel>()


                var celebrities: JsonObject = json.getAsJsonObject("cars")

                val keys = celebrities.keySet()

                var nn: Iterator<String> =  keys.iterator()

                while (nn.hasNext()) {
                    val key = nn.next()
                    if (celebrities.get(key) is JsonObject) {
                        var json= celebrities.getAsJsonObject(key)

                        Log.d("dddddd",json.toString())

                        val commiteeModel: CarModel = CarModel()
                        commiteeModel.setImageUrl(json.get("photo").asString)
                        commiteeModel.setName(key);

                        carList?.add(commiteeModel)

                        carModelList!!.postValue(carList)
                    }
                }
                addCarData(carList)

            } else {
                Toast.makeText(activity, activity.resources.getString(ircon.org.assignment.R.string.errorString), Toast.LENGTH_SHORT).show()
            }
        }
    }




    override fun onCleared() {
        disposables.clear()
    }

}