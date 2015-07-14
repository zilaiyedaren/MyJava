package org.wuheng.mybatis.web.service.entertainment.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.TagDao;
import org.wuheng.mybatis.web.dao.entertainment.FilmDao;
import org.wuheng.mybatis.web.formBean.FilmForm;
import org.wuheng.mybatis.web.service.entertainment.FilmService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.Film;
import org.wuheng.mybatis.web.slient.pojo.Tag;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
@Service("filmService")
public class FilmServiceImpl implements FilmService {
    protected Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private FilmDao filmDao;

    @Autowired
    private TagDao tagDao;

    @Override
    public DataGridResult listFilm(FilmForm filmForm) {
        filmForm.initOffset();
        int filmCount=filmDao.getFilmCount(filmForm);
        List<Film> list=filmDao.getFilmList(filmForm);
        return new DataGridResult(filmCount,list);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createFilm(Film film) {
        // screen , country 无法从前端传递 由此查询赋值
        Tag tag =tagDao.getTagById(film.getScreenId()) ;
        film.setScreen(tag.getName());
        tag=tagDao.getTagById(film.getCountryId());
        film.setCountry(tag.getName());
        filmDao.createFilm(film);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateFilm(Film film) {
        filmDao.updateFilm(film);
        CacheUtil.clearAuthInfo();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteFilm(Long... filmIds) {
        filmDao.deleteFilm(filmIds);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
