package ad0424.yls.example.com.pushdemo;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "04nuzW9FNfzgZ2pQOTVhGktD";
    private Banner banner;
    //设置图片资源:url或本地资源
    private List<String> images = new ArrayList<>();
    private boolean isStop = true;

    //设置图片标题:自动对应
    private List<String> titles = new ArrayList<>();
    private FloatingActionButton mFloatingActionButton;
private  int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applyPermissions();
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, API_KEY);


        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                R.layout.custompushnotification, R.id.iv_img, R.id.tv_title, R.id.tv_content);
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        //  cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        // cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);

        cBuilder.setNotificationSound(Uri.withAppendedPath(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 推送高级设置，通知栏样式设置为下面的ID
        PushManager.setNotificationBuilder(this, 1, cBuilder);

        initViews();
    }

    private void applyPermissions() {
        String permissions[] = {Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
   if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
       ActivityCompat.requestPermissions(MainActivity.this,permissions,1001);
   }
    }

    private void initViews() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.faButton);


        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493055021075&di=a76c783ac7ab881ac8dba5906f416d76&imgtype=jpg&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2Fe4dde71190ef76c604622df99816fdfaae5167b4.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=324829640,951724642&fm=23&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4188294320,149968986&fm=23&gp=0.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=204624335,391968255&fm=23&gp=0.jpg");
        images.add("http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg");
        images.add("http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg");
        images.add("http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg");

        titles.add("十大星级品牌联盟，全场2折起");
        titles.add("全场2折起十大星级品牌联盟");
        titles.add("嗨购5折不要停12趁现在");
        titles.add("实打实大顶顶顶顶");
        titles.add("实打实大顶顶顶顶");
        titles.add("全场2折起十大星级品牌联盟");
        titles.add("嗨购5折不要停12趁现在");


        banner = (Banner) findViewById(R.id.banner);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);

        final List< Class<? extends ViewPager.PageTransformer>> list = new ArrayList<>();

        /*
        Transformer.Default
Transformer.Accordion
Transformer.BackgroundToForeground
Transformer.ForegroundToBackground
Transformer.CubeIn
Transformer.CubeOut
Transformer.DepthPage
Transformer.FlipHorizontal
Transformer.FlipVertical
Transformer.RotateDown
Transformer.RotateUp
Transformer.ScaleInOut
Transformer.Stack
Transformer.Tablet
Transformer.ZoomIn
Transformer.ZoomOut
Transformer.ZoomOutSlide
         */


       list.add(Transformer.Default);
       // list.add(Transformer.Accordion);

       list.add(Transformer.ForegroundToBackground);
     list.add(Transformer.CubeIn);
//
     list.add(Transformer.RotateUp);

      // list.add(Transformer.FlipVertical);
       list.add(Transformer.RotateDown);

        list.add(Transformer.ScaleInOut);
       list.add(Transformer.Stack);
        list.add(Transformer.ZoomOutSlide);
        list.add(Transformer.Tablet);
        list.add(Transformer.ZoomOutSlide);



        //设置banner动画效果
      //  banner.setBannerAnimation(Transformer.DepthPage);



        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if(i == list.size()){
                            i = 0;
                        }

                        banner.setBannerAnimation(list.get(i));
                        i++;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isStop) {
                    banner.stopAutoPlay();
                    mFloatingActionButton.setImageResource(R.drawable.begin);
                    isStop = false;
                } else {
                    banner.startAutoPlay();
                    mFloatingActionButton.setImageResource(R.drawable.stop);
                    isStop = true;
                }
            }
        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */


            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);


            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
