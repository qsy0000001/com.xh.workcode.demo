package com.xh.mybatis.demo.service.impl;

import com.xh.mybatis.demo.dao.mapper.HeroMapper;
import com.xh.mybatis.demo.dao.model.Hero;
import com.xh.mybatis.demo.dao.model.HeroExample;
import com.xh.mybatis.demo.service.IHeroOperationService;
import com.xh.mybatis.demo.util.RedisUtils;
import com.xh.mybatis.demo.vo.request.AddHeroRequest;
import com.xh.mybatis.demo.vo.request.UpdateHeroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 *
 * @author xionghao
 * @date 2017/12/12
 */
@CacheConfig(cacheNames = "heros")
@Service("heroOperationService")
public class HeroOperationServiceImpl implements IHeroOperationService {

    @Autowired
    HeroMapper heroMapper;
    @Autowired
    RedisUtils redisUtils;

    /**
     *  因为必须要有返回值，才能保存到数据库中，如果保存的对象的某些字段是需要数据库生成的，
     *  那保存对象进数据库的时候，就没必要放到缓存了.
     * @param request
     */
    @Override
    public void addHero(AddHeroRequest request) {
        Hero hero = new Hero();
        hero.setName(request.getName());
        hero.setPrice(request.getPrice());
        hero.setSex(request.getSex());
        hero.setType(request.getType());
        hero.setCreateTime(new Date());
        int rows = heroMapper.insert(hero);
        System.out.println("rows = "+rows);
    }

    @Cacheable(key = "#p0.toString()")
    @Override
    public Hero findById(Integer id) {
        Hero hero = heroMapper.selectByPrimaryKey(id);
        System.out.println("hero = "+hero);
        return hero;
    }

    @Override
    public List<Hero> findHeros() {
        HeroExample example = new HeroExample();
        List<Hero> heros = heroMapper.selectByExample(example);
        System.out.println("size = "+heros.size());
        return heros;
    }

    @CachePut(key = "#p0.id.toString()")
    @Override
    public Hero updateHero(UpdateHeroRequest request) {
        Hero hero = heroMapper.selectByPrimaryKey(request.getId());
        hero.setSex(request.getSex());
        hero.setName(request.getName());
        hero.setType(request.getType());
        hero.setPrice(request.getPrice());
        System.out.println("hero2 = "+hero);
        int rows = heroMapper.updateByPrimaryKey(hero);
        System.out.println("update rows = "+rows);
        return hero;
    }

    @CacheEvict(key = "#p0.toString()")
    @Override
    public void deleteHero(Integer id) {
        int rows = heroMapper.deleteByPrimaryKey(id);
        System.out.println("delete rows = "+rows);
    }


}
