package com.dao;

import com.entity.XiaozhangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaozhangView;

/**
 * 校长 Dao 接口
 *
 * @author 
 */
public interface XiaozhangDao extends BaseMapper<XiaozhangEntity> {

   List<XiaozhangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
