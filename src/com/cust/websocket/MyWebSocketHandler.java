package com.cust.websocket;


import com.cust.easyutil.Compress;
import com.cust.easyutil.UUIDTool;
import com.cust.services.FEDService;
import com.cust.util.PageData;
import com.google.gson.GsonBuilder;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import net.sf.json.JSONObject;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class MyWebSocketHandler implements WebSocketHandler{

	@Resource(name="FEDS")
    private FEDService feds;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final Map<String, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<String, WebSocketSession>();
    }

	//连接关闭
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//握手实现连接后
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	//发送信息前的处理
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
		// TODO Auto-generated method stub
		String message=webSocketMessage.getPayload().toString();
		String biaoshi;
		String message2;
		if(message.equals("R_{\"deviceid\":null}")){
			return;
		}
		try {
			biaoshi=message.split("_")[0];
			message2=message.split("_")[1];
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(message2);
		String deviceid=(String) jsonObject.get("deviceid");
		
		if(biaoshi.equals("R")){
			
	            userSocketSessionMap.put(deviceid, webSocketSession);
	        
		}
		else if(biaoshi.equals("A")){
			/*报警*/
			
			Map map=new HashMap();
			String accidentid=(String) jsonObject.get("accidentid");
			JSONObject json2=jsonObject.getJSONObject("message");
			String accident_name=(String) json2.get("accidentname");
			Double lat=(Double) json2.get("lat");
			Double lon=(Double) json2.get("lon");
			int role=(Integer) json2.get("role");
			PageData pd=new PageData();
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			pd.put("id", UUIDTool.getUUID());
			pd.put("name", accident_name+"_"+deviceid+"_"+d);
			pd.put("record_GPS_lat", lat);
			pd.put("record_GPS_lon", lon);
			pd.put("role", role);
			pd.put("fk_user_id", deviceid);
			pd.put("time", d);
			feds.excutAddAccident_log(pd);
			jsonObject.put("date", DATE_FORMAT.format(new Date()));
			Iterator iter = userSocketSessionMap.entrySet().iterator();
				while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				WebSocketSession val = (WebSocketSession) entry.getValue();
				sendMessage(val,new TextMessage(message));
				}
		}else if(biaoshi.equals("T")){
			/*消息*/
			String myid=(String) jsonObject.get("deviceid");//发送者id
			String targetid=(String) jsonObject.get("targetid");//接收者id
			String msg=(String) jsonObject.get("text");//消息体
			int type=(Integer) jsonObject.get("type");
			PageData pd=new PageData();
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			pd.put("id", UUIDTool.getUUID());
			pd.put("time", d);
			pd.put("sender",myid);
			pd.put("receiver",targetid);
			pd.put("text_body",msg);
			pd.put("type", type);
			feds.excutAddMessage_log(pd);
			jsonObject.put("date", DATE_FORMAT.format(new Date()));
			/*获取两个用户链接的websocket会话*/
			WebSocketSession val2=userSocketSessionMap.get(targetid);
			sendMessage(val2,new TextMessage(message));
		}
		else if(biaoshi.equals("V")){
			String myid=(String) jsonObject.get("deviceid");//发送者id
			String targetid=(String) jsonObject.get("targetId");//接收者id
			String voice=(String) jsonObject.get("voice");//声音体
			int type=(Integer) jsonObject.get("type");
			byte[] x=Compress.decodeBase64(voice);//解码
			x=Compress.uncompress(x);//解压
			PageData pd=new PageData();
			FileOutputStream fos = null;
			BufferedOutputStream bos=null;
			File file = null;
			File targetFile=null;
			String path=null;
			String path2=null;
			try {  
				File dir = new File("yinpin");  
				if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
					dir.mkdirs();  
				}  
				path=myid+".amr";
				file = new File("C:\\apache-tomcat-7.0.70\\webapps\\FireAlum\\yinpin",path);
				path2=UUIDTool.getUUID()+".wav";
				targetFile=new File("C:\\apache-tomcat-7.0.70\\webapps\\FireAlum\\yinpin",path2);
				fos = new FileOutputStream(file);  
				fos.write(x);
				AudioAttributes audio = new AudioAttributes();
				Encoder encoder = new Encoder();
				audio.setCodec("pcm_s16le");
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat("wav");
				attrs.setAudioAttributes(audio);
				encoder.encode(file, targetFile, attrs);
				} catch (Exception e) {  
					e.printStackTrace();  
				} finally {  
					if (fos != null) {  
						try {  
							fos.close();  
						} catch (IOException e1) {  
							e1.printStackTrace();  
						}  
					}  
				}  
			file.delete();
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			pd.put("id", UUIDTool.getUUID());
			pd.put("time", d);
			pd.put("sender",myid);
			pd.put("receiver",targetid);
			pd.put("store_path","yinpin/"+path2);
			pd.put("type", type);
			feds.excutAddMessage_log(pd);
			jsonObject.put("path","yinpin/"+path2);
			String voiceMessage="V_"+jsonObject.toString();
			WebSocketSession val2=userSocketSessionMap.get(targetid);
			sendMessage(val2,new TextMessage(voiceMessage));
		}
		System.out.println(message);
	}
	public void sendMessage(WebSocketSession webSocketSession,TextMessage message)throws IOException {
       /* WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }*/
		 if (webSocketSession != null && webSocketSession.isOpen()) {
			 webSocketSession.sendMessage(message);
	        }
    }
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

    
    
}
