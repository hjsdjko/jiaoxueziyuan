package com.entity.model;

import com.entity.KechengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 课程
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class KechengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 教师
     */
    private Integer laoshiId;


    /**
     * 教师名字
     */
    private String tempLaoshiName;


    /**
     * 课程名字
     */
    private String kechengName;


    /**
     * 课程地点
     */
    private String kechengAddress;


    /**
     * 课程形式
     */
    private Integer kechengTypes;


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
	 * 获取：教师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 设置：教师
	 */
    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 获取：教师名字
	 */
    public String getTempLaoshiName() {
        return tempLaoshiName;
    }


    /**
	 * 设置：教师名字
	 */
    public void setTempLaoshiName(String tempLaoshiName) {
        this.tempLaoshiName = tempLaoshiName;
    }
    /**
	 * 获取：课程名字
	 */
    public String getKechengName() {
        return kechengName;
    }


    /**
	 * 设置：课程名字
	 */
    public void setKechengName(String kechengName) {
        this.kechengName = kechengName;
    }
    /**
	 * 获取：课程地点
	 */
    public String getKechengAddress() {
        return kechengAddress;
    }


    /**
	 * 设置：课程地点
	 */
    public void setKechengAddress(String kechengAddress) {
        this.kechengAddress = kechengAddress;
    }
    /**
	 * 获取：课程形式
	 */
    public Integer getKechengTypes() {
        return kechengTypes;
    }


    /**
	 * 设置：课程形式
	 */
    public void setKechengTypes(Integer kechengTypes) {
        this.kechengTypes = kechengTypes;
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
