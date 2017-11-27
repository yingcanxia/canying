package com.snb.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snb.hbm.dao.BaseHibernateDAO;
import com.snb.hbm.orm.Cuncuntong_cun;
import com.snb.hbm.orm.Cuncuntong_zhen;
import com.snb.hbm.orm.Fabu_nongmin_suqiu;
import com.snb.hbm.orm.Gongqiu_xinxi;
import com.snb.hbm.orm.Huangye_dianjia;
import com.snb.hbm.orm.Huifu_nongmin_suqiu;
import com.snb.hbm.orm.Jiage_tongbao;
import com.snb.hbm.orm.Keshi;
import com.snb.hbm.orm.Nongmin_suqiu;
import com.snb.hbm.orm.Nongshi_tixing;
import com.snb.hbm.orm.Nongyejishu;
import com.snb.hbm.orm.Nongzi_caigou;
import com.snb.hbm.orm.Rexian;
import com.snb.hbm.orm.Rexian_nongji;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Scroll_img;
import com.snb.hbm.orm.Shuoming_wendang;
import com.snb.hbm.orm.Users;
import com.snb.hbm.orm.Xingzheng_yuyue;
import com.snb.hbm.orm.Xinwen;
import com.snb.hbm.orm.YiYuan;
import com.snb.hbm.orm.Zhanxiao_xinxi;
import com.snb.hbm.orm.Zhengce_fagui;
import com.snb.hbm.orm.ZhuanJia;
import com.snb.services.AbstractService;
import com.snb.services.IService;

public class SnbService extends AbstractService implements IService{
	private BaseHibernateDAO dao;
	@Override
	public Map query(Map map) throws Exception {
		// TODO Auto-generated method stub
		String name=(String)map.get("username");
		Integer _user_biaozhiwei=(Integer)map.get("_user_biaozhiwei");//���»��� ����  !=
		int user_id=0;
		if(map.get("user_id") != null){
			user_id=(Integer)map.get("user_id");
		}
		StringBuilder sql=new StringBuilder("from Users where 1=1 ");
		List paraList=new ArrayList();
		if(name!=null&&!name.equals("")){
			sql.append("and user_yonghuming_id = ?");
			paraList.add(name);
		}
		if(user_id!=0){
			sql.append("and user_id = ?");
			paraList.add(user_id);
		}
		if(_user_biaozhiwei!=null&&!_user_biaozhiwei.equals("")){
			sql.append("and user_biaozhiwei != ?");
			paraList.add(_user_biaozhiwei);
		}
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryTongji(Map map) throws Exception {
		// TODO Auto-generated method stub
		int name=(Integer)map.get("user_id");
		String sql="select *,count(*) FROM rizhi GROUP BY rizhi_duix_mingcheng,fk_user_id having fk_user_id ="+name;
		List list=dao.querySQL(sql, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryYiYuan(Map map) throws Exception {
		List paraList=new ArrayList();
		StringBuilder sql=new StringBuilder("from YiYuan where 1=1 and yiyuan_biaozhiwei=1");
		String mingcheng=(String)map.get("mingcheng");
		if(mingcheng!=null&&!"".equals(mingcheng)){
			sql.append(" and yiyuan_mingcheng like ?");
			paraList.add("%"+mingcheng+"%");
		}
		String yyid=(String)map.get("yyid");
		if(yyid!=null&&!"".equals(yyid)){
			int id=Integer.parseInt(yyid);
			sql.append(" and yiyuan_id=? ");
			paraList.add(id);
		}
		sql.append(" order by yiyuan_gengxin_shijian desc");
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryZhuanjia(Map map) throws Exception {
		List paraList=new ArrayList();
		StringBuilder sql=new StringBuilder("from ZhuanJia where 1=1 and zhuanjia_biaozhiwei=1");
		String keshi_id=(String)map.get("ksid");
		if(keshi_id!=null&&!"".equals(keshi_id)){
			sql.append(" and fk_keshi_id= ?");
			int ksid=Integer.parseInt(keshi_id);
			paraList.add(ksid);
		}
		sql.append(" order by zhuanjia_gengxin_shijian desc");
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryKeshi(Map map) throws Exception {
		List paraList=new ArrayList();
		StringBuilder sql=new StringBuilder("from Keshi where 1=1 and keshi_biaozhiwei=1");
		String mingcheng=(String)map.get("mingcheng");
		if(mingcheng!=null&&!"".equals(mingcheng)){
			sql.append(" and keshi_mingcheng like ?");
			paraList.add("%"+mingcheng+"%");
		}
		String yiyuan_id=(String)map.get("yyid");
		if(yiyuan_id!=null&&!"".equals(yiyuan_id)){
			sql.append(" and fk_yiyuan_id= ?");
			int yyid=Integer.parseInt(yiyuan_id);
			paraList.add(yyid);
		}
		String ksid=(String)map.get("ksid");
		if(ksid!=null&&!"".equals(ksid)){
			sql.append(" and keshi_id=? ");
			int id=Integer.parseInt(ksid);
			paraList.add(id);
		}
		sql.append(" order by keshi_gengxin_shijian desc");
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
		
	}
	public Map queryllTongji(Map map) throws Exception {
		// TODO Auto-generated method stub
		Map reMap=new HashMap();
		//int name=(Integer)map.get("user_id");
		String sqlnstx="SELECT SUM(browse_count) FROM Nongshi_tixing ";
		List listnstx=dao.querySQL(sqlnstx, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listnstx", listnstx);
		String sqlzcfg="SELECT SUM(browse_count) FROM Zhengce_fagui ";
		List listzcfg=dao.querySQL(sqlzcfg, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listzcfg", listzcfg);
		String sqljgtb="SELECT SUM(browse_count) FROM Jiage_tongbao ";
		List listjgtb=dao.querySQL(sqljgtb, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listjgtb", listjgtb);
		String sqlxw="SELECT SUM(browse_count) FROM Xinwen ";
		List listxw=dao.querySQL(sqlxw, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listxw", listxw);
		String sqlzhen="SELECT SUM(browse_count) FROM Cuncuntong_zhen ";
		List listzhen=dao.querySQL(sqlzhen, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listzhen", listzhen);
		String sqlcun="SELECT SUM(browse_count) FROM Cuncuntong_cun ";
		List listcun=dao.querySQL(sqlcun, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listcun", listcun);
		String sqlzxxx="SELECT SUM(browse_count) FROM Zhanxiao_xinxi ";
		List listzxxx=dao.querySQL(sqlzxxx, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listzxxx", listzxxx);
		String sqlnyjs="SELECT SUM(browse_count) FROM Nongyejishu ";
		List listnyjs=dao.querySQL(sqlnyjs, null);
		//List list=dao.query(sql.toString(), paraList.toArray());
		reMap.put("listnyjs", listnyjs);
		return reMap;
	}
	public Map query0(Map map,Map mapb) throws Exception {
		// TODO Auto-generated method stub
		String user_id=(String)map.get("user_id");
		Nongmin_suqiu quer=(Nongmin_suqiu)mapb.get("quer");
		StringBuilder sql=new StringBuilder("select a.*,b.huifu_nongmin_suqiu_neirong "+
		"from Nongmin_suqiu a LEFT OUTER JOIN huifu_nongmin_suqiu b ON "+ 
"a.nongmin_suqiu_id=b.fk_neirong_suqiu_yunashi_id "
		+"where a.fk_nongmin_suqiu_user_id="+user_id+" and a.nongmin_suqiu_biaozhiwei=1 ");
		List paraList=new ArrayList();
		if(quer.getNongmin_suqiu_timu()!=null&&!quer.getNongmin_suqiu_timu().equals("")){
//			sql.append(" and a.nongmin_suqiu_timu like ? ");
//			paraList.add("%"+quer.getNongmin_suqiu_timu()+"%");
			sql.append(" and a.nongmin_suqiu_timu like ");
			sql.append("'%"+quer.getNongmin_suqiu_timu()+"%'");
		}
		if(quer.getNongmin_suqiu_renyuan_xingming()!=null&&!quer.getNongmin_suqiu_renyuan_xingming().equals("")){
//			sql.append(" and a.nongmin_suqiu_renyuan_xingming = ? ");
//			paraList.add(quer.getNongmin_suqiu_renyuan_xingming());
			sql.append(" and a.nongmin_suqiu_renyuan_xingming = '");
			sql.append(quer.getNongmin_suqiu_renyuan_xingming());
			sql.append("'");
		}
		if(quer.getNongmin_suqiu_renyuan_dianhua()!=null&&!quer.getNongmin_suqiu_renyuan_dianhua().equals("")){
//			sql.append(" and a.nongmin_suqiu_renyuan_dianhua = ? ");
//			paraList.add(quer.getNongmin_suqiu_renyuan_dianhua());
			sql.append(" and a.nongmin_suqiu_renyuan_dianhua = ");
			sql.append(quer.getNongmin_suqiu_renyuan_dianhua());
		}
		if(quer.getState()>=0){
//			sql.append(" and a.state= ? ");
//			paraList.add(quer.getState());
			sql.append(" and a.state= ");
			sql.append(quer.getState());
		}
		sql.append(" ORDER BY a.nongmin_suqiu_gengxin_shijian DESC ");
		System.out.println("sql--"+sql);
//		List list=dao.querySQL(" select * from student ", null);
//		List list=dao.query(sql.toString(), paraList.toArray());
		List list=dao.querySQL(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map query1(Map map) throws Exception {
		// TODO Auto-generated method stub
		Nongmin_suqiu quer=(Nongmin_suqiu)map.get("quer");
		StringBuilder sql=new StringBuilder("select a.*,b.huifu_nongmin_suqiu_neirong "
				+"from Nongmin_suqiu a LEFT OUTER JOIN huifu_nongmin_suqiu b ON "
				+"a.nongmin_suqiu_id=b.fk_neirong_suqiu_yunashi_id "
				+"where a.nongmin_suqiu_biaozhiwei=1 ");
		List paraList=new ArrayList();
		if(quer.getNongmin_suqiu_timu()!=null&&!quer.getNongmin_suqiu_timu().equals("")){
//			sql.append(" and a.nongmin_suqiu_timu like ? ");
//			paraList.add("%"+quer.getNongmin_suqiu_timu()+"%");
			sql.append(" and a.nongmin_suqiu_timu like  ");
			sql.append("'%"+quer.getNongmin_suqiu_timu()+"%'");
		}
		if(quer.getNongmin_suqiu_renyuan_xingming()!=null&&!quer.getNongmin_suqiu_renyuan_xingming().equals("")){
//			sql.append(" and a.nongmin_suqiu_renyuan_xingming = ? ");
//			paraList.add(quer.getNongmin_suqiu_renyuan_xingming());
			sql.append(" and a.nongmin_suqiu_renyuan_xingming = '");
			sql.append(quer.getNongmin_suqiu_renyuan_xingming());
			sql.append("'");
		}
		if(quer.getNongmin_suqiu_renyuan_dianhua()!=null&&!quer.getNongmin_suqiu_renyuan_dianhua().equals("")){
//			sql.append(" and a.nongmin_suqiu_renyuan_dianhua = ? ");
//			paraList.add(quer.getNongmin_suqiu_renyuan_dianhua());
			sql.append(" and a.nongmin_suqiu_renyuan_dianhua = ");
			sql.append(quer.getNongmin_suqiu_renyuan_dianhua());
		}
		if(quer.getState()!=-1){
//			sql.append(" and a.state= ? ");
//			paraList.add(quer.getState());
			sql.append(" and a.state= ");
			sql.append(quer.getState());
		}
		sql.append(" ORDER BY a.nongmin_suqiu_gengxin_shijian DESC ");
		System.out.println("sql--"+sql);
//		List list=dao.querySQL(" select * from student ", null);
//		List list=dao.query(sql.toString(), paraList.toArray());
		List list=dao.querySQL(sql.toString(), null);
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryYy0(Map map,Map mapb) throws Exception {//���Ϊ��ʱ�Ĳ���ԤԼ�ķ���
		// TODO Auto-generated method stub
		String user_id=(String)map.get("user_id");
		String timu=(String)mapb.get("timu");
		StringBuilder sql=new StringBuilder("from Xingzheng_yuyue where fk_xingzheng_yuyue_user_id="+user_id+"and xingzheng_yuyue_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and xingzheng_yuyue_timu like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY xingzheng_yuyue_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryYy1(Map map) throws Exception {//���Ϊ1�ǵĲ�ѯԤԼ�ķ���
		// TODO Auto-generated method stub
		Xingzheng_yuyue query=(Xingzheng_yuyue)map.get("query");
		StringBuilder sql=new StringBuilder("from Xingzheng_yuyue where xingzheng_yuyue_biaozhiwei=1 ");
		List paraList=new ArrayList();
		if(query.getXingzheng_yuyue_timu()!=null&&!query.getXingzheng_yuyue_timu().equals("")){
			sql.append("and xingzheng_yuyue_timu like ?");
			paraList.add("%"+query.getXingzheng_yuyue_timu()+"%");
		}
		if(query.getXingzheng_yuyue_renyuan_dianhua()!=null&&!query.getXingzheng_yuyue_renyuan_dianhua().equals("")){
			sql.append("and xingzheng_yuyue_renyuan_dianhua = ?");
			paraList.add(query.getXingzheng_yuyue_renyuan_dianhua());
		}
		if(query.getXingzheng_yuyue_renyuan_xingming()!=null&&!query.getXingzheng_yuyue_renyuan_xingming().equals("")){
			sql.append("and xingzheng_yuyue_renyuan_xingming = ?");
			paraList.add(query.getXingzheng_yuyue_renyuan_xingming());
		}
		if(query.getXingzheng_yuyue_zhuangtai()>=0){
			sql.append("and xingzheng_yuyue_zhuangtai = ?");
			paraList.add(query.getXingzheng_yuyue_zhuangtai());
		}
		sql.append("ORDER BY xingzheng_yuyue_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryZx(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Zhanxiao_xinxi where zhanxiao_xinxi_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and zhanxiao_xinxi_timu like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY zhanxiao_xinxi_zhidingwei DESC,zhanxiao_xinxi_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryJg(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Jiage_tongbao where jiage_tongbao_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and jiage_tongbao_timu like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY jiage_tongbao_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryZc(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String guanjianzi=(String)map.get("guanjianzi");
		int xinxing = (Integer)map.get("xinxing");
		StringBuilder sql=new StringBuilder("from Zhengce_fagui where zhengce_biaozhiwei!=0");
		List paraList=new ArrayList();
		if(guanjianzi!=null&&!guanjianzi.equals("")){
			sql.append("and zhengce_guanjiazi like ? ");
			paraList.add("%"+guanjianzi+"%");
		}
		if(xinxing==2){
			sql.append("and zhengce_biaozhiwei =2  ");
			
		}
		sql.append("ORDER BY zhengce_zhidingwei DESC,zhengce_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryGq1(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String shangpin=(String)map.get("shangpin");
		StringBuilder sql=new StringBuilder("from Gongqiu_xinxi where gongqiu_xinxi_biaozhiwei=1");
		List paraList=new ArrayList();
		if(shangpin!=null&&!shangpin.equals("")){
			sql.append("and gongqiu_xinxi_shangpin like ? ");
			paraList.add("%"+shangpin+"%");
		}
		sql.append("ORDER BY gongqiu_xinxi_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryGq0(Map map,Map mbp) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String shangpin=(String)map.get("shangpin");
		String user_id=(String)mbp.get("user_dianhua");
		StringBuilder sql=new StringBuilder("from Gongqiu_xinxi where fk_gongqiu_xinxi_user_id ="+user_id+" and gongqiu_xinxi_biaozhiwei=1 ");
		List paraList=new ArrayList();
		if(shangpin!=null&&!shangpin.equals("")){
			sql.append("and gongqiu_xingxi_shanpin like ? ");
			paraList.add("%"+shangpin+"%");
		}
		sql.append("ORDER BY gongqiu_xinxi_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map query2(Map map) throws Exception {
		// TODO Auto-generated method stub
		String name=(String)map.get("user_yonghuming_id");
		StringBuilder sql=new StringBuilder("from Users where user_biaozhiwei!=0 and 1=1 ");
		List paraList=new ArrayList();
		if(name!=null&&!name.equals("")){
			sql.append("and user_yonghuming_id = ?");
			paraList.add(name);
		}
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryRx(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Rexian where rexian_biaozhiwei=1 ");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append(" and banshi_name like ? ");
			paraList.add("%"+timu+"%");
			sql.append(" or rexian_biaozhiwei=1 and zhuanjia_name like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append(" ORDER BY rexian_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		System.out.println("sql: "+sql);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryNz(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Nongzi_caigou where nongzi_caigou_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and nongzi_mingcheng like ?");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY nongzi_caigou_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryTx(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Nongshi_tixing where nongshi_tixing_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and nongshi_tixing_timu like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY nongshi_tixing_zhidingwei DESC,nongshi_tixing_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map querycun(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		int timu=(Integer)map.get("zhenid");
		StringBuilder sql=new StringBuilder("from Cuncuntong_cun where  cun_biaozhiwei=1 ");
		List paraList=new ArrayList();
		if(timu!=-1){
			sql.append("and fk_zhen_id = ? ");
			paraList.add(timu);
		}
		sql.append("ORDER BY cun_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryzhen(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Cuncuntong_zhen where zhen_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and zhen_mingcheng like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY zhen_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryjs(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		int timu=(Integer)map.get("timu");
		int xinxing=(Integer)map.get("xinxing");
		StringBuilder sql=new StringBuilder("from Nongyejishu where jishu_biaozhiwei!=0");
		List paraList=new ArrayList();
		if(timu>=0){
			sql.append("and jishu_type = ? ");
			paraList.add(timu);
		}
		if(xinxing==2){
			sql.append("and jishu_biaozhiwei = 2 ");
		}
		sql.append("ORDER BY jishu_zhidingwei DESC,gengxin_time DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryrn(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		int timu=(Integer)map.get("rexianid");
		StringBuilder sql=new StringBuilder("from Rexian_nongji where fk_rexian_id="+timu+" and biaozhiwei=1");
		List paraList=new ArrayList();
		
		
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryXw(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		int xinxing=(Integer)map.get("xinxing");
		
		StringBuilder sql=new StringBuilder("from Xinwen where xinwen_biaozhiwei!=0");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and xinwen_timu like ? ");
			paraList.add("%"+timu+"%");
		}
		//System.out.println("xinxing:"+xinxing);
		if(xinxing==2){
			sql.append("and xinwen_biaozhiwei = 2  ");
		}
		sql.append("ORDER BY xinwen_zhidingwei DESC,xinwen_gengxin_riqi DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryFb(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Fabu_nongmin_suqiu where fabu_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and fabu_nongmin_suqiu_timu like ? ");
			paraList.add("%"+timu+"%");
		}
		sql.append("ORDER BY fabu_nongmin_suqiu_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryHy(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		Huangye_dianjia quer=(Huangye_dianjia)map.get("quer");
		StringBuilder sql=new StringBuilder("from Huangye_dianjia where huangye_biaozhiwei=1");
		List paraList=new ArrayList();
		if(quer.getHuangye_dianjia_type()!=-1){
			sql.append("and huangye_dianjia_type = ? ");
			paraList.add(quer.getHuangye_dianjia_type());
		}
		if(quer.getHuangye_dianjia_mingcheng()!=null&&!quer.getHuangye_dianjia_mingcheng().equals("")){
			sql.append("and huangye_dianjia_mingcheng like ? ");
			paraList.add("%"+quer.getHuangye_dianjia_mingcheng()+"%");
		}
		if(quer.getHuangye_dianjia_suoshuzhen()!=null&&!quer.getHuangye_dianjia_suoshuzhen().equals("")&&!quer.getHuangye_dianjia_suoshuzhen().equals("ȫ��")){
			sql.append("and huangye_dianjia_suoshuzhen like ? ");
			paraList.add("%"+quer.getHuangye_dianjia_suoshuzhen()+"%");
		}
		sql.append("ORDER BY huangye_zhidingwei DESC,huangye_gengxin_shijian DESC ");
//		List list=dao.querySQL(" select * from student ", null);
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryYiyou(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		int timu=(Integer)map.get("timu");
		/*StringBuilder sql=new StringBuilder("from Huangye_dianjia where huangye_biaozhiwei=1");
		List paraList=new ArrayList();
		if(timu!=0){
			sql.append("and huangye_dianjia_type = ? ");
			paraList.add(timu);
		}*/
		List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
//		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryhf(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		int nongmin_suqiu_id=(Integer)map.get("nongmin_suqiu_id");
		StringBuilder sql=new StringBuilder("from Huifu_nongmin_suqiu where 1=1");
		List paraList=new ArrayList();
		if(nongmin_suqiu_id!=0){
			sql.append("and fk_neirong_suqiu_yunashi_id = ? ");
			paraList.add(nongmin_suqiu_id);
		}
		//List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map querySi(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		//int nongmin_suqiu_id=(Integer)map.get("nongmin_suqiu_id");
		StringBuilder sql=new StringBuilder("from Scroll_img where biaozhiwei=1");
		List paraList=new ArrayList();
		/*if(nongmin_suqiu_id!=0){
			sql.append("and fk_neirong_suqiu_yunashi_id = ? ");
			paraList.add(nongmin_suqiu_id);
		}*/
		//List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map querySm(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		//int nongmin_suqiu_id=(Integer)map.get("nongmin_suqiu_id");
		StringBuilder sql=new StringBuilder("from Shuoming_wendang where 1=1");
		List paraList=new ArrayList();
		/*if(nongmin_suqiu_id!=0){
			sql.append("and fk_neirong_suqiu_yunashi_id = ? ");
			paraList.add(nongmin_suqiu_id);
		}*/
		//List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryUs(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Users where user_biaozhiwei=1 and (user_leixing=1 or user_leixing=2) ");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and user_yonghuming_id like ? ");
			paraList.add("%"+timu+"%");
		}
		//List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map querymanager(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Users where user_leixing=2");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and user_yonghuming_id = ? ");
			paraList.add("%"+timu+"%");
		}
		//List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryuser0(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String timu=(String)map.get("timu");
		StringBuilder sql=new StringBuilder("from Users where user_leixing=0");
		List paraList=new ArrayList();
		if(timu!=null&&!timu.equals("")){
			sql.append("and user_dianhua = ? ");
			paraList.add(timu);
		}
		//List list=dao.querySQL("select * from rexian_nongji,nongyejishu where rexian_nongji.fk_nongji_id=nongyejishu.jishu_id and biaozhiwei=1 and fk_rexian_id ="+timu, null);
		
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map queryzongti(Map map) throws Exception {//���Ϊ��ʱ�Ĳ���չ���ķ���
		// TODO Auto-generated method stub
		String qishi=(String)map.get("qishi");
		String jieshu=(String)map.get("jieshu");
		//StringBuilder sql=new StringBuilder("from Users where user_leixing=0");
		//List paraList=new ArrayList();
		String sql="select duixiang_mingcheng ,COUNT(*)FROM (SELECT  duixiang_mingcheng,  liulan_shijian, COUNT(*)  FROM liulan_rizhi GROUP BY duixiang_mingcheng ,liulan_shijian ) x GROUP BY duixiang_mingcheng";
		if(!qishi.equals(" 00:00:00")&&!qishi.equals("null 00:00:00")&&!jieshu.equals(" 23:59:59")&&!jieshu.equals("null 23:59:59")){
			sql="select duixiang_mingcheng ,COUNT(*)FROM (SELECT  duixiang_mingcheng,  liulan_shijian, COUNT(*)  FROM liulan_rizhi GROUP BY duixiang_mingcheng ,liulan_shijian HAVING liulan_shijian BETWEEN '"+qishi+"'AND'"+jieshu+"' ) x GROUP BY duixiang_mingcheng";
			
		}
		List list=dao.querySQL(sql, null);
		
		//List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	public Map querydownload() throws Exception {
		StringBuilder sql=new StringBuilder("from Download where 1=1");
		List paraList=new ArrayList();
		List list=dao.query(sql.toString(), paraList.toArray());
		Map reMap=new HashMap();
		reMap.put("list", list);
		return reMap;
	}
	@Override
	public Map save(Map map) throws Exception {
		// TODO Auto-generated method stub
		return super.save(map);
	}
	public Map savecun(Map map) throws Exception {
		// TODO Auto-generated method stub
		Cuncuntong_cun cc=(Cuncuntong_cun)map.get("cun");
		dao.save(cc);
		return null;
	}
	public Map saveyiyuan(Map map) throws Exception {
		// TODO Auto-generated method stub
		YiYuan yiyuan=(YiYuan)map.get("yiyuan");
		dao.save(yiyuan);
		return null;
	}
	public Map savekeshi(Map map) throws Exception {
		// TODO Auto-generated method stub
		Keshi keshi=(Keshi)map.get("keshi");
		dao.save(keshi);
		return null;
	}
	public Map savezhen(Map map) throws Exception {
		// TODO Auto-generated method stub
		Cuncuntong_zhen zhen=(Cuncuntong_zhen)map.get("zhen");
		dao.save(zhen);
		return null;
	}
	public Map saveUsers(Map map) throws Exception {
		// TODO Auto-generated method stub
		Users us=(Users)map.get("Users");
		dao.save(us);
		return null;
	}
	public Map saveHuifu(Map map) throws Exception {
		// TODO Auto-generated method stub
		Huifu_nongmin_suqiu hf=(Huifu_nongmin_suqiu)map.get("hf");
		dao.saveOrUpdate(hf);
		return null;
	}
	public Map saveZx(Map map) throws Exception {
		// TODO Auto-generated method stub
		Zhanxiao_xinxi zx=(Zhanxiao_xinxi)map.get("zx");
		dao.save(zx);
		return null;
	}
	public Map saveZc(Map map) throws Exception {
		// TODO Auto-generated method stub
		Zhengce_fagui zc=(Zhengce_fagui)map.get("zc");
		dao.save(zc);
		return null;
	}
	public Map saveJg(Map map) throws Exception {
		// TODO Auto-generated method stub
		Jiage_tongbao jg=(Jiage_tongbao)map.get("jg");
		dao.save(jg);
		return null;
	}
	public Map saveRx(Map map) throws Exception {
		// TODO Auto-generated method stub
		Rexian rx=(Rexian)map.get("rx");
		dao.save(rx);
		return null;
	}
	public Map saveNz(Map map) throws Exception {
		// TODO Auto-generated method stub
		Nongzi_caigou nz=(Nongzi_caigou)map.get("nz");
		dao.save(nz);
		return null;
	}
	public Map saveJs(Map map) throws Exception {
		// TODO Auto-generated method stub
		Nongyejishu js=(Nongyejishu)map.get("js");
		dao.save(js);
		return null;
	}
	public Map saveTx(Map map) throws Exception {
		// TODO Auto-generated method stub
		Nongshi_tixing tx=(Nongshi_tixing)map.get("tx");
		dao.save(tx);
		return null;
	}
	public Map saveXw(Map map) throws Exception {
		// TODO Auto-generated method stub
		Xinwen xw=(Xinwen)map.get("xw");
		dao.save(xw);
		return null;
	}
	public Map saveFb(Map map) throws Exception {
		// TODO Auto-generated method stub
		Fabu_nongmin_suqiu xw=(Fabu_nongmin_suqiu)map.get("fb");
		dao.save(xw);
		return null;
	}
	public Map saveHy(Map map) throws Exception {
		// TODO Auto-generated method stub
		Huangye_dianjia hy=(Huangye_dianjia)map.get("hy");
		dao.save(hy);
		return null;
	}
	public Map saveRz(Map map) throws Exception {
		// TODO Auto-generated method stub
		Rizhi rz=(Rizhi)map.get("rz");
		dao.save(rz);
		return null;
	}
	public Map savezhuanjia(Map map) throws Exception {
		// TODO Auto-generated method stub
		ZhuanJia zhuanjia=(ZhuanJia)map.get("zhuanjia");
		dao.save(zhuanjia);
		return null;
	}
	public Map saveRn(Map map) throws Exception {
		// TODO Auto-generated method stub
		Rexian_nongji rz=(Rexian_nongji)map.get("rn");
		dao.saveOrUpdate(rz);
		return null;
	}
	public Map saveSi(Map map) throws Exception {
		// TODO Auto-generated method stub
		Scroll_img si=(Scroll_img)map.get("si");
		dao.saveOrUpdate(si);
		return null;
	}
	public Map saveSm(Map map) throws Exception {
		// TODO Auto-generated method stub
		Shuoming_wendang sm=(Shuoming_wendang)map.get("sm");
		dao.saveOrUpdate(sm);
		return null;
	}
	@Override
	public Map update(Map map) throws Exception {
		// TODO Auto-generated method 
		return super.update(map);
	}
	public Map updatesq(Map map) throws Exception {
		// ODO Auto-generated method stub
		Nongmin_suqiu sq=(Nongmin_suqiu)map.get("sq");
		dao.update(sq);
		return null;
	}
	public Map updatezx(Map map) throws Exception {
		// ODO Auto-generated method stub
		Zhanxiao_xinxi sq=(Zhanxiao_xinxi)map.get("zx");
		dao.update(sq);
		return null;
	}
	public Map updateyy(Map map) throws Exception {
		// ODO Auto-generated method stub
		Xingzheng_yuyue yy=(Xingzheng_yuyue)map.get("yy");
		dao.update(yy);
		return null;
	}
	public Map updategq(Map map) throws Exception {
		// ODO Auto-generated method stub
		Gongqiu_xinxi gq=(Gongqiu_xinxi)map.get("gq");
		dao.update(gq);
		return null;
	}
	public Map updatezc(Map map) throws Exception {
		// ODO Auto-generated method stub
		Zhengce_fagui zc=(Zhengce_fagui)map.get("zc");
		dao.update(zc);
		return null;
	}
	public Map updaterx(Map map) throws Exception {
		// ODO Auto-generated method stub
		Rexian rx=(Rexian)map.get("rx");
		dao.update(rx);
		return null;
	}
	public Map updatejg(Map map) throws Exception {
		// ODO Auto-generated method stub
		Jiage_tongbao jg=(Jiage_tongbao)map.get("jg");
		dao.update(jg);
		return null;
	}
	public Map updatenz(Map map) throws Exception {
		// ODO Auto-generated method stub
		Nongzi_caigou nz=(Nongzi_caigou)map.get("nz");
		dao.update(nz);
		return null;
	}
	public Map updatetx(Map map) throws Exception {
		// ODO Auto-generated method stub
		Nongshi_tixing tx=(Nongshi_tixing)map.get("tx");
		dao.update(tx);
		return null;
	}
	public Map updatexw(Map map) throws Exception {
		// ODO Auto-generated method stub
		Xinwen xw=(Xinwen)map.get("xw");
		dao.update(xw);
		return null;
	}
	public Map updatezhen(Map map) throws Exception {
		// ODO Auto-generated method stub
		Cuncuntong_zhen zhen=(Cuncuntong_zhen)map.get("zhen");
		dao.update(zhen);
		return null;
	}
	public Map updateyiyuan(Map map) throws Exception {
		YiYuan yiyuan=(YiYuan)map.get("yiyuan");
		dao.update(yiyuan);
		return null;
	}
	public Map updatezhuanjia(Map map) throws Exception {
		ZhuanJia zhuanjia=(ZhuanJia)map.get("zhuanjia");
		dao.update(zhuanjia);
		return null;
	}
	public Map updatekeshi(Map map) throws Exception {
		Keshi keshi=(Keshi)map.get("keshi");
		dao.update(keshi);
		return null;
	}
	public Map updatecun(Map map) throws Exception {
		// ODO Auto-generated method stub
		Cuncuntong_cun cun=(Cuncuntong_cun)map.get("cun");
		dao.update(cun);
		return null;
	}
	public Map updatesm(Map map) throws Exception {
		// ODO Auto-generated method stub
		Shuoming_wendang cun=(Shuoming_wendang)map.get("sm");
		dao.update(cun);
		return null;
	}
	public Map updatejs(Map map) throws Exception {
		// ODO Auto-generated method stub
		Nongyejishu js=(Nongyejishu)map.get("js");
		dao.update(js);
		return null;
	}
	public Map updatefb(Map map) throws Exception {
		// ODO Auto-generated method stub
		Fabu_nongmin_suqiu fb=(Fabu_nongmin_suqiu)map.get("fb");
		dao.update(fb);
		return null;
	}
	public Map updatehy(Map map) throws Exception {
		// ODO Auto-generated method stub
		Huangye_dianjia hy=(Huangye_dianjia)map.get("hy");
		dao.update(hy);
		return null;
	}
	public Map updateus(Map map) throws Exception {
		// ODO Auto-generated method stub
		Users us=(Users)map.get("us");
		dao.update(us);
		return null;
	}
	public Map updatern(Map map) throws Exception {
		// ODO Auto-generated method stub
		Rexian_nongji us=(Rexian_nongji)map.get("rn");
		dao.update(us);
		return null;
	}
	@Override
	public Map delete(Map map) throws Exception {
		// TODO Auto-generated method stub
		return super.delete(map);
	}
	public Map deleteus(Map map) throws Exception {
		// TODO Auto-generated method stub
		Users us=(Users)map.get("us");
		dao.delete(us);
		return super.delete(map);
	}
	public void setDao(BaseHibernateDAO dao) {
		this.dao = dao;
	}

	
}
