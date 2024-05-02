package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 课件
 *
 * @author 
 * @email
 */
@TableName("kejian")
public class KejianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KejianEntity() {

	}

	public KejianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 教师
     */
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 课件名称
     */
    @TableField(value = "kejian_name")

    private String kejianName;


    /**
     * 课件照片
     */
    @TableField(value = "kejian_photo")

    private String kejianPhoto;


    /**
     * 课件文件
     */
    @TableField(value = "kejian_file")

    private String kejianFile;


    /**
     * 课件类型
     */
    @TableField(value = "kejian_types")

    private Integer kejianTypes;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "kejian_delete")

    private Integer kejianDelete;


    /**
     * 课件介绍
     */
    @TableField(value = "kejian_content")

    private String kejianContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：教师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 获取：教师
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 设置：课件名称
	 */
    public String getKejianName() {
        return kejianName;
    }


    /**
	 * 获取：课件名称
	 */

    public void setKejianName(String kejianName) {
        this.kejianName = kejianName;
    }
    /**
	 * 设置：课件照片
	 */
    public String getKejianPhoto() {
        return kejianPhoto;
    }


    /**
	 * 获取：课件照片
	 */

    public void setKejianPhoto(String kejianPhoto) {
        this.kejianPhoto = kejianPhoto;
    }
    /**
	 * 设置：课件文件
	 */
    public String getKejianFile() {
        return kejianFile;
    }


    /**
	 * 获取：课件文件
	 */

    public void setKejianFile(String kejianFile) {
        this.kejianFile = kejianFile;
    }
    /**
	 * 设置：课件类型
	 */
    public Integer getKejianTypes() {
        return kejianTypes;
    }


    /**
	 * 获取：课件类型
	 */

    public void setKejianTypes(Integer kejianTypes) {
        this.kejianTypes = kejianTypes;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getKejianDelete() {
        return kejianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setKejianDelete(Integer kejianDelete) {
        this.kejianDelete = kejianDelete;
    }
    /**
	 * 设置：课件介绍
	 */
    public String getKejianContent() {
        return kejianContent;
    }


    /**
	 * 获取：课件介绍
	 */

    public void setKejianContent(String kejianContent) {
        this.kejianContent = kejianContent;
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

    @Override
    public String toString() {
        return "Kejian{" +
            "id=" + id +
            ", laoshiId=" + laoshiId +
            ", kejianName=" + kejianName +
            ", kejianPhoto=" + kejianPhoto +
            ", kejianFile=" + kejianFile +
            ", kejianTypes=" + kejianTypes +
            ", shangxiaTypes=" + shangxiaTypes +
            ", kejianDelete=" + kejianDelete +
            ", kejianContent=" + kejianContent +
            ", createTime=" + createTime +
        "}";
    }
}
