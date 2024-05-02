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
 * 校长
 *
 * @author 
 * @email
 */
@TableName("xiaozhang")
public class XiaozhangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XiaozhangEntity() {

	}

	public XiaozhangEntity(T t) {
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
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 校长姓名
     */
    @TableField(value = "xiaozhang_name")

    private String xiaozhangName;


    /**
     * 校长手机号
     */
    @TableField(value = "xiaozhang_phone")

    private String xiaozhangPhone;


    /**
     * 校长身份证号
     */
    @TableField(value = "xiaozhang_id_number")

    private String xiaozhangIdNumber;


    /**
     * 校长头像
     */
    @TableField(value = "xiaozhang_photo")

    private String xiaozhangPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    @TableField(value = "xiaozhang_email")

    private String xiaozhangEmail;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：校长姓名
	 */
    public String getXiaozhangName() {
        return xiaozhangName;
    }


    /**
	 * 获取：校长姓名
	 */

    public void setXiaozhangName(String xiaozhangName) {
        this.xiaozhangName = xiaozhangName;
    }
    /**
	 * 设置：校长手机号
	 */
    public String getXiaozhangPhone() {
        return xiaozhangPhone;
    }


    /**
	 * 获取：校长手机号
	 */

    public void setXiaozhangPhone(String xiaozhangPhone) {
        this.xiaozhangPhone = xiaozhangPhone;
    }
    /**
	 * 设置：校长身份证号
	 */
    public String getXiaozhangIdNumber() {
        return xiaozhangIdNumber;
    }


    /**
	 * 获取：校长身份证号
	 */

    public void setXiaozhangIdNumber(String xiaozhangIdNumber) {
        this.xiaozhangIdNumber = xiaozhangIdNumber;
    }
    /**
	 * 设置：校长头像
	 */
    public String getXiaozhangPhoto() {
        return xiaozhangPhoto;
    }


    /**
	 * 获取：校长头像
	 */

    public void setXiaozhangPhoto(String xiaozhangPhoto) {
        this.xiaozhangPhoto = xiaozhangPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getXiaozhangEmail() {
        return xiaozhangEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setXiaozhangEmail(String xiaozhangEmail) {
        this.xiaozhangEmail = xiaozhangEmail;
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
        return "Xiaozhang{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", xiaozhangName=" + xiaozhangName +
            ", xiaozhangPhone=" + xiaozhangPhone +
            ", xiaozhangIdNumber=" + xiaozhangIdNumber +
            ", xiaozhangPhoto=" + xiaozhangPhoto +
            ", sexTypes=" + sexTypes +
            ", xiaozhangEmail=" + xiaozhangEmail +
            ", createTime=" + createTime +
        "}";
    }
}
