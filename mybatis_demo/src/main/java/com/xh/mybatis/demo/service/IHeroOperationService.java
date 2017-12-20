package com.xh.mybatis.demo.service;

import com.xh.mybatis.demo.dao.model.Hero;
import com.xh.mybatis.demo.vo.request.AddHeroRequest;
import com.xh.mybatis.demo.vo.request.UpdateHeroRequest;

import java.util.List;


/**
 *
 * @author xionghao
 * @date 2017/12/12
 */
public interface IHeroOperationService {
    void addHero(AddHeroRequest request);

    Hero findById(Integer id);

    List<Hero> findHeros();

    Hero updateHero(UpdateHeroRequest updateHeroRequest);

    void deleteHero(Integer id);
}
