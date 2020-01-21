package ircon.org.assignment.ui.home.repository;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import ircon.org.assignment.utils.ApiCallInterface;


/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<JsonElement> executeCommittee() {
        return apiCallInterface.committee_list();
    }

    public Observable<JsonElement> executeGalleryCat() {
        return apiCallInterface.galleryCat_list();
    }


    public Observable<JsonElement> executeRegistration(String organizationName, String organizationAddress, String personName, String emailId, String phoneNo, String organozationnNo, String aboutOrg) {
        return apiCallInterface.agency_registration(organizationName,organizationAddress,personName,emailId,phoneNo,organozationnNo,aboutOrg);
    }

    public Observable<JsonElement> executeTeam() {
        return apiCallInterface.team_list();
    }

    public Observable<JsonElement> executeABoutIrcon() {
        return apiCallInterface.team_list();
    }

    public Observable<JsonElement> executePolicy() {
        return apiCallInterface.policy_list();
    }

    public Observable<JsonElement> executeExpList() {
        return apiCallInterface.exp_List();
    }

    public Observable<JsonElement> executeBudgetList(String expenditure_id) {
        return apiCallInterface.budget_List(expenditure_id);
    }

    public Observable<JsonElement> executeAboutIrcon(String csr) {
        return apiCallInterface.about_ircon(csr);
    }
}
