package com.loveplusplus.update.sample.http;

import com.loveplusplus.update.sample.model.entity.TestAppUpdate;

import retrofit.http.GET;
import rx.Observable;

/**
 * get data
 * Created by xybcoder on 2016/3/1.
 */
public interface UpdateRetrofit {

    /*
   接口功能:测试APP更新接口,Github上提供的测试接口
   字段说明:
  */
    @GET("/feicien/android-auto-update/develop/extras/update.json")
    Observable<TestAppUpdate> checkTestAPPUpdate();
}
