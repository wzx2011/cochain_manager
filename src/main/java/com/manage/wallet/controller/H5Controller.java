package com.manage.wallet.controller;

import com.manage.util.ResponseData;
import com.manage.wallet.entity.dto.WalletUserDTO;
import com.manage.wallet.entity.vo.WalletTokenVO;
import com.manage.wallet.entity.vo.WalletUserVO;
import com.manage.wallet.service.IWalletTokenService;
import com.manage.wallet.service.IWalletTransactionService;
import com.manage.wallet.service.IWalletUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钱包交易信息 Controller控制层
 * @author wzx
 * @create 2019年06月12日 13:49:34
 **/
@Controller
public class H5Controller{

	@Autowired
	private IWalletUserService walletUserService;

	@Autowired
	private IWalletTokenService walletTokenService;

	@Autowired
	private IWalletTransactionService walletTransactionService;

	private static Logger logger = Logger.getLogger(H5Controller.class);

	/**
	 * 钱包用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("h5/wlogin")
	@ResponseBody
	public ResponseData doLogin(@RequestParam("username")  String username,
								@RequestParam("password")  String password) {
		// 返回数据的集合
		ResponseData responseData = new ResponseData();
		// 入参实体
		WalletUserDTO walletUserDTO = new WalletUserDTO();
		walletUserDTO.setLoginName(username);
		walletUserDTO.setPassword(password);

		// 返回数据
		WalletUserVO walletUserVO = walletUserService.getWalletUserByLoginNameAndPassword(walletUserDTO);
		if (walletUserVO != null) {
			responseData.setCode("0001");
			responseData.setMsg("登录成功！");
			responseData.setObjData(walletUserVO);
		} else {
			responseData.setCode("0002");
			responseData.setMsg("登录失败！用户名或密码有误，请重新输入！");
		}
		return responseData;
	}

	/**
	 * 获取钱包用户积分信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("h5/token")
	@ResponseBody
	public ResponseData queryWalletToken(@RequestParam("userId")  Integer userId) {
		// 返回数据的集合
		ResponseData responseData = new ResponseData();

		// 连表查询获取当前用户的钱包积分数据
		List<WalletTokenVO> walletTokenVOS = walletTokenService.getWalletTokenByUserId(userId);
		// 判断是否有数据返回
		if (walletTokenVOS.size() != 0){
			responseData.setCode("0001");
			responseData.setMsg("获取钱包用户积分信息集合成功！");
			responseData.setListData(walletTokenVOS);
		} else {
			responseData.setCode("0002");
			responseData.setMsg("当前用户尚无积分信息！");
			responseData.setListData(walletTokenVOS);
		}

		return responseData;
	}

	/**
	 * 获取钱包用户交易相关信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("h5/transaction")
	@ResponseBody
	public ResponseData queryWalletTransaction(@RequestParam("userId")  Integer userId,
											   @RequestParam("tokenId")  Integer tokenId) {
		// 返回数据的集合
		ResponseData responseData = new ResponseData();

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("tokenId",tokenId);

		// 连表查询获取当前用户的钱包交易数据
		List<Map<String, Object>> listData = walletTransactionService.getWalletTransactionByUserId(paraMap);

		if (listData.size() != 0) {
			responseData.setCode("0001");
			responseData.setMsg("获取钱包用户交易信息集合成功！");
			responseData.setListData(listData);
		} else {
			responseData.setCode("0002");
			responseData.setMsg("当前用户尚无兑换记录！");
			responseData.setListData(listData);
		}

		return responseData;
	}

}
