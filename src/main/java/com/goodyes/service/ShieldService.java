package com.goodyes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodyes.exception.DefaultException;
import com.goodyes.http.HttpClient;
import com.goodyes.utils.CommonUtil;
import com.goodyes.vo.ShieldCloudCheckResponse;
import com.goodyes.vo.UrlParam;

import java.util.HashMap;
import java.util.Map;

public class ShieldService {

  private ObjectMapper objectMapper = new ObjectMapper();
  private HttpClient httpClient = new HttpClient();

  private static final String SHIELD_URL = "https://wechaturl.us/api/CheckIp.json";

  public ShieldService(){
  }

  /**
   * 本功能的作用是屏蔽厂商的云端检测功能
   *
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>ip - ip地址，目前只支持ipV4</li>
   *   </ul>
   * @param urlParam
   * @return ShieldCloudCheckResponse
   * @throws DefaultException
   * @throws JsonProcessingException
   */
  public ShieldCloudCheckResponse ShieldCloudCrawlerCheck(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("ip", urlParam.getIp());
    String res = httpClient.doPost(SHIELD_URL, paraMap);
    return objectMapper.readValue(res, new TypeReference<ShieldCloudCheckResponse>(){});
  }
}
