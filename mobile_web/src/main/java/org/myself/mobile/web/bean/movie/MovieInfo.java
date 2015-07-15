package org.myself.mobile.web.bean.movie;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-19
 * Time: 下午12:31
 * To change this template use File | Settings | File Templates.
 */
public class MovieInfo {
//movie_id 	string 	影片id
//    movie_name 	string 	影片名称
//    movie_type 	string 	影片类型
//    movie_release_date 	string 	影片上映时间
//    movie_nation 	string 	影片所属国家
//    movie_starring 	string 	影片演员
//    movie_length 	string 	影片时长
//    movie_picture 	string 	影片图片地址
//    movie_score 	string 	影片评分
//    movie_director 	string 	影片导演
//    movie_tags 	string 	影片所属类型
//    movie_message 	string 	影片概要信息
//    is_imax 	int 	是否imax类型
//    is_new 	int 	是否首映
//    movies_wd 	string 	影片关键字
    private String movieDescription;

    private String movieId;

    private String movieName;

    private String movieType;

    private String movieReleaseDate;

    private String movieNation;

    private String movieStarring;

    private String movieLength;

    private String moviePicture;

    private String movieScore;

    private String movieDirector;

    private String movieTags;

    private String movieMessage;

    private int movieIsImax;

    private int movieIsNew;

    private String movieWd;

    private List<TimeTable> timeTable;

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public List<TimeTable> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTable> timeTable) {
        this.timeTable = timeTable;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieStarring() {
        return movieStarring;
    }

    public void setMovieStarring(String movieStarring) {
        this.movieStarring = movieStarring;
    }

    public String getMovieNation() {
        return movieNation;
    }

    public void setMovieNation(String movieNation) {
        this.movieNation = movieNation;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public String getMoviePicture() {
        return moviePicture;
    }

    public void setMoviePicture(String moviePicture) {
        this.moviePicture = moviePicture;
    }

    public String getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(String movieScore) {
        this.movieScore = movieScore;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieTags() {
        return movieTags;
    }

    public void setMovieTags(String movieTags) {
        this.movieTags = movieTags;
    }

    public String getMovieMessage() {
        return movieMessage;
    }

    public void setMovieMessage(String movieMessage) {
        this.movieMessage = movieMessage;
    }

    public int getMovieIsImax() {
        return movieIsImax;
    }

    public void setMovieIsImax(int movieIsImax) {
        this.movieIsImax = movieIsImax;
    }

    public int getMovieIsNew() {
        return movieIsNew;
    }

    public void setMovieIsNew(int movieIsNew) {
        this.movieIsNew = movieIsNew;
    }

    public String getMovieWd() {
        return movieWd;
    }

    public void setMovieWd(String movieWd) {
        this.movieWd = movieWd;
    }
}
