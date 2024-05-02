package com.entity.model;

import com.entity.KebiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 课表
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class KebiaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 节数
     */
    private Integer sectionsTypes;


    /**
     * 班级
     */
    private Integer clazzId;


    /**
     * 学期
     */
    private Integer xueqiTypes;


    /**
     * 课程
     */
    private String kecheng;


    /**
     * 排序字段
     */
    private Integer paixuNumber;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：节数
	 */
    public Integer getSectionsTypes() {
        return sectionsTypes;
    }


    /**
	 * 设置：节数
	 */
    public void setSectionsTypes(Integer sectionsTypes) {
        this.sectionsTypes = sectionsTypes;
    }
    /**
	 * 获取：班级
	 */
    public Integer getClazzId() {
        return clazzId;
    }


    /**
	 * 设置：班级
	 */
    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }
    /**
	 * 获取：学期
	 */
    public Integer getXueqiTypes() {
        return xueqiTypes;
    }


    /**
	 * 设置：学期
	 */
    public void setXueqiTypes(Integer xueqiTypes) {
        this.xueqiTypes = xueqiTypes;
    }
    /**
	 * 获取：课程
	 */
    public String getKecheng() {
        return kecheng;
    }


    /**
	 * 设置：课程
	 */
    public void setKecheng(String kecheng) {
        this.kecheng = kecheng;
    }
    /**
	 * 获取：排序字段
	 */
    public Integer getPaixuNumber() {
        return paixuNumber;
    }


    /**
	 * 设置：排序字段
	 */
    public void setPaixuNumber(Integer paixuNumber) {
        this.paixuNumber = paixuNumber;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
