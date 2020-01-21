package ircon.org.assignment.utils

import com.google.gson.JsonElement

import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by ${Saquib} on 03-05-2018.
 */


interface ApiCallInterface {

    @FormUrlEncoded
    @POST(URLConstant.CSR_POLICY_EXP_WISE_URL)
    fun budget_List(@Field("expenditure_id") expenditure_id: String): Observable<JsonElement>

    @FormUrlEncoded
    @POST(URLConstant.GALLERY_CAT_WISE_LIST_URL)
    fun galleryCatWise_list(@Field("category") category: String): Observable<JsonElement>

    @GET(URLConstant.COMMITTEE_LIST)
    fun committee_list(): Observable<JsonElement>

    @GET(URLConstant.GALLERY_CAT_LIST)
    fun galleryCat_list(): Observable<JsonElement>

    @GET(URLConstant.TEAM_LIST)
    fun team_list(): Observable<JsonElement>

    @FormUrlEncoded
    @POST(URLConstant.AGENCY_REGISTRATION)
    fun agency_registration(@Field("organization_name") organization_name: String,@Field("address") address: String,
                            @Field("person_name") person_name: String,@Field("email") email: String,@Field("mobile") mobile: String,
                            @Field("registration_no") registration_no: String,@Field("brief_organization") brief_organization: String): Observable<JsonElement>

    @GET(URLConstant.POLICY_LIST)
    fun policy_list(): Observable<JsonElement>

    @GET(URLConstant.EXP_LIST_URL)
    fun exp_List(): Observable<JsonElement>



    @GET("aboutircon/{login}")
    fun about_ircon(@Path("login") login: String): Observable<JsonElement>

}
