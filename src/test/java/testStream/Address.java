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

/**
 * @author long.jiang
 * @version  创建时间：2015-2-13 下午2:02:35   
 */
public class Address{
	 private int id;  
	    private String add;  
	    private String zipcode;  
	  
	    public Address(int id, String add, String zipcode) {  
	        this.id = id;  
	        this.add = add;  
	        this.zipcode = zipcode;  
	    }  
	  
	    public int getId() {  
	        return id;  
	    }  
	  
	    public void setId(int id) {  
	        this.id = id;  
	    }  
	  
	    public String getAdd() {  
	        return add;  
	    }  
	  
	    public void setAdd(String add) {  
	        this.add = add;  
	    }  
	  
	    public String getZipcode() {  
	        return zipcode;  
	    }  
	  
	    public void setZipcode(String zipcode) {  
	        this.zipcode = zipcode;  
	    }  
}
