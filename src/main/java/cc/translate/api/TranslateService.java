package cc.translate.api;

import cc.translate.api.model.DictResult;
import cc.translate.api.model.TokenKey;
import cc.translate.api.model.TranslateResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Translate API Service
 * 
 */
interface TranslateService {

    @GET("/")
    @Headers({
            "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94"
                    + " Safari/537.37"
        })
    Call<TokenKey> getTokenKey();

    @POST("/translate_a/single?client=webapp&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie="
            + "UTF-8&oe=UTF-8&getSourceText=btn&ssel=0&tsel=0&kc=1")
    @Headers({
        "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 "
                + "Safari/537.37",
        "referer: https://translate.google.cn/?hl=zh-CN&tab=wT",
        "authority: translate.google.cn"
        })
    @FormUrlEncoded
    Call<TranslateResult> translate(@Query("sl") String sourceLanguage, @Query("tl") String targetLanguage,
                                    @Field("q") String keywords, @Query("tk") String token);
    
    @Streaming
    @Headers({
        "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 "
                + "Safari/537.37",
        "referer: https://translate.google.cn/?hl=zh-CN&tab=wT",
        "authority: translate.google.cn"
        })
    

    @GET("/translate_tts?ie=UTF-8&total=1&idx=0&client=t")
    Call<ResponseBody> audio(
    		@Query("q") String keywords, @Query("tk") String token,@Query("textlen")Integer textlen,@Query("tl") String tl);
    
    @GET("http://www.iciba.com/{word}")
    Call<DictResult> translateDict(
    		@Path("word") String word);
}