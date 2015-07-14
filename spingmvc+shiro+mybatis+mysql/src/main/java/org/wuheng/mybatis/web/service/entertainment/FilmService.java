package org.wuheng.mybatis.web.service.entertainment;

import org.wuheng.mybatis.web.formBean.FilmForm;
import org.wuheng.mybatis.web.slient.pojo.Film;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public interface FilmService {
    public DataGridResult listFilm(FilmForm filmForm);
    public void createFilm(Film film);
    public void updateFilm(Film film);
    public void deleteFilm(Long... filmIds);
}
