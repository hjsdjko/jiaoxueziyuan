package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 课表
 *
 * @author 
 * @email
 */
@TableName("kebiao")
public class KebiaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KebiaoEntity() {

	}

	public KebiaoEntity(T t) {
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

    @Override
    public String toString() {
        return "Kebiao{" +
            "id=" + id +
            ", sectionsTypes=" + sectionsTypes +
            ", clazzId=" + clazzId +
            ", xueqiTypes=" + xueqiTypes +
            ", kecheng=" + kecheng +
            ", paixuNumber=" + paixuNumber +
            ", createTime=" + createTime +
        "}";
    }
}
