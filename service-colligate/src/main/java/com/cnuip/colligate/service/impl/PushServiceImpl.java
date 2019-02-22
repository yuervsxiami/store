package com.cnuip.colligate.service.impl;

import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.PushService;
import com.cnuip.colligate.vo.domain.ConvType;
import com.cnuip.colligate.vo.domain.DeleteClass;
import com.cnuip.colligate.vo.domain.PushModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Crixalis on 2018/5/4.
 */

@Service
public class PushServiceImpl implements PushService {

    private Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.cnuip.url}")
    private String cnuipUrl;


    @Override
    public Object getRecords(String clientId, ConvType convType) throws Exception {
        Object pushDetailMap;
        try{
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-push/v1/push/messages?clientId={clientId}&convType={convType}", HttpMethod.GET, null, Map.class,clientId,convType);
            pushDetailMap = responseEntity.getBody().get("result");
            if(!responseEntity.getBody().get("code").toString().equals("0")) {
                pushDetailMap = responseEntity.getBody();
            }
        }catch (Exception e){
            logger.error(ClientEnum.PUSH_ERROR+":"+e);
            throw new ClientException(ClientEnum.PUSH_ERROR,e);
        }
        return pushDetailMap;
    }

    @Override
    public Object subscriber(String clientId, ConvType convType) throws Exception {
        Object pushDetailMap;
        try{
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-push/v1/push/subscriber?clientId={clientId}&convType={convType}", HttpMethod.POST, null, Map.class,clientId,convType);
            pushDetailMap = responseEntity.getBody().get("result");
            if(!responseEntity.getBody().get("code").toString().equals("0")) {
                pushDetailMap = responseEntity.getBody();
            }
        }catch (Exception e){
            logger.error(ClientEnum.PUSH_ERROR+":"+e);
            throw new ClientException(ClientEnum.PUSH_ERROR,e);
        }
        return pushDetailMap;
    }

    @Override
    public Object push(PushModel pushBody) throws Exception {
        Object pushDetailMap;
        try{
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-push/v1/push/", HttpMethod.POST, new HttpEntity<PushModel>(pushBody), Map.class);
            pushDetailMap = responseEntity.getBody().get("result");
            if(!responseEntity.getBody().get("code").toString().equals("0")) {
                pushDetailMap = responseEntity.getBody();
            }
        }catch (Exception e){
            logger.error(ClientEnum.PUSH_ERROR+":"+e);
            throw new ClientException(ClientEnum.PUSH_ERROR,e);
        }
        return pushDetailMap;
    }

    @Override
    public Object pushRecord(String objectId) throws Exception {
        Object pushDetailMap;
        try{
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-push/v1/push/record?objectId={objectId}", HttpMethod.GET, null, Map.class,objectId);
            pushDetailMap = responseEntity.getBody().get("result");
            if(!responseEntity.getBody().get("code").toString().equals("0")) {
                pushDetailMap = responseEntity.getBody();
            }
        }catch (Exception e){
            logger.error(ClientEnum.PUSH_ERROR+":"+e);
            throw new ClientException(ClientEnum.PUSH_ERROR,e);
        }
        return pushDetailMap;
    }

    @Override
    public Object deleteMessages(String clientId, ConvType convType, List<DeleteClass> deleteClazz) throws Exception {
        Object pushDetailMap;
        try{
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-push/v1/push/?clientId={clientId}&convType={convType}", HttpMethod.DELETE, new HttpEntity<List<DeleteClass>>(deleteClazz), Map.class,clientId,convType);
            pushDetailMap = responseEntity.getBody().get("result");
            if(!responseEntity.getBody().get("code").toString().equals("0")) {
                pushDetailMap = responseEntity.getBody();
            }
        }catch (Exception e){
            logger.error(ClientEnum.PUSH_ERROR+":"+e);
            throw new ClientException(ClientEnum.PUSH_ERROR,e);
        }
        return pushDetailMap;
    }
}
