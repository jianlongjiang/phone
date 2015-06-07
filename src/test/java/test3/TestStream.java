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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestStream{

	public static void main( String[] args ) {
		String reqXml = getXml();
		XStream xs = new XStream(new DomDriver());
		xs.processAnnotations(new Class[] { AllnewstateRQ.class, Client.class, Sign.class });
		Object obj = xs.fromXML(reqXml);
		AllnewstateRQ allnewstateRQ = (AllnewstateRQ) obj;
		System.out.println(allnewstateRQ.getProtocol());
		System.out.println(allnewstateRQ.getClient().getArea());
		System.out.println(reqXml);

	}

	static String getXml(){
		StringBuilder str = new StringBuilder();
		str.append("")
		.append("<config>")
		.append("<client type11=\"8888\" osversion=\"9999\" version=\"123\" oemtag=\"5555\"  area=\"areacode\" />")
		.append("<protocol>1.10</protocol>")
		.append("<sign value=\"asdfasdf\" />")
		.append("<vientiance version=\"version\" />")
		.append("</config>")
		;
		return str.toString();
	}
}