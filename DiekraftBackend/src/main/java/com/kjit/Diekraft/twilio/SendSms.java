package com.kjit.Diekraft.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms {

    public static final String ACCOUNT_SID =
            "ACaef48c3483053f66107d8be05396f294";
    public static final String AUTH_TOKEN =
            "4e297468f0183c428034f3b87493644a";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+918000114108"), // to
                        new PhoneNumber("+18638694851"), // from
                        "hiiiiii")
                .create();

        System.out.println(message.getSid());
    }
}
