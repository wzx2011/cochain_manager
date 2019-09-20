package com.manage.wallet.controller;

import com.manage.wallet.entity.dto.WalletUserDTO;
import com.manage.wallet.entity.vo.WalletUserVO;
import com.manage.wallet.service.IWalletUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 钱包用户信息 Controller控制层
 * @author wzx
 * @create 2019年06月12日 13:50:10
 **/
@Controller
public class WalletUserController extends BaseController{
	
	@Autowired
	private IWalletUserService walletUserService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入钱包用户信息 首页
	 * @Date 2019年06月12日 13:50:10
	 * @return java.lang.String
	 **/
	@RequestMapping("goWalletUserPage")
	public String goWalletUserPage() {
		return "financial/walletinfo/user_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年06月12日 13:50:10
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddWalletUserPage")
	public String goAddWalletUserPage() {
		return "financial/interface/user_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询钱包用户信息 列表
	 * @Date 2019年06月12日 13:50:10
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getWalletUserList")
	@ResponseBody
	public Map<String, Object> getWalletUserList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		WalletUserDTO walletUserDTO=new WalletUserDTO();
		walletUserDTO.setStart((page - 1) * rows);
		walletUserDTO.setEnd(rows);
		jsonMap = walletUserService.getWalletUserAllList(walletUserDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:50:10
	 * @Param [walletUserDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddWalletUser")
	@ResponseBody
	public Map<String, Object> doAddWalletUser(WalletUserDTO walletUserDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = walletUserService.saveWalletUser(walletUserDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年06月12日 13:50:10
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdWalletUserPage")
	public String goUpdWalletUserPage(int id, Model model) {
		WalletUserVO walletUserVO = walletUserService.getWalletUserById(id);
		model.addAttribute("walletUserVO", walletUserVO);
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年06月12日 13:50:10
	 * @Param [WalletUserDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdWalletUser")
	@ResponseBody
	public Map<String, Object> doUpdWalletUser(WalletUserDTO walletUserDTO) {
		Map<String, Object> jsonMap = walletUserService.updateWalletUser(walletUserDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年06月12日 13:50:10
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delWalletUserById")
	@ResponseBody
	public Map<String, Object> delWalletUserById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = walletUserService.deleteWalletUserById(id);
		return jsonMap;
	}
	
}
