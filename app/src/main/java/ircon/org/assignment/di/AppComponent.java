package ircon.org.assignment.di;


import javax.inject.Singleton;

import dagger.Component;

import ircon.org.assignment.ui.home.view.TeamActivity;


/**
 * Created by ${Saquib} on 03-05-2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doCommiteeActivityInjection(TeamActivity activity);



}
