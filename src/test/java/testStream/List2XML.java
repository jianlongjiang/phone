/*******************************************************
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         	佛祖保佑       永无BUG  永不修改
         	佛祖镇楼                            BUG辟易  
                              佛曰:  
                                  写字楼里写字间，写字间里程序员；  
                                  程序人员写程序，又拿程序换酒钱。  
                                  酒醒只在网上坐，酒醉还来网下眠；  
                                  酒醉酒醒日复日，网上网下年复年。  
                                  但愿老死电脑间，不愿鞠躬老板前；  
                                  奔驰宝马贵者趣，公交自行程序员。  
                                  别人笑我忒疯癫，我笑自己命太贱；  
                                  不见满街漂亮妹，哪个归得程序员？  
************************************************************/
package testStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author long.jiang
 * @version  创建时间：2015-2-13 下午2:03:38   
 */
public class List2XML{
	public static void main(String args[]) {  
        XStream xs = new XStream(new DomDriver());  
        Address address1 = new Address(001, "经三路", "450000");  
        Address address2 = new Address(002, "朝阳区", "10000");  
        List<Address> list = new ArrayList<Address>();  
        list.add(address1);  
        list.add(address2);  
        Person person = new Person(001, 1, 26, "spark", list);  
        xs.alias("person", Person.class); // 类别名  
        xs.alias("address", Address.class);  
        xs.aliasField("addressList", Person.class, "addList"); // 类成员别名  
        xs.aliasField("addressId", Address.class, "id");  
        xs.useAttributeFor(Address.class, "zipcode"); // 设置某个节点为父节点上的一个属性  
         xs.aliasField("ZipCode", Address.class, "zipcode");  //与下面效果一样  
        
//        xs.aliasAttribute(Address.class, "zipcode", "ZipCode");  
        File file=new File("d:/temp/List2XML.xml");  
        if(!file.exists()||!file.isDirectory()){  
            String location=file.getAbsolutePath();  
            String[] arry=location.split("\\\\");  
            File file1=new File(arry[0]+"/"+arry[1]);  
            file1.mkdir();  
        }  
        try {  
            FileOutputStream fos=new FileOutputStream(file);  
            OutputStreamWriter osw=new OutputStreamWriter(fos,Charset.forName("utf-8"));  
            BufferedWriter bw=new BufferedWriter(osw);  
            xs.toXML(person,bw);  
            System.out.println("aaaa");
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
}
