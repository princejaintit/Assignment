package ircon.org.assignment.ui.home;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ircon.org.assignment.roomOrm.database.AppDatabase;
import ircon.org.assignment.ui.home.repository.Repository;
import irconcsr.ircon.org.irconcsrnew.ui.team.viewModel.TeamViewModel;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


         if (modelClass.isAssignableFrom(TeamViewModel.class)) {
            return (T) new TeamViewModel(repository);
        }


        throw new IllegalArgumentException("Unknown class name");
    }
}
