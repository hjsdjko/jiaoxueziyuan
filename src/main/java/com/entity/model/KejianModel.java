package com.entity.model;

import com.entity.KejianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 课件
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class KejianModel implements Serializable {
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
     * 课件名称
     */
    private String kejianName;


    /**
     * 课件照片
     */
    private String kejianPhoto;


    /**
     * 课件文件
     */
    private String kejianFile;


    /**
     * 课件类型
     */
    private Integer kejianTypes;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer kejianDelete;


    /**
     * 课件介绍
     */
    private String kejianContent;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 获取：课件名称
	 */
    public String getKejianName() {
        return kejianName;
    }


    /**
	 * 设置：课件名称
	 */
    public void setKejianName(String kejianName) {
        this.kejianName = kejianName;
    }
    /**
	 * 获取：课件照片
	 */
    public String getKejianPhoto() {
        return kejianPhoto;
    }


    /**
	 * 设置：课件照片
	 */
    public void setKejianPhoto(String kejianPhoto) {
        this.kejianPhoto = kejianPhoto;
    }
    /**
	 * 获取：课件文件
	 */
    public String getKejianFile() {
        return kejianFile;
    }


    /**
	 * 设置：课件文件
	 */
    public void setKejianFile(String kejianFile) {
        this.kejianFile = kejianFile;
    }
    /**
	 * 获取：课件类型
	 */
    public Integer getKejianTypes() {
        return kejianTypes;
    }


    /**
	 * 设置：课件类型
	 */
    public void setKejianTypes(Integer kejianTypes) {
        this.kejianTypes = kejianTypes;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getKejianDelete() {
        return kejianDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setKejianDelete(Integer kejianDelete) {
        this.kejianDelete = kejianDelete;
    }
    /**
	 * 获取：课件介绍
	 */
    public String getKejianContent() {
        return kejianContent;
    }


    /**
	 * 设置：课件介绍
	 */
    public void setKejianContent(String kejianContent) {
        this.kejianContent = kejianContent;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
