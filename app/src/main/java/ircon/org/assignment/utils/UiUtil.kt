package ircon.org.assignment.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build

import java.text.SimpleDateFormat
import android.util.Patterns
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import java.util.regex.Pattern


class UiUtil {

    fun isStoragePermissionGrantedActivity( activity:Activity): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {

                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 101)
                return false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true
        }
    }


    fun convertDate(date:String):String{
        var convertdate = ""
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate = spf.parse(date)
        spf = SimpleDateFormat("dd MMM yyyy")
        convertdate = spf.format(newDate)
        return convertdate
    }

    fun isValidEmail(email: CharSequence): Boolean {
        /*var isValid = true

        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val inputStr = email

        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputStr)
        if (matcher.matches()) {
            isValid = false
        }
        return isValid*/
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}