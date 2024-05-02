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
 * 课程
 *
 * @author 
 * @email
 */
@TableName("kecheng")
public class KechengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KechengEntity() {

	}

	public KechengEntity(T t) {
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
     * 老师
     */
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 老师名字
     */
    @TableField(value = "temp_laoshi_name")

    private String tempLaoshiName;


    /**
     * 课程名字
     */
    @TableField(value = "kecheng_name")

    private String kechengName;


    /**
     * 课程地点
     */
    @TableField(value = "kecheng_address")

    private String kechengAddress;


    /**
     * 课程形式
     */
    @TableField(value = "kecheng_types")

    private Integer kechengTypes;


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
	 * 设置：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 获取：老师
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 设置：老师名字
	 */
    public String getTempLaoshiName() {
        return tempLaoshiName;
    }


    /**
	 * 获取：老师名字
	 */

    public void setTempLaoshiName(String tempLaoshiName) {
        this.tempLaoshiName = tempLaoshiName;
    }
    /**
	 * 设置：课程名字
	 */
    public String getKechengName() {
        return kechengName;
    }


    /**
	 * 获取：课程名字
	 */

    public void setKechengName(String kechengName) {
        this.kechengName = kechengName;
    }
    /**
	 * 设置：课程地点
	 */
    public String getKechengAddress() {
        return kechengAddress;
    }


    /**
	 * 获取：课程地点
	 */

    public void setKechengAddress(String kechengAddress) {
        this.kechengAddress = kechengAddress;
    }
    /**
	 * 设置：课程形式
	 */
    public Integer getKechengTypes() {
        return kechengTypes;
    }


    /**
	 * 获取：课程形式
	 */

    public void setKechengTypes(Integer kechengTypes) {
        this.kechengTypes = kechengTypes;
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
        return "Kecheng{" +
            "id=" + id +
            ", laoshiId=" + laoshiId +
            ", tempLaoshiName=" + tempLaoshiName +
            ", kechengName=" + kechengName +
            ", kechengAddress=" + kechengAddress +
            ", kechengTypes=" + kechengTypes +
            ", paixuNumber=" + paixuNumber +
            ", createTime=" + createTime +
        "}";
    }
}
