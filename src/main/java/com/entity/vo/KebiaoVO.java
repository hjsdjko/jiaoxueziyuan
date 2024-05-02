package com.entity.vo;

import com.entity.KebiaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 课表
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("kebiao")
public class KebiaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 节数
     */

    @TableField(value = "sections_types")
    private Integer sectionsTypes;


    /**
     * 班级
     */

    @TableField(value = "clazz_id")
    private Integer clazzId;


    /**
     * 学期
     */

    @TableField(value = "xueqi_types")
    private Integer xueqiTypes;


    /**
     * 课程
     */

    @TableField(value = "kecheng")
    private String kecheng;


    /**
     * 排序字段
     */

    @TableField(value = "paixu_number")
    private Integer paixuNumber;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：节数
	 */
    public Integer getSectionsTypes() {
        return sectionsTypes;
    }


    /**
	 * 获取：节数
	 */

    public void setSectionsTypes(Integer sectionsTypes) {
        this.sectionsTypes = sectionsTypes;
    }
    /**
	 * 设置：班级
	 */
    public Integer getClazzId() {
        return clazzId;
    }


    /**
	 * 获取：班级
	 */

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }
    /**
	 * 设置：学期
	 */
    public Integer getXueqiTypes() {
        return xueqiTypes;
    }


    /**
	 * 获取：学期
	 */

    public void setXueqiTypes(Integer xueqiTypes) {
        this.xueqiTypes = xueqiTypes;
    }
    /**
	 * 设置：课程
	 */
    public String getKecheng() {
        return kecheng;
    }


    /**
	 * 获取：课程
	 */

    public void setKecheng(String kecheng) {
        this.kecheng = kecheng;
    }
    /**
	 * 设置：排序字段
	 */
    public Integer getPaixuNumber() {
        return paixuNumber;
    }


    /**
	 * 获取：排序字段
	 */

    public void setPaixuNumber(Integer paixuNumber) {
        this.paixuNumber = paixuNumber;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
