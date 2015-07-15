package org.myself.mobile.web.wxpay;

public class MD5SignUtil {
	public static String Sign(String content, String key)
			throws SDKRuntimeException {
		String signStr = "";

		if ("" == key) {
			throw new SDKRuntimeException("�Ƹ�ͨǩ��key����Ϊ�գ�");
		}
		if ("" == content) {
			throw new SDKRuntimeException("�Ƹ�ͨǩ�����ݲ���Ϊ��");
		}
		signStr = content + "&key=" + key;

		return MD5Util.MD5(signStr).toUpperCase();

	}
	public static boolean VerifySignature(String content, String sign, String md5Key) {
		String signStr = content + "&key=" + md5Key;
		String calculateSign = MD5Util.MD5(signStr).toUpperCase();
		String tenpaySign = sign.toUpperCase();
		return (calculateSign == tenpaySign);
	}
}
