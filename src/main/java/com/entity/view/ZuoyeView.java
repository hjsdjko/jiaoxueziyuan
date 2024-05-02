package com.entity.view;

import com.entity.ZuoyeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 作业
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zuoye")
public class ZuoyeView extends ZuoyeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 作业类型的值
		*/
		private String zuoyeValue;



		//级联表 clazz
			/**
			* 班级名称
			*/
			private String clazzName;
			/**
			* 班级位置
			*/
			private String clazzAddress;

		//级联表 laoshi
			/**
			* 教师姓名
			*/
			private String laoshiName;
			/**
			* 教师手机号
			*/
			private String laoshiPhone;
			/**
			* 教师身份证号
			*/
			private String laoshiIdNumber;
			/**
			* 教师头像
			*/
			private String laoshiPhoto;
			/**
			* 电子邮箱
			*/
			private String laoshiEmail;

	public ZuoyeView() {

	}

	public ZuoyeView(ZuoyeEntity zuoyeEntity) {
		try {
			BeanUtils.copyProperties(this, zuoyeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 作业类型的值
			*/
			public String getZuoyeValue() {
				return zuoyeValue;
			}
			/**
			* 设置： 作业类型的值
			*/
			public void setZuoyeValue(String zuoyeValue) {
				this.zuoyeValue = zuoyeValue;
			}





				//级联表的get和set clazz
					/**
					* 获取： 班级名称
					*/
					public String getClazzName() {
						return clazzName;
					}
					/**
					* 设置： 班级名称
					*/
					public void setClazzName(String clazzName) {
						this.clazzName = clazzName;
					}
					/**
					* 获取： 班级位置
					*/
					public String getClazzAddress() {
						return clazzAddress;
					}
					/**
					* 设置： 班级位置
					*/
					public void setClazzAddress(String clazzAddress) {
						this.clazzAddress = clazzAddress;
					}























				//级联表的get和set laoshi
					/**
					* 获取： 教师姓名
					*/
					public String getLaoshiName() {
						return laoshiName;
					}
					/**
					* 设置： 教师姓名
					*/
					public void setLaoshiName(String laoshiName) {
						this.laoshiName = laoshiName;
					}
					/**
					* 获取： 教师手机号
					*/
					public String getLaoshiPhone() {
						return laoshiPhone;
					}
					/**
					* 设置： 教师手机号
					*/
					public void setLaoshiPhone(String laoshiPhone) {
						this.laoshiPhone = laoshiPhone;
					}
					/**
					* 获取： 教师身份证号
					*/
					public String getLaoshiIdNumber() {
						return laoshiIdNumber;
					}
					/**
					* 设置： 教师身份证号
					*/
					public void setLaoshiIdNumber(String laoshiIdNumber) {
						this.laoshiIdNumber = laoshiIdNumber;
					}
					/**
					* 获取： 教师头像
					*/
					public String getLaoshiPhoto() {
						return laoshiPhoto;
					}
					/**
					* 设置： 教师头像
					*/
					public void setLaoshiPhoto(String laoshiPhoto) {
						this.laoshiPhoto = laoshiPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getLaoshiEmail() {
						return laoshiEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setLaoshiEmail(String laoshiEmail) {
						this.laoshiEmail = laoshiEmail;
					}
















}
