package ircon.org.assignment.ui.Splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent

import android.os.Handler
import ircon.org.assignment.R
import ircon.org.assignment.ui.home.view.TeamActivity


class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        initView()

    }

    private fun initView() {
        Handler().postDelayed(Runnable /*
              * Showing splash screen with a timer. This will be useful when you
              * want to show case your app logo / company
              */

        {
            val intent = Intent(this@SplashActivity, TeamActivity::class.java)
            //  intent.putExtra("fromSplash",true);
            startActivity(intent)

            finish()
        }, 2000)


    }


}