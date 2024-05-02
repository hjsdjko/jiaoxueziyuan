package com.dao;

import com.entity.KejianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.KejianView;

/**
 * 课件 Dao 接口
 *
 * @author 
 */
public interface KejianDao extends BaseMapper<KejianEntity> {

   List<KejianView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
