package com.xh.mybatis.demo.dao.mapper;

import com.xh.mybatis.demo.dao.model.Hero;
import com.xh.mybatis.demo.dao.model.HeroExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Mapper
public interface HeroMapper {
    @SelectProvider(type=HeroSqlProvider.class, method="countByExample")
    int countByExample(HeroExample example);

    @Delete({
        "delete from hero",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into hero (id, name, ",
        "price, sex, type, ",
        "create_time)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=DOUBLE}, #{sex,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Hero record);

    @InsertProvider(type=HeroSqlProvider.class, method="insertSelective")
    int insertSelective(Hero record);

    @SelectProvider(type=HeroSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Hero> selectByExample(HeroExample example);

    @Select({
        "select",
        "id, name, price, sex, type, create_time",
        "from hero",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Hero selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, name, price, sex, type, create_time",
            "from hero",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Hero selectByName(@Param("name") String name);

    @UpdateProvider(type=HeroSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Hero record);

    @Update({
        "update hero",
        "set name = #{name,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DOUBLE},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Hero record);
}