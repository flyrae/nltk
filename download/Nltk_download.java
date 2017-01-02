import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
  
import org.w3c.dom.Document; 
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList; 
  
public class Nltk_download
{ 
  public static void main(String[] args) throws Exception 
  { 
	  
	  File file =new File("/home/why/tmp/download_nltk_all.sh");

      //if file doesnt exists, then create it
      if(!file.exists()){
       file.createNewFile();
      }

      //true = append file
      FileWriter fileWritter = new FileWriter(file);
      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
             
    // step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器） 
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
      
//   System.out.println("class name: " + dbf.getClass().getName()); 
      
    // step 2:获得具体的dom解析器 
    DocumentBuilder db = dbf.newDocumentBuilder(); 
      
//   System.out.println("class name: " + db.getClass().getName()); 
      
    // step3: 解析一个xml文档，获得Document对象（根结点） 
    Document document = db.parse(new File("nltk_data.xml")); 
      
    NodeList list = document.getElementsByTagName("nltk_data"); 
    String tmp = "";
    String res="";
    for(int i = 0; i < list.getLength(); i++) 
    { 
      Element element = (Element)list.item(i); 
      
        
      Element content = (Element)element.getElementsByTagName("packages").item(0);
      NodeList pack = content.getElementsByTagName("package");
      for(int j=0;j<pack.getLength();j++){
    	  if(j >= 0){
    		  Element el = (Element)pack.item(j);
        	  tmp = el.getAttribute("url");
        	  System.out.println(j+":"+tmp);
        	  String t1[] =tmp.split("/");
        	  String dir = t1[t1.length-2];
        	  
        	  String data="cd ~/nltk_data/"+dir+"/\n";
        	  data += "wget "+tmp+"\n";
        	  res+=data;
        	  bufferWritter.write(data);
        	  bufferWritter.flush();
    	  }
    	  
      }
//      bufferWritter.write("a");
      bufferWritter.flush();
      bufferWritter.close();
      
      String[] t = tmp.split("/");
      for(int k=0;k<t.length;k++){
    	  System.out.println(k+":"+t[k]);
      }
      System.out.println(res); 
      
        
      
        
      System.out.println("--------------------------------------"); 
    } 
  } 
} 
