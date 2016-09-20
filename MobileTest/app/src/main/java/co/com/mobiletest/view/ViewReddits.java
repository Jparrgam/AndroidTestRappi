package co.com.mobiletest.view;

import android.view.View;

import java.util.List;

import co.com.mobiletest.view.model.RedditsModelEntity;

/**
 * @author Jaime Gamboa
 */
public interface ViewReddits {

    /**
     * show progress bar
     */
    void showProgress();

    /**
     * method execute http request failed
     * @param failure message failed
     */
    void onFailureRequest (String failure);

    /**
     * method execute http request success code #200
     * @param redditsModel response api
     */
    void onSuccessRequest (List<RedditsModelEntity> redditsModel);

    /**
     * call back method cardView selected item
     *
     * @param redditsModelEntity item selected
     */
    void onItemSelected (RedditsModelEntity redditsModelEntity, View view);
}
