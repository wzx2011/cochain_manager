package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.DemoLogDTO;
import com.manage.cochain.entity.po.UpchainBaseDO;
import com.manage.cochain.entity.po.UpchainTraceDO;
import com.manage.cochain.service.IDemoLogService;
import com.manage.httpclient.cochain.OKHttp3Util;
import com.manage.util.ResponseData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: apitable
 * @description: 上链接口管理控制层
 * @author: wzx
 * @create: 2019-05-17 09:19
 */
@Controller
public class DemoController {

    @Autowired
    private IDemoLogService demoLogService;

    private static Logger logger = Logger.getLogger(DemoController.class);

    /**
     * 进入基础信息上链页面
     * @return
     */
    @RequestMapping("goBasePage")
    public String goBasePage() {
        return "financial/chaininfo/base";
    }

    /**
     * 进入交易信息上链页面
     * @return
     */
    @RequestMapping("goTracePage")
    public String goTracePage() {
        return "financial/chaininfo/trace";
    }

    /**
     * 基础信息上链
     * @param upchainDataDO
     * @return
     */
    @RequestMapping("doBaseToChainInfo")
    @ResponseBody
    public ResponseData doBaseToChainInfo(UpchainBaseDO upchainDataDO) {
        DemoLogDTO demoLogDTO = new DemoLogDTO();
        OKHttp3Util okHttp3Util = new OKHttp3Util();
        ResponseData responseData = new ResponseData();
        String url = upchainDataDO.getIpPortB()+upchainDataDO.getProjectValueB()+upchainDataDO.getProjectUrlB();
        String content = null;
        String token = null;
        demoLogDTO.setRequestUrl(url);
        demoLogDTO.setRequestData(upchainDataDO.getParameterB());
        try {
            // 调用第三方接口获取token
            token = okHttp3Util.getToken(upchainDataDO.getAppidB(),upchainDataDO.getAppkeyB());
        } catch (Exception e) {
            String errorMsg = "调用第三方接口获取token出错！" +
                    "\n第三方接口：okHttp3Util.getToken" +
                    "\n接口参数：appid=" + upchainDataDO.getAppidB() + "\tappkey=" + upchainDataDO.getAppkeyB() +
                    "\n错误信息：" + e.getMessage();

            // 上链信息日志
            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            e.printStackTrace();
            return responseData;
        }

        try {
            // 替换参数
            content = replaceParameter(upchainDataDO.getParameterB(),token);
        } catch (Exception e) {
            String errorMsg = e.getMessage();

            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            e.printStackTrace();
            return responseData;
        }

        // 参数替换成功，则将替换参数返回到上链日志中
        demoLogDTO.setRequestData(content);

        JSONObject resultBaseData = null;
        try {
            // 基础信息上链
            resultBaseData = okHttp3Util.baseInfoToChain(url,content,token,upchainDataDO.getUidB(),upchainDataDO.getSecretkeyB());
        } catch (Exception e) {
            String errorMsg = "基础信息上链出错！" +
                    "\n第三方接口：okHttp3Util.baseInfoToChain" +
                    "\n接口参数：url=" + url + "\tcontent=" + content + "\ttoken=" + token +
                    "\tuid=" + upchainDataDO.getUidB() + "\tsecretkey=" + upchainDataDO.getSecretkeyB() +
                    "\n错误信息：" + e.getMessage();

            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            e.printStackTrace();
            return responseData;
        }

        String msg = "基础信息上链成功！请求url=" + url+
                "\n替换之后的参数为："+content;
        responseData.setCode("0001");
        responseData.setMsg(msg);
        responseData.setObjData(resultBaseData.toString());

        demoLogDTO.setStatus(1);
        demoLogDTO.setErrorMessage(msg);
        demoLogDTO.setResponseData(resultBaseData.toString());
        demoLogService.saveDemoLog(demoLogDTO);

        return responseData;
    }

    /**
     * 交易信息上链
     * @param upchainDataDO
     * @return
     */
    @RequestMapping("doTraceToChainInfo")
    @ResponseBody
    public ResponseData doTraceToChainInfo(UpchainTraceDO upchainDataDO) {
        DemoLogDTO demoLogDTO = new DemoLogDTO();
        OKHttp3Util okHttp3Util = new OKHttp3Util();
        ResponseData responseData = new ResponseData();
        String traceUrl = upchainDataDO.getIpPortT()
                +upchainDataDO.getProjectValueT()
                +upchainDataDO.getProjectUrlT();

        demoLogDTO.setRequestUrl(traceUrl);
        demoLogDTO.setRequestData(upchainDataDO.getParameterT());

        String token = null;
        try {
            // 调用第三方接口获取token
            token = okHttp3Util.getToken(upchainDataDO.getAppidT(),upchainDataDO.getAppkeyT());
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "调用第三方接口获取token出错！" +
                    "\n第三方接口：okHttp3Util.getToken" +
                    "\n接口参数：appid=" + upchainDataDO.getAppidT() + "\tappkey=" + upchainDataDO.getAppkeyT() +
                    "\n错误信息：" + e.getMessage();

            demoLogDTO.setStatus(0);
            demoLogDTO.setErrorMessage(errorMsg);
            demoLogService.saveDemoLog(demoLogDTO);

            responseData.setCode("0002");
            responseData.setMsg(errorMsg);
            logger.error(errorMsg);
            return responseData;
        }

        JSONArray array = JSONArray.fromObject(upchainDataDO.getParameterT());
        JSONObject resultTraceData = null;
        List<String> list = new ArrayList<>();
        for(int i=0;i<array.size();i++) {
            try {
                // 交易信息上链
                resultTraceData = okHttp3Util.traceInfoToChain(traceUrl,array.get(i).toString(),token,upchainDataDO.getUidT(),upchainDataDO.getSecretkeyT());
                list.add(resultTraceData.toString());
            } catch (Exception e) {
                String errorMsg = "交易信息上链出错！" +
                        "\n第三方接口：okHttp3Util.traceInfoToChain" +
                        "\n接口参数：url=" + traceUrl + "\tjsonData=" + array.get(i).toString() + "\ttoken=" + token +
                        "\tuid=" + upchainDataDO.getUidT() + "\tsecretkey=" + upchainDataDO.getSecretkeyT() +
                        "\n错误信息：" + e.getMessage();

                demoLogDTO.setStatus(0);
                demoLogDTO.setErrorMessage(errorMsg);
                demoLogService.saveDemoLog(demoLogDTO);

                responseData.setCode("0002");
                responseData.setMsg(errorMsg);
                logger.error(errorMsg);
                e.printStackTrace();
                return responseData;
            }
        }

        String msg = "交易信息上链成功！请求url=" + traceUrl;
        responseData.setCode("0001");
        responseData.setMsg(msg);
        responseData.setListData(list);

        demoLogDTO.setStatus(1);
        demoLogDTO.setErrorMessage(msg);
        demoLogDTO.setResponseData(list.toString());
        demoLogService.saveDemoLog(demoLogDTO);

        return responseData;
    }

    /**
     * 替换参数中的id
     * @param parameter
     * @param token
     * @return
     * @throws Exception
     */
    private String replaceParameter(String parameter,String token) throws Exception {
        JSONObject content = JSONObject.fromObject(parameter);
        JSONArray artifacts = content.getJSONArray("artifacts");

        int batchNumber = getIdLength(artifacts);
        Map<String, String> map = new HashMap<>();
        map.put("batchNumber", String.valueOf(batchNumber));
        OKHttp3Util okHttp3Util = new OKHttp3Util();
        JSONObject result = null;
        try {
            // 调用第三方接口获取替换的artifactIds数组
            result = okHttp3Util.getArtifactIds(JSONObject.fromObject(map).toString(),token);
        } catch (Exception e) {
            String errorMsg = "调用第三方接口获取替换的artifactIds数组出错！" +
                    "\n第三方接口：okHttp3Util.getArtifactIds" +
                    "\n接口参数：json=" + map + "\ttoken=" + token +
                    "\n错误信息：" + e.getMessage();
            logger.error(errorMsg);
            e.printStackTrace();
            throw new Exception(errorMsg);
        }

        JSONArray idArray = result.getJSONObject("data").getJSONArray("ids");
        int idLength = 0;
        for (int i = 0; i < artifacts.size(); i++) {
            JSONObject artifact = artifacts.getJSONObject(i);
            if (artifact.get("id") != null) {
                idLength++;
                artifact.put("id", idArray.get(idLength - 1));
            }

            if (artifact.get("children") == null) {
                continue;
            } else {
                JSONArray children = artifact.getJSONArray("children");
                for (int j = 0; j < children.size(); j++) {
                    JSONObject fchild = children.getJSONObject(j);
                    if (fchild.get("id") != null) {
                        idLength++;
                        fchild.put("id", idArray.get(idLength - 1));
                    }

                    if (fchild.get("children") == null) {
                        continue;
                    } else {
                        JSONArray schildren = fchild.getJSONArray("children");
                        for (int k = 0; k < schildren.size(); k++) {
                            JSONObject schild = schildren.getJSONObject(k);
                            if (schild.get("id") != null) {
                                idLength++;
                                schild.put("id", idArray.get(idLength - 1));
                            }
                        }
                    }
                }
            }
        }
        return content.toString();
    }

    /**
     * 获取参数中id属性的个数
     * @param artifacts
     * @return
     * @throws Exception
     */
    private int getIdLength(JSONArray artifacts) throws Exception{
        int idLength = 0;
        for (int i = 0; i < artifacts.size(); i++) {
            JSONObject artifact = artifacts.getJSONObject(i);
            if (artifact.get("id") != null) {
                idLength++;
            }

            if (artifact.get("children") == null) {
                continue;
            } else {
                JSONArray children = artifact.getJSONArray("children");
                for (int j = 0; j < children.size(); j++) {
                    JSONObject fchild = children.getJSONObject(j);
                    if (fchild.get("id") != null) {
                        idLength++;
                    }

                    if (fchild.get("children") == null) {
                        continue;
                    } else {
                        JSONArray schildren = fchild.getJSONArray("children");
                        for (int k = 0; k < schildren.size(); k++) {
                            JSONObject schild = schildren.getJSONObject(k);
                            if (schild.get("id") != null) {
                                idLength++;
                            }
                        }
                    }
                }
            }
        }
        return idLength;
    }

}
