package com.entity.model;

import com.entity.XiaozhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 校长
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XiaozhangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 校长姓名
     */
    private String xiaozhangName;


    /**
     * 校长手机号
     */
    private String xiaozhangPhone;


    /**
     * 校长身份证号
     */
    private String xiaozhangIdNumber;


    /**
     * 校长头像
     */
    private String xiaozhangPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String xiaozhangEmail;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：校长姓名
	 */
    public String getXiaozhangName() {
        return xiaozhangName;
    }


    /**
	 * 设置：校长姓名
	 */
    public void setXiaozhangName(String xiaozhangName) {
        this.xiaozhangName = xiaozhangName;
    }
    /**
	 * 获取：校长手机号
	 */
    public String getXiaozhangPhone() {
        return xiaozhangPhone;
    }


    /**
	 * 设置：校长手机号
	 */
    public void setXiaozhangPhone(String xiaozhangPhone) {
        this.xiaozhangPhone = xiaozhangPhone;
    }
    /**
	 * 获取：校长身份证号
	 */
    public String getXiaozhangIdNumber() {
        return xiaozhangIdNumber;
    }


    /**
	 * 设置：校长身份证号
	 */
    public void setXiaozhangIdNumber(String xiaozhangIdNumber) {
        this.xiaozhangIdNumber = xiaozhangIdNumber;
    }
    /**
	 * 获取：校长头像
	 */
    public String getXiaozhangPhoto() {
        return xiaozhangPhoto;
    }


    /**
	 * 设置：校长头像
	 */
    public void setXiaozhangPhoto(String xiaozhangPhoto) {
        this.xiaozhangPhoto = xiaozhangPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getXiaozhangEmail() {
        return xiaozhangEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setXiaozhangEmail(String xiaozhangEmail) {
        this.xiaozhangEmail = xiaozhangEmail;
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
