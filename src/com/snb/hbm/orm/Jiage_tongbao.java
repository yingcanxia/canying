package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Jiage_tongbao {

	private int jiage_tongbao_id;//�۸�ͨ����id
	private int jiage_tongbao_yuefen;//ͨ���·�
	private String jiage_tongbao_timu;//ͨ������Ŀ
	private String jiage_tongbao_neirong_wenjian_lianjie;//ͨ��ͼƬ������
	private int fk_jiage_tongbao_user_id;//ͨ���˵�id
	private Timestamp jiage_tongbao_chuanjian_shijian;//ͨ������ʱ��
	private int jiage_tongbao_biaozhiwei;//ͨ����־λ
	private Timestamp jiage_tongbao_gengxin_shijian;//ͨ������ʱ��
	private int browse_count;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getJiage_tongbao_id() {
		return jiage_tongbao_id;
	}
	public void setJiage_tongbao_id(int jiage_tongbao_id) {
		this.jiage_tongbao_id = jiage_tongbao_id;
	}
	public int getJiage_tongbao_yuefen() {
		return jiage_tongbao_yuefen;
	}
	public void setJiage_tongbao_yuefen(int jiage_tongbao_yuefen) {
		this.jiage_tongbao_yuefen = jiage_tongbao_yuefen;
	}
	public String getJiage_tongbao_timu() {
		return jiage_tongbao_timu;
	}
	public void setJiage_tongbao_timu(String jiage_tongbao_timu) {
		this.jiage_tongbao_timu = jiage_tongbao_timu;
	}
	
	public String getJiage_tongbao_neirong_wenjian_lianjie() {
		return jiage_tongbao_neirong_wenjian_lianjie;
	}
	public void setJiage_tongbao_neirong_wenjian_lianjie(
			String jiage_tongbao_neirong_wenjian_lianjie) {
		this.jiage_tongbao_neirong_wenjian_lianjie = jiage_tongbao_neirong_wenjian_lianjie;
	}
	public int getFk_jiage_tongbao_user_id() {
		return fk_jiage_tongbao_user_id;
	}
	public void setFk_jiage_tongbao_user_id(int fk_jiage_tongbao_user_id) {
		this.fk_jiage_tongbao_user_id = fk_jiage_tongbao_user_id;
	}
	public Timestamp getJiage_tongbao_chuanjian_shijian() {
		return jiage_tongbao_chuanjian_shijian;
	}
	public void setJiage_tongbao_chuanjian_shijian(
			Timestamp jiage_tongbao_chuanjian_shijian) {
		this.jiage_tongbao_chuanjian_shijian = jiage_tongbao_chuanjian_shijian;
	}
	public int getJiage_tongbao_biaozhiwei() {
		return jiage_tongbao_biaozhiwei;
	}
	public void setJiage_tongbao_biaozhiwei(int jiage_tongbao_biaozhiwei) {
		this.jiage_tongbao_biaozhiwei = jiage_tongbao_biaozhiwei;
	}
	public Timestamp getJiage_tongbao_gengxin_shijian() {
		return jiage_tongbao_gengxin_shijian;
	}
	public void setJiage_tongbao_gengxin_shijian(Timestamp jiage_tongbao_gengxin_shijian) {
		this.jiage_tongbao_gengxin_shijian = jiage_tongbao_gengxin_shijian;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
	
}
