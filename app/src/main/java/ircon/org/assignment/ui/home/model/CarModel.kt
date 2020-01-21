package ircon.org.assignment.ui.home.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import ircon.org.assignment.BR
import ircon.org.assignment.R

@Entity(tableName = "CarModel")
class CarModel() :Parcelable, BaseObservable(){
    @ColumnInfo(name = "name")
    private var name:String = ""
    @ColumnInfo(name = "imageUrl")
    private var imageUrl:String = ""


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var id: Int = 0



    public fun getId():Int{
        return id
    }


    public fun setId(id:Int){
        this.id=id



    }

    @Bindable
    public fun getName():String{
        return name
    }


    public fun setName(name:String) {
        this.name = name
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    public fun getImageUrl():String{
        return imageUrl
    }


    public fun setImageUrl(imageUrl:String){
        this.imageUrl=imageUrl
        notifyPropertyChanged(BR.imageUrl)


    }





    constructor(parcel: Parcel) : this() {

        imageUrl = parcel.readString()!!
        name = parcel.readString()!!

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(imageUrl)
        parcel.writeString(name)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<CelebrityModel> = object : Parcelable.Creator<CelebrityModel> {
            override fun createFromParcel(`in`: Parcel): CelebrityModel {
                return CelebrityModel(`in`)
            }

            override fun newArray(size: Int): Array<CelebrityModel?> {
                return arrayOfNulls(size)
            }
        }

        @JvmStatic @BindingAdapter("profileImage")
        fun loadImage(view: CircleImageView, imageUrl: String) {
            Glide.with(view.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .apply(RequestOptions.circleCropTransform())
                    .into(view)
        }
    }
}