package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.KebiaoEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 课表
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("kebiao")
public class KebiaoView extends KebiaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 节数的值
		*/
		private String sectionsValue;
		/**
		* 学期的值
		*/
		private String xueqiValue;



		//级联表 clazz
			/**
			* 班级名称
			*/
			private String clazzName;
			/**
			* 班级位置
			*/
			private String clazzAddress;


		/**
		 *星期一
		 */
		private String yi;

		/**
		 *星期二
		 */
		private String er;


		/**
		 *星期三
		 */
		private String san;

		/**
		 *星期四
		 */
		private String si;

		/**
		 *星期五
		 */
		private String wu;
	public KebiaoView() {

	}

	public KebiaoView(KebiaoEntity kebiaoEntity) {
		try {
			BeanUtils.copyProperties(this, kebiaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getYi() {
		return yi;
	}

	public void setYi(String yi) {
		this.yi = yi;
	}

	public String getEr() {
		return er;
	}

	public void setEr(String er) {
		this.er = er;
	}

	public String getSan() {
		return san;
	}

	public void setSan(String san) {
		this.san = san;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getWu() {
		return wu;
	}

	public void setWu(String wu) {
		this.wu = wu;
	}

	/**
			* 获取： 节数的值
			*/
			public String getSectionsValue() {
				return sectionsValue;
			}
			/**
			* 设置： 节数的值
			*/
			public void setSectionsValue(String sectionsValue) {
				this.sectionsValue = sectionsValue;
			}
			/**
			* 获取： 学期的值
			*/
			public String getXueqiValue() {
				return xueqiValue;
			}
			/**
			* 设置： 学期的值
			*/
			public void setXueqiValue(String xueqiValue) {
				this.xueqiValue = xueqiValue;
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






















}
