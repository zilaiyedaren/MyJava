package org.myself.mobile.web.action.convenience.movie;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.movie.CinemaInfo;
import org.myself.mobile.web.bean.movie.MovieInfo;
import org.myself.mobile.web.bean.movie.Review;
import org.myself.mobile.web.bean.movie.TimeTable;
import org.myself.mobile.web.utils.JsonStrReader;
import org.springframework.http.HttpStatus;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-19
 * Time: 上午11:59
 * To change this template use File | Settings | File Templates.
 */
public class MovieAction extends BaseAction {
    @Action("hostMovie")
    public String hostMovie(){
        return SUCCESS;
    }
    @Action("movieInfo")
    public String movieInfo() throws Exception{
        String apiUrl="http://api.36wu.com/Movie/GetHotMovie?location=%E5%8C%97%E4%BA%AC";
        HttpEntity httpEntity=null;

        try {
            HttpGet httpGet=new HttpGet(apiUrl);
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode== HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    jsonObject=jsonObject.getJSONObject("data");
                    JSONArray jsonArray=jsonObject.getJSONArray("movie");
                    movieInfoList=new ArrayList<MovieInfo>();
                    JSONObject jb=null;
                    for(int i=0;i<jsonArray.length();i++){
                        jb=jsonArray.getJSONObject(i);
                        movieInfo=new MovieInfo();
                        movieInfo.setMovieIsImax(jb.getInt("is_imax"));
                        movieInfo.setMovieIsNew(jb.getInt("is_new"));
                        movieInfo.setMovieId(jb.getString("movie_id"));
                        movieInfo.setMovieName(jb.getString("movie_name"));
                        movieInfo.setMovieLength(jb.getString("movie_length"));
                        movieInfo.setMovieNation(jb.getString("movie_nation"));
                        movieInfo.setMovieDirector(jb.getString("movie_director"));
                        movieInfo.setMovieMessage(jb.getString("movie_message"));
                        movieInfo.setMovieReleaseDate(jb.getString("movie_release_date"));
                        movieInfo.setMoviePicture(jb.getString("movie_picture"));
                        movieInfo.setMovieScore(jb.getString("movie_score"));
                        movieInfo.setMovieStarring(jb.getString("movie_starring"));
                        movieInfo.setMovieTags(jb.getString("movie_tags"));
                        movieInfo.setMovieType(jb.getString("movie_type"));
                        movieInfo.setMovieWd(jb.getString("movies_wd"));
                        movieInfoList.add(movieInfo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }
        return SUCCESS;
    }

//    @Action("movieSearch")
//    public String movieSearch(){
//        return SUCCESS;
//    }
//    @Action("searchInfo")
//    public String searchInfo() throws Exception{
//        String apiUrl="http://api.36wu.com/Movie/GetMovieInfo?movie=%E6%94%BE%E6%89%8B%E7%88%B1&location=%E5%8C%97%E4%BA%AC";
//        return SUCCESS;
//    }

    @Action("cinemaSearch")
    public String cinemaSearch(){
        return SUCCESS;
    }

    @Action("cinemaInfo")
    public String cinemaInfo() throws Exception{
        String apiUrl="http://api.36wu.com/Movie/GetNearCinemaInfoByCinema?cinema=%E4%B8%87%E8%BE%BE%E5%BD%B1%E5%9F%8E&location=%E5%8C%97%E4%BA%AC";

        HttpEntity httpEntity=null;
        HttpGet httpGet=new HttpGet(apiUrl);
        HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.OK.value()){
            httpEntity=httpResponse.getEntity();
            JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
            if(jsonObject.getInt("status")==200){
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                cinemaInfoList=new ArrayList<CinemaInfo>();
                JSONObject jb=null;
                JSONObject jb1=null;
                JSONObject jb2=null;
                JSONObject jb3=null;
                for(int i=0;i<jsonArray.length();i++){
                    jb=jsonArray.getJSONObject(i);
                    cinemaInfo=new CinemaInfo();
                    cinemaInfo.setAddress(jb.getString("address"));
                    cinemaInfo.setName(jb.getString("name"));
                    cinemaInfo.setTelphone(jb.getString("telephone"));
                    JSONArray ja1=jb.getJSONArray("review");
                    reviewList=new ArrayList<Review>();
                    for(int j=0;j<ja1.length();j++){
                         jb1=ja1.getJSONObject(j);
                         review=new Review();
                         review.setContent(jb1.getString("content"));
                         review.setDate(jb1.getString("date"));
                         reviewList.add(review);
                    }
                    cinemaInfo.setReview(reviewList);
                    JSONArray ja2=jb.getJSONArray("movies");
                    movieInfoList=new ArrayList<MovieInfo>();
                    for(int k=0;k<ja2.length();k++){
                        jb2=ja2.getJSONObject(k);
                        movieInfo=new MovieInfo();
                        movieInfo.setMovieDescription(jb2.getString("movie_description").replace("&nbsp",""));
                        movieInfo.setMovieName(jb2.getString("movie_name"));
                        movieInfo.setMovieLength(jb2.getString("movie_length"));
                        movieInfo.setMovieNation(jb2.getString("movie_nation"));
                        movieInfo.setMovieDirector(jb2.getString("movie_director"));
                        movieInfo.setMovieMessage(jb2.getString("movie_message"));
                        movieInfo.setMovieReleaseDate(jb2.getString("movie_release_date"));
                        movieInfo.setMoviePicture(jb2.getString("movie_picture"));
                        movieInfo.setMovieScore(jb2.getString("movie_score"));
                        movieInfo.setMovieStarring(jb2.getString("movie_starring"));
                        movieInfo.setMovieTags(jb2.getString("movie_tags"));
                        movieInfo.setMovieType(jb2.getString("movie_type"));
                        JSONArray ja3=jb2.getJSONArray("time_table");
                        timeTableList=new ArrayList<TimeTable>();
                        for(int n=0;n<ja3.length();n++){
                            jb3=ja3.getJSONObject(n);
                            timeTable=new TimeTable();
                            timeTable.setDate(jb3.getString("date"));
                            timeTable.setLan(jb3.getString("lan"));
                            timeTable.setPrice(jb3.getDouble("price"));
                            timeTable.setTime(jb3.getString("time"));
                            timeTable.setType(jb3.getString("type"));
                            timeTableList.add(timeTable);
                        }
                        movieInfo.setTimeTable(timeTableList);
                        movieInfoList.add(movieInfo);
                    }
                    cinemaInfo.setMovie(movieInfoList);
                    cinemaInfoList.add(cinemaInfo);
                }
            }
        }
        return SUCCESS;
    }

//    @Action("nearCinema")
//    public String nearCinema(){
//        return SUCCESS;
//    }
//    @Action("nearInfo")
//    public String nearInfo() throws Exception{
//        String apiUrl="http://api.36wu.com/Movie/GetNearCinemaInfoByLocation?location=116.307852,40.057023";
//        return SUCCESS;
//    }
    private String city;

    private MovieInfo movieInfo;

    private List<MovieInfo> movieInfoList;

    private String cinema;

    private CinemaInfo cinemaInfo;

    private List<CinemaInfo> cinemaInfoList;

    private Review review;

    private TimeTable timeTable;

    private List<Review> reviewList;

    private List<TimeTable> timeTableList;


    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public List<CinemaInfo> getCinemaInfoList() {
        return cinemaInfoList;
    }

    public void setCinemaInfoList(List<CinemaInfo> cinemaInfoList) {
        this.cinemaInfoList = cinemaInfoList;
    }

    public List<MovieInfo> getMovieInfoList() {
        return movieInfoList;
    }

    public void setMovieInfoList(List<MovieInfo> movieInfoList) {
        this.movieInfoList = movieInfoList;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
