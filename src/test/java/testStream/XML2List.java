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

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.util.Iterator;  
import java.util.List;  
  
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.io.xml.DomDriver;  
  
public class XML2List {  
  
    public static void main(String args[]){  
        XStream xs=new XStream(new DomDriver());  
        File file=new File("d:/temp/List2XML.xml");  
        try {  
            FileInputStream fis=new FileInputStream(file);  
            xs.alias("person", Person.class);  
            xs.alias("address", Address.class);  
            xs.aliasAttribute(Person.class,"addList", "addressList");  
            xs.aliasAttribute(Address.class, "id", "addressId");  
            xs.aliasAttribute(Address.class, "zipcode", "ZipCode");  //前后顺序无关  
         
            xs.useAttributeFor(Address.class, "zipcode");  
            Person person=(Person) xs.fromXML(fis);  
            System.out.println("name="+person.getName());  
            List<?> list=person.getAddList();  
            Iterator<?> iter=list.iterator();  
            Address add;  
            System.out.println("地址信息：");  
            while(iter.hasNext()){  
                add=(Address) iter.next();  
                System.out.println(add.getAdd());  
                System.out.println(add.getZipcode());  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
}  
