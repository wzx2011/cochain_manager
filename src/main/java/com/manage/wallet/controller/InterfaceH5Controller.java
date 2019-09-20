package com.manage.wallet.controller;

import com.manage.cochain.entity.dto.DemoLogDTO;
import com.manage.cochain.service.IDemoLogService;
import com.manage.httpclient.cochain.OKHttp3WalletUtil;
import com.manage.util.ResponseData;
import com.manage.wallet.entity.po.TokenInterfaceDO;
import com.manage.wallet.entity.po.TranInterfaceDO;
import com.manage.wallet.entity.po.UserInterfaceDO;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cochain_manager
 * @description: 接口控制器
 * @author: wzx
 * @create: 2019-06-25 15:00
 */
@Controller
public class InterfaceH5Controller {

    @Autowired
    private IDemoLogService demoLogService;

    private static Logger logger = Logger.getLogger(InterfaceH5Controller.class);

    /**
     * 调用新增钱包用户接口
     * @param userDO
     * @return
     */
    @RequestMapping("doCallUserInterface")
    @ResponseBody
    public ResponseData doCallUserInterface(UserInterfaceDO userDO) {
        OKHttp3WalletUtil okHttp3Util = new OKHttp3WalletUtil();
        ResponseData responseData = new ResponseData();

        String url = "http://39.104.135.20:8011/v1/wallet/add/"+userDO.getUserUid()+"/"+userDO.getUserPassword();
        String requestData = "uid:" + userDO.getUserUid() + ",password=" + userDO.getUserPassword();
        DemoLogDTO demoLogDTO = new DemoLogDTO();
        demoLogDTO.setRequestUrl(url);
        demoLogDTO.setRequestData(requestData);
        String userData = null;
        try {
            // 调用新增钱包用户接口
            userData = okHttp3Util.createUser(userDO.getUserUid(),userDO.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "调用新增钱包用户接口出错！" +
                    "\n接口参数：uid=" + userDO.getUserUid() + "\tpassword=" + userDO.getUserPassword() +
                    "\n错误信息：" + e.getMessage();

            // 调用接口日志
            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            return responseData;
        }

        String msg = "调用新增钱包用户接口成功！";
        responseData.setCode("0001");
        responseData.setMsg(msg);
        responseData.setObjData(userData);

        demoLogDTO.setStatus(1);
        demoLogDTO.setErrorMessage(msg);
        demoLogDTO.setResponseData(userData);
        demoLogService.saveDemoLog(demoLogDTO);

        return responseData;
    }

    /**
     * 调用发放圆票接口
     * @param tokenDO
     * @return
     */
    @RequestMapping("doCallUserTokenInterface")
    @ResponseBody
    public ResponseData doCallUserTokenInterface(TokenInterfaceDO tokenDO) {
        OKHttp3WalletUtil okHttp3Util = new OKHttp3WalletUtil();
        ResponseData responseData = new ResponseData();

        String endTime = tokenDO.getTokenEndTime() + " 00:00:00";

        String url = "http://39.104.135.20:8011/v1/wallet/token/create";
        Map<String, String> content = new HashMap<String, String>();
        content.put("accountId", tokenDO.getTokenAccountId());
        content.put("password", tokenDO.getTokenPassword());
        content.put("amount", tokenDO.getTokenAmount());
        content.put("endTime", endTime);

        DemoLogDTO demoLogDTO = new DemoLogDTO();
        demoLogDTO.setRequestUrl(url);
        demoLogDTO.setRequestData(content.toString());

        String tokenData = null;
        try {
            // 调用发放圆票接口
            tokenData = okHttp3Util.createToken(tokenDO.getTokenAccountId(),tokenDO.getTokenPassword(),tokenDO.getTokenAmount(),endTime);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "调用发放圆票接口出错！" +
                    "\n接口参数：accountId=" + tokenDO.getTokenAccountId() + "\tpassword=" + tokenDO.getTokenPassword() +
                    "\tamount=" + tokenDO.getTokenAmount() + "\tendTime=" + endTime +
                    "\n错误信息：" + e.getMessage();

            // 调用接口日志
            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            return responseData;
        }

        String msg = "调用发放圆票接口成功！";
        responseData.setCode("0001");
        responseData.setMsg(msg);
        responseData.setObjData(tokenData);

        demoLogDTO.setStatus(1);
        demoLogDTO.setErrorMessage(msg);
        demoLogDTO.setResponseData(tokenData);
        demoLogService.saveDemoLog(demoLogDTO);

        return responseData;
    }

    /**
     * 调用圆票转移接口
     * @param tranDO
     * @return
     */
    @RequestMapping("doCallUserTranInterface")
    @ResponseBody
    public ResponseData doCallUserTranInterface(TranInterfaceDO tranDO) {
        OKHttp3WalletUtil okHttp3Util = new OKHttp3WalletUtil();
        ResponseData responseData = new ResponseData();

        String url = "http://39.104.135.20:8011/v1/wallet/tran";
        Map<String, String> content = new HashMap<String, String>();
        content.put("tokenid", tranDO.getTranTokenId());
        content.put("fromId", tranDO.getTranFromId());
        content.put("toId", tranDO.getTranToId());
        content.put("amount", tranDO.getTranAmount());
        content.put("password", tranDO.getTranPassword());

        DemoLogDTO demoLogDTO = new DemoLogDTO();
        demoLogDTO.setRequestUrl(url);
        demoLogDTO.setRequestData(content.toString());

        JSONObject tranData = null;
        try {
            // 调用圆票转移接口
            tranData = okHttp3Util.tran(tranDO.getTranTokenId(),tranDO.getTranFromId(),tranDO.getTranToId(),tranDO.getTranAmount(), tranDO.getTranPassword());
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "调用圆票转移接口出错！" +
                    "\n接口参数：tokenid=" + tranDO.getTranTokenId() + "\tfromid=" + tranDO.getTranFromId() +
                    "\ttoid=" + tranDO.getTranToId() + "\tamount=" + tranDO.getTranAmount() + "\tpassword=" + tranDO.getTranPassword() +
                    "\n错误信息：" + e.getMessage();

            // 调用接口日志
            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            return responseData;
        }

        String msg = "调用发放圆票接口成功！";
        responseData.setCode("0001");
        responseData.setMsg(msg);
        responseData.setObjData(tranData.toString());

        demoLogDTO.setStatus(1);
        demoLogDTO.setErrorMessage(msg);
        demoLogDTO.setResponseData(tranData.toString());
        demoLogService.saveDemoLog(demoLogDTO);

        return responseData;
    }

}
