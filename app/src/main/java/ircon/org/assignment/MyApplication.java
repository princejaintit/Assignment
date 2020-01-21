package ircon.org.assignment;

import android.app.Application;
import android.content.Context;

import ircon.org.assignment.di.AppComponent;
import ircon.org.assignment.di.AppModule;
import ircon.org.assignment.di.DaggerAppComponent;

import ircon.org.assignment.di.UtilsModule;


/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();


    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
