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
package test3;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("client")
public class Client {
@XStreamAsAttribute //对属性值进行注解
@XStreamAlias(value="type11")
private String type;//此时类中的属性名要和xml内的属性名相对应
 
@XStreamAsAttribute
private String osversion;
 
@XStreamAsAttribute
private String version;
 
@XStreamAsAttribute
private String oemtag;
 
@XStreamAsAttribute
private String area;
 
public String getType() {
return type;
}
 
public void setType(String type) {
this.type = type;
}
 
public String getOsversion() {
return osversion;
}
 
public void setOsversion(String osversion) {
this.osversion = osversion;
}
 
public String getVersion() {
return version;
}
 
public void setVersion(String version) {
this.version = version;
}
 
public String getOemtag() {
return oemtag;
}
 
public void setOemtag(String oemtag) {
this.oemtag = oemtag;
}
 
public String getArea() {
return area;
}
 
public void setArea(String area) {
this.area = area;
}
 
}
