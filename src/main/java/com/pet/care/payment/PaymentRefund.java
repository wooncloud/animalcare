package com.pet.care.payment;

import java.io.IOException;
import java.util.Properties;

import com.pet.care.comm.Util;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public class PaymentRefund {

	IamportClient client;
	
	public void setup() {
		Properties prop = new Util().readProperties("properties/pay.properties");
		String key = prop.getProperty("key");
		String secret = prop.getProperty("secret");
		client = new IamportClient(key,secret);	
	}
	
	public void cancelPaymentByImpUid(String imp_uid) {
		CancelData cancel_data = new CancelData(imp_uid, true);
		try {
			IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
			System.out.println("환불완료");
		} catch (IamportResponseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
