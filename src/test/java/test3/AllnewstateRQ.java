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

@XStreamAlias("config")
public class AllnewstateRQ{
	// 当节点下有独立属性的时候，需要创建一个独立的类用来保存节点内的属性
	private Client		client		= new Client();
	private Sign		sign		= new Sign();
//	private Vientiance	vientiance	= new Vientiance();
	// 当节点下没有属性，直接由StringValue的时候可直接创建String类型属性
	private String		protocol;

	public Client getClient() {
		return client;
	}

	public void setClient( Client client ) {
		this.client = client;
	}

	public Sign getSign() {
		return sign;
	}

	public void setSign( Sign sign ) {
		this.sign = sign;
	}

//	public Vientiance getVientiance() {
//		return vientiance;
//	}
//
//	public void setVientiance( Vientiance vientiance ) {
//		this.vientiance = vientiance;
//	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol( String protocol ) {
		this.protocol = protocol;
	}
}
