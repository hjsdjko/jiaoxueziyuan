package com.entity.vo;

import com.entity.KechengEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 课程
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("kecheng")
public class KechengVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 教师
     */

    @TableField(value = "laoshi_id")
    private Integer laoshiId;


    /**
     * 教师名字
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
	 * 设置：教师名字
	 */
    public String getTempLaoshiName() {
        return tempLaoshiName;
    }


    /**
	 * 获取：教师名字
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

}
