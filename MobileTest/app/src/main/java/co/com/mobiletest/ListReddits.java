package co.com.mobiletest;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import co.com.mobiletest.adapter.RecyclerViewRedditAdapter;
import co.com.mobiletest.presenter.GetRedditsPresenter;
import co.com.mobiletest.presenter.GetRedditsPresenterImpl;
import co.com.mobiletest.util.constant;
import co.com.mobiletest.view.ViewReddits;
import co.com.mobiletest.view.model.RedditsModelEntity;
import top.wefor.circularanim.CircularAnim;


/**
 * @author Jaime Gamboa
 * @see AppCompatActivity
 * @see ViewReddits
 */
@SuppressWarnings("ALL")
public class ListReddits extends AppCompatActivity implements ViewReddits {

    /**
     * Injection views
     */
    @BindView(R.id.recyclerViewReddists)
    RecyclerView recyclerViewReddists;
    @BindView(R.id._progress_bar_reddists)
    ProgressWheel _progress_bar_reddists;

    /**
     * configuration and local variables
     */
    private GetRedditsPresenter getReddistsPresenter;
    private RecyclerViewRedditAdapter recyclerViewRedditAdapter;

    /**
     * onCreate view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reddists);
        ButterKnife.bind(this);

        getReddistsPresenter = new GetRedditsPresenterImpl(this);
        getReddistsPresenter.setContext(this);

        if(recyclerViewReddists != null) {
            recyclerViewReddists.setHasFixedSize(true);
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
            recyclerViewReddists.setLayoutManager(gridLayoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewReddists.setLayoutManager(linearLayoutManager);
        }

        getReddistsPresenter.getReddists(constant._SERVICE_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getReddistsPresenter.onDestroyView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * show progress bar
     */
    @Override
    public void showProgress() {
        _progress_bar_reddists.setVisibility(View.VISIBLE);
    }

    /**
     * method execute http request failed
     *
     * @param failure message failed
     */
    @Override
    public void onFailureRequest(String failure) {
        _progress_bar_reddists.setVisibility(View.INVISIBLE);
        Log.e(this.getLocalClassName().getClass().getSimpleName(),"onFailureRequest response " +failure);
    }

    /**
     * method execute http request success code #200
     *
     * @param redditsModel response api
     */
    @Override
    public void onSuccessRequest(List<RedditsModelEntity> redditsModel) {
        _progress_bar_reddists.setVisibility(View.INVISIBLE);
        recyclerViewRedditAdapter = new RecyclerViewRedditAdapter(redditsModel, this, this);
        recyclerViewReddists.setAdapter(recyclerViewRedditAdapter);
        Toast.makeText(ListReddits.this, getResources().getString(R.string._details_item),Toast.LENGTH_SHORT).show();
    }

    /**
     * call back method cardView selected item
     *
     * @param redditsModelEntity item selected
     */
    @Override
    public void onItemSelected(final RedditsModelEntity redditsModelEntity, View view) {
        CircularAnim.fullActivity(ListReddits.this, view).go(new CircularAnim.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(ListReddits.this, DetailsReddist.class).putExtra("RedditsModelEntity",(Serializable)redditsModelEntity));
            }
        });
    }
}
