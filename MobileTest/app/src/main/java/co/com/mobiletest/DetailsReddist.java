package co.com.mobiletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.mobiletest.view.model.RedditsModelEntity;

/**
 * @author Jaime Gamboa
 * @see AppCompatActivity
 */
public class DetailsReddist extends AppCompatActivity {

    /**
     * Injection views
     */
    @BindView(R.id.ImageRedditDetails)
    ImageView ImageRedditDetails;
    @BindView(R.id.DetailRedditName)
    TextView DetailRedditName;
    @BindView(R.id.DescriptionHtmlReddit)
    TextView DescriptionHtmlReddit;

    /**
     * configuration and local variables
     */
    private RedditsModelEntity model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_reddist);
        ButterKnife.bind(this);
        model = (RedditsModelEntity) getIntent().getExtras().getSerializable("RedditsModelEntity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).load(model.header_img).into(ImageRedditDetails);
        DetailRedditName.setText(model.display_name);
        DescriptionHtmlReddit.setText(Html.fromHtml(model.public_description));
    }
}
