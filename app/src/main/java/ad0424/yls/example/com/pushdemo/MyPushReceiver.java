package ad0424.yls.example.com.pushdemo;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by yhdj on 2017/4/24.
 */

public class MyPushReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        Log.i("aaaaaaaaaaa", "onBind: " + errorCode + " " + appid + "  " + userId + "  "  + requestId);
        Log.e("AAAAAAAAAAAAA", "onBind: " + channelId);


        OkHttpUtils
                .post()//
                .url("http://192.168.134.83:8080/PushDemo/channelId")//
                .addParams("channelId",channelId)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaaaaaaaaaaaaaa", "onError: " +"error" );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaaaaaaaaaaaaaa", "onError: " +"success" );
                    }
                });


    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s, String s1) {

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
