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

@Entity(tableName = "CelebrityModel")
class CelebrityModel() :Parcelable, BaseObservable() {
    @ColumnInfo(name = "age")
    private var age:String = ""
    @ColumnInfo(name = "popularity")
    private var popularity:String = ""
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


    public fun setName(name:String){
        this.name=name
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


    @Bindable
    public fun getPopularity():String{
        return popularity
    }


    public fun setPopularity(popularity:String){
        this.popularity=popularity
        notifyPropertyChanged(BR.popularity)


    }



    @Bindable
    public fun getAge():String{
        return age
    }

    public fun setAge(age:String){
        this.age=age
        notifyPropertyChanged(BR.age)


    }


    constructor(parcel: Parcel) : this() {
        age = parcel.readString()!!
        name = parcel.readString()!!
        popularity = parcel.readString()!!
        imageUrl = parcel.readString()!!

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(age)
        parcel.writeString(name)
        parcel.writeString(popularity)
        parcel.writeString(imageUrl)

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
                    .apply(RequestOptions.circleCropTransform())
                    .into(view)
        }
    }

}