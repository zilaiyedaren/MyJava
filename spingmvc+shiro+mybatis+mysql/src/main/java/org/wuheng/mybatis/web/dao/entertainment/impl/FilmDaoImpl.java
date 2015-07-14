package org.wuheng.mybatis.web.dao.entertainment.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.entertainment.FilmDao;
import org.wuheng.mybatis.web.formBean.FilmForm;
import org.wuheng.mybatis.web.slient.pojo.Film;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
@Repository("filmDao")
public class FilmDaoImpl extends SqlSessionDaoSupport implements FilmDao {
    @Override
    public int getFilmCount(FilmForm filmForm) {
        return (Integer) getSqlSession().selectOne("SHIRO-FILM.getFilmCount",filmForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Film> getFilmList(FilmForm filmForm) {
        return getSqlSession().selectList("SHIRO-FILM.getFilmList",filmForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createFilm(Film film) {
        getSqlSession().insert("SHIRO-FILM.createFilm",film);
    }

    @Override
    public void updateFilm(Film film) {
        getSqlSession().update("SHIRO-FILM.updateFilm",film);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteFilm(Long... filmIds) {
        for(int i=0;i<filmIds.length;i++){
            getSqlSession().delete("SHIRO-FILM.deleteFilm",filmIds[i]);
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
