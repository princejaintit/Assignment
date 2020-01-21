package ircon.org.assignment.ui.home.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Context.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonElement
import ircon.org.assignment.MyApplication

import ircon.org.assignment.ui.home.ViewModelFactory
import ircon.org.assignment.ui.home.adaptor.CarAdaptor
import ircon.org.assignment.ui.home.adaptor.TeamAdaptor
import ircon.org.assignment.ui.home.model.CarModel
import ircon.org.assignment.ui.home.model.CelebrityModel
import ircon.org.assignment.utils.ApiResponse
import ircon.org.assignment.utils.Constant
import ircon.org.assignment.utils.Status
import irconcsr.ircon.org.irconcsrnew.BaseActivity
import irconcsr.ircon.org.irconcsrnew.ui.team.viewModel.TeamViewModel
import kotlinx.android.synthetic.main.activity_commitee.*
import javax.inject.Inject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ircon.org.assignment.R





class TeamActivity : BaseActivity() {
    @set:Inject
    var viewModelFactory: ViewModelFactory? = null

     var progressDialog: ProgressDialog?=null
    var  viewModel : TeamViewModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_commitee)

        setSupportActionBar(toolbar_csr_commitee)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {

        progressDialog = Constant.getProgressDialog(this, "Please wait...")

        setSupportActionBar(toolbar_csr_commitee as Toolbar?)
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        commitee_recy.layoutManager= LinearLayoutManager(this@TeamActivity)
        car_recy.layoutManager= LinearLayoutManager(this@TeamActivity)

        commitee_recy.setNestedScrollingEnabled(false);
        commitee_recy.setNestedScrollingEnabled(false);

        (application as MyApplication).getAppComponent().doCommiteeActivityInjection(this)

          viewModel  = ViewModelProviders.of(this,viewModelFactory).get(TeamViewModel::class.java!!)

        viewModel?.getDattData(applicationContext)

        if(checkNetworkConnection()){
            viewModel?.hitCommitteeAPI()


            viewModel?.loginResponse()!!.observe(this, Observer {
                if (it != null) {
                    this.consumeResponse(it)
                }
            })
        }
            else {
            viewModel!!.getList().subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({        //consume modelClasses here which is a list of ModelClass
                        modelClasses ->
                        var dashboardGridAdaptor: TeamAdaptor = TeamAdaptor(this , modelClasses as ArrayList<CelebrityModel>)

                        commitee_recy.adapter = dashboardGridAdaptor
                        println("RoomWithRx: " + modelClasses.size)

                    }, { e -> println("RoomWithRx: " + e.message) })

            viewModel!!.getCarList().subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({        //consume modelClasses here which is a list of ModelClass
                        modelClasses ->
                        var dashboardGridAdaptor: CarAdaptor = CarAdaptor(this , modelClasses as ArrayList<CarModel>)

                        car_recy.adapter = dashboardGridAdaptor
                        println("RoomWithRx: " + modelClasses.size)

                    }, { e -> println("RoomWithRx: " + e.message) })

        }
    }

    fun checkNetworkConnection():Boolean{
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }

/*
     * method to handle response
     * */
private fun consumeResponse(apiResponse: ApiResponse) {
    when (apiResponse.status) {

        Status.LOADING -> progressDialog?.show()

        Status.SUCCESS -> {
            progressDialog?.dismiss()

            viewModel?.data(apiResponse.data!!,this)?.observe(this, Observer<ArrayList<CelebrityModel>> {

                var dashboardGridAdaptor: TeamAdaptor = TeamAdaptor(this ,it!!)

                commitee_recy.adapter = dashboardGridAdaptor
            })

            viewModel?.dataCar(apiResponse.data!!,this)?.observe(this, Observer<ArrayList<CarModel>> {

                var dashboardGridAdaptor: CarAdaptor = CarAdaptor(this ,it!!)

                car_recy.adapter = dashboardGridAdaptor
            })

            //renderSuccessResponse(apiResponse.data!!)
        }

        Status.ERROR -> {
            progressDialog?.dismiss()
            Toast.makeText(this@TeamActivity, resources.getString(R.string.errorString), Toast.LENGTH_SHORT).show()
        }

        else -> {
        }
    }


}
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        getMenuInflater().inflate(R.menu.option, menu);
//        return true;
//    }
}