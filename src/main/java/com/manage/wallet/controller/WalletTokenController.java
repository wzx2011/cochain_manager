package com.manage.wallet.controller;

import com.manage.wallet.entity.dto.WalletTokenDTO;
import com.manage.wallet.entity.vo.WalletTokenVO;
import com.manage.wallet.service.IWalletTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 钱包积分信息 Controller控制层
 * @author wzx
 * @create 2019年06月12日 13:50:56
 **/
@Controller
public class WalletTokenController extends BaseController{
	
	@Autowired
	private IWalletTokenService walletTokenService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入钱包积分信息 首页
	 * @Date 2019年06月12日 13:50:56
	 * @return java.lang.String
	 **/
	@RequestMapping("goWalletTokenPage")
	public String goWalletTokenPage() {
		return "financial/walletinfo/token_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年06月12日 13:50:56
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddWalletTokenPage")
	public String goAddWalletTokenPage() {
		return "financial/interface/token_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询钱包积分信息 列表
	 * @Date 2019年06月12日 13:50:56
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getWalletTokenList")
	@ResponseBody
	public Map<String, Object> getWalletTokenList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		WalletTokenDTO walletTokenDTO=new WalletTokenDTO();
		walletTokenDTO.setStart((page - 1) * rows);
		walletTokenDTO.setEnd(rows);
		jsonMap = walletTokenService.getWalletTokenAllList(walletTokenDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:50:56
	 * @Param [walletTokenDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddWalletToken")
	@ResponseBody
	public Map<String, Object> doAddWalletToken(WalletTokenDTO walletTokenDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = walletTokenService.saveWalletToken(walletTokenDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年06月12日 13:50:56
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdWalletTokenPage")
	public String goUpdWalletTokenPage(int id, Model model) {
		WalletTokenVO walletTokenVO = walletTokenService.getWalletTokenById(id);
		model.addAttribute("walletTokenVO", walletTokenVO);
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年06月12日 13:50:56
	 * @Param [WalletTokenDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdWalletToken")
	@ResponseBody
	public Map<String, Object> doUpdWalletToken(WalletTokenDTO walletTokenDTO) {
		Map<String, Object> jsonMap = walletTokenService.updateWalletToken(walletTokenDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年06月12日 13:50:56
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delWalletTokenById")
	@ResponseBody
	public Map<String, Object> delWalletTokenById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = walletTokenService.deleteWalletTokenById(id);
		return jsonMap;
	}
	
}
