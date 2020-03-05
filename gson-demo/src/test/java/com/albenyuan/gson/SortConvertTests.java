package com.albenyuan.gson;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

@Slf4j
public class SortConvertTests {


    @Test
    public void sortConvert() {

        String context = "{\"signature\":\"dJoEca+TPbrrJCJbgujUN2DZvya0n28mgTmRYpedzbyQarpVF/WPj7yTp3D/tRj7/bl8Eqfngsqftfzb0vfQCWYzFCijfVFh8Zs8IlDskra8FAwMDnd9geA7OU7/P+Fg1Km6qvxX3Fy8bn3iWVrELZmvbn3MxVOGAUzIkwS9vH/YZNAfyiYyz/xUmn5cm6+KZaC29LcnKQ7P+0Yfs+ijj5CLLRPdMo1nEE9Ydi2JlnUOYJOSz4SfhBSiGz0HycgWRU7EbxFiEn/VnYPKlKYwn2fZ5eiTxktXO4NEyTQ6qZxo1ItoC1xvu9uqrS893gaV0PJh03mTd82A/HkHioAcsw==\",\"response\":{\"head\":{\"appid\":\"28lp95314801\",\"version\":\"1.0.0\",\"sign_type\":\"SHA1withRSA\",\"response_time\":\"2020-03-03T17:44:52+08:00\"},\"body\":{\"result_code\":\"200\",\"biz_response\":{\"result_code\":\"FAIL\",\"error_code\":\"32007\",\"error_message\":\"付款码无效或已经超时\"}}}}";
        Gson gson = new Gson();
        LinkedHashMap map = gson.fromJson(context, LinkedHashMap.class);



        System.out.println(String.format("context: %s", context));
        System.out.println(String.format("signature: %s", map.get("signature")));
        String text = gson.toJson(map);
        System.out.println(String.format("text: %s", text));

        Assert.assertEquals(context, text);

    }
}
