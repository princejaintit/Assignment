package irconcsr.ircon.org.irconcsrnew

import android.app.Activity
import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnDismissListener
import android.content.Intent
import android.net.Uri

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import ircon.org.assignment.R

import java.io.File

open class BaseActivity : AppCompatActivity() {

    /*@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(mToolbar);
	}
	*/
    /**
     * This function is used to show an alert to the user.
     *
     * @param context
     * - Context of the activity in which dialog is to be shown
     * @param title
     * - Title to be displayed in the alert dialog
     * @param message
     * - Message to be shown in the alter dialog
     * @param isCancelable
     * @param view
     * - view to be passed if the focus is required on dismiss of the
     * alert. (e.g., setting cursor/focus in EditText)
     * @param isFinished
     * - Whether the calling Activity needs to be finished on
     * dismissal of the dialog?
     */
    fun showMessage(context: Context, title: String,
                    message: String, isCancelable: Boolean, view: View?,
                    isFinished: Boolean) {
        val builder = Builder(context)
        val alertDialog = builder.create()
        if (title != "") {
            alertDialog.setTitle(title)
        }
        alertDialog.setMessage(message)
        alertDialog.setCancelable(isCancelable)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                getString(R.string.ok)
        ) { dialog, which ->
            if (view != null) {
                val shake = AnimationUtils
                        .loadAnimation(context, R.anim.shake)
                view.requestFocus()
                view.startAnimation(shake)
            }
        }

        alertDialog.setOnDismissListener {
            if (isFinished) {
                (context as Activity).finish()
            }
        }
        alertDialog.show()
    }


    /**
     * To start the activity
     * @param activity
     *
     * @param className
     * Activity class to be start
     */

    fun startActivityCustom(activity: Activity, className: Class<*>) {
        startActivity(Intent(activity, className))
    }

    /*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
	}*/


    fun isFilePresent(fileDir: File, fileName: String): Boolean {
        val path = fileDir.absolutePath + "/" + fileName
        val file = File(path)
        return file.exists()
    }


    fun openAppInPlayStore(paramContext: Context) {
        /* paramContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/")));*/
        val marketUri = Uri.parse("market://details?id=" + paramContext.packageName)
        val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
        startActivity(marketIntent)
    }


    fun openMyOtherApp() {
        /* paramContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/")));*/
        val marketUri = Uri.parse("https://play.google.com/store/apps/developer?id=Prince+Jain")
        val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
        startActivity(marketIntent)
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.local -> onBackPressed()
        }

        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_out_from_left, R.anim.slide_in_to_left)
    }

}
