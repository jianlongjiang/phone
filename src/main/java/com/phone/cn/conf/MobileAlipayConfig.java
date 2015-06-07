package com.phone.cn.conf;
/**
 * @author zgdcool
 * @version 2015年2月10日 下午12:34:16
 *   
 */
public class MobileAlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088111263511865";
	
	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	public static String key = "v7j1k63olk1a74rm96j976pitujf3x0d";
	
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANGIMqVvzeMQKdvF"
		+"1kK8++GRjqYhcw7VoGF184IFlresZ0v3/11YvvNORnZwrDlBG0vYsNPougR49vAp"
		+"dLYQtY1gacUXyHoa2Qr0bJSTn8EFU8dBSSldbKKddCcGVjU8ZYFVf8XAQoV4dPaV"
		+"dzPCBYXxxZ6o4+m87VYHE9S36Vw5AgMBAAECgYBVJy6+ASQBJW59fHdJBBixYUBB"
		+"z9I8syDMuuzNaIwJ9Sy9uPm06/4eTy9frwau9gCr4FJsrGX8r1Rb4d0mqL+/Cndp"
		+"JOavyrcXWnu8p8eTb7OWhd+NKOLrbu+k0Cp6NKI7iSeFPHv3NXXoPWyH2XkhpG8j"
		+"upWSbPqJxtbplQJHiQJBAPNQz3WnnOUtMdr0pZWg++7Vl91T653ogZaozGm+UoSG"
		+"BMgcG4j5zh/cTh2nAmB5ePB4GQG27YPKcDeEn6oImhcCQQDcdIZnIgezDtlB4pgW"
		+"T44E84EYgTyDeu/kfTtbJNksNa7shDnQ2XQH3axqT5gmbNwHFONQFJ6KoojoyxWk"
		+"yL4vAkEA325Eo8DgBV9EGdFwQH/M+Ruw0fBfO+knHscwtqWrtsAuoeeVrWnfS0iN"
		+"fxM+zoKdeQx/wiBN6Si7dW/XAlfVzwJAHzdg0LnA3Vn86ZdscbxSH+FbJl0mdHe0"
		+"AGa7QOy208vGOTd/+FLSW0rIX0VmBgrkHRyYE37X9EmPmWcoaW0DgQJBAIDnu/GT"
		+"WYt28L9pCXrxaFnwU99+264IX/QZWi/ZTenLURpbmXIfacnh/UReQmCl8AE8dSTT"
		+"lmSBAM3knhyg3Y4=";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClGlDD/"
			+ "y1yFeWMdzCorbohkz/JN9fCjKN/yHfZ 2pb3sClJWknOaPIv7JOa1ejs4PXTXNTy/44hKbK"
			+ "npb0zpfpAcI6sKiKlkBQZYiWwjPLBVdHOO6aR wDU2EtMlCEoKm3vgERKMFPhffvOyDe7nG"
			+ "YbLO4bE15Yqi5gbDEN+ILP9LwIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/Users/zgdcool/Desktop/";

	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

}
