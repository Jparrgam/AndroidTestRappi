package co.com.mobiletest.iterator;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.com.mobiletest.persistent.RedditDatabase;
import co.com.mobiletest.util.constant;
import co.com.mobiletest.view.model.RedditsModelEntity;
import cz.msebera.android.httpclient.Header;

/**
 * @author Jaime Gamboa
 * @see co.com.mobiletest.iterator.GetRedditsIterator
 */
@SuppressWarnings("ALL")
public class GetRedditsIteratorImpl implements GetRedditsIterator {

    private List<RedditsModelEntity> redditsModelEntity = new ArrayList<>();

    /**
     * method to perform services HTTPGet
     *
     * @param service
     * @param onRequestFinished
     */
    @Override
    public void getInfoService(String service, final onRequestFinished onRequestFinished) {
        new AsyncHttpClient().get(service, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    try {
                        JSONArray jsonObject = new JSONObject(new String(responseBody)).getJSONObject(constant._SERVICE_RESPONSE_ARRAY).getJSONArray(constant._SERVICE_RESPONSE_ARRAY_CHILDREN);
                        for (int i = 0; i < jsonObject.length(); i++) {
                            RedditsModelEntity entity = new RedditsModelEntity (
                                i,
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("icon_img"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("submit_text_html"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("submit_text"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("display_name"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("header_img"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("description_html"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("public_description_html"),
                                jsonObject.getJSONObject(i).getJSONObject("data").getString("public_description")
                            );
                            redditsModelEntity.add(entity);
                            RedditDatabase.saveReddit(entity);
                        }
                        onRequestFinished.onSuccessRequest(redditsModelEntity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(GetRedditsIteratorImpl.class.getSimpleName(), "onFailure");
                onRequestFinished.onFailureRequest(error.getMessage());
            }
        });
    }

    /**
     * method execute bd
     *
     * @param onRequestFinished
     */
    @Override
    public void getInfoOfflineMode(onRequestFinished onRequestFinished) {
        List<RedditsModelEntity> entities = RedditDatabase.getReddit();
        onRequestFinished.onSuccessRequest(entities);
    }
}
