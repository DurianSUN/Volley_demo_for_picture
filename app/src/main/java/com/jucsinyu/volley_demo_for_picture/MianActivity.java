package com.jucsinyu.volley_demo_for_picture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jucsinsun on 2015/12/18.
 */
public class MianActivity extends Activity {
    private RequestQueue mRequestQueue;
    private ImageView pic_1,pic_2,pic_3,pic_4;
    private URL url ;
    private String url_string01,url_string02,url_string03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();
        final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(
                20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return mImageCache.get(key);
            }
        };
        ImageLoader mImageLoader = new ImageLoader(mRequestQueue, imageCache);
        // imageView是一个ImageView实例
        // ImageLoader.getImageListener的第二个参数是默认的图片resource id
        // 第三个参数是请求失败时候的资源id，可以指定为0
        ImageLoader.ImageListener listener01 = ImageLoader
                .getImageListener(pic_1, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);
        ImageLoader.ImageListener listener02 = ImageLoader
                .getImageListener(pic_2, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);
        ImageLoader.ImageListener listener03 = ImageLoader
                .getImageListener(pic_3, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);
        ImageLoader.ImageListener listener04 = ImageLoader
                .getImageListener(pic_4, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);

        mImageLoader.get(url_string01, listener01);
        mImageLoader.get(url_string02, listener02);
        mImageLoader.get(url_string03, listener03);
        mImageLoader.get(url_string02, listener04);

    }

    private void init() {
        mRequestQueue = Volley.newRequestQueue(this);
        pic_1 = (ImageView) findViewById(R.id.id_pic_01);
        pic_2 = (ImageView) findViewById(R.id.id_pic_02);
        pic_3 = (ImageView) findViewById(R.id.id_pic_03);
        pic_4 = (ImageView) findViewById(R.id.id_pic_04);

        url_string01 = "http://7xofac.com1.z0.glb.clouddn.com/mn1.jpg";
        url_string02 = "http://7xofac.com1.z0.glb.clouddn.com/mn2.jpg";
        url_string03 = "http://7xofac.com1.z0.glb.clouddn.com/mn3.jpg";


    }
}
