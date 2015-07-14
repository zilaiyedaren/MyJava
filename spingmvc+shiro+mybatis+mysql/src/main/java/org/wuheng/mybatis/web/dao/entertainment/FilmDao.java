package org.wuheng.mybatis.web.dao.entertainment;

import org.wuheng.mybatis.web.formBean.FilmForm;
import org.wuheng.mybatis.web.slient.pojo.Film;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public interface FilmDao {
    public int getFilmCount(FilmForm filmForm);

    // 获取满足条件的film列表对象
    public List<Film> getFilmList(FilmForm filmForm);

    // 添加新影片
    public void createFilm(Film film);

    public void updateFilm(Film film);

    public void deleteFilm(Long... filmIds);
}
