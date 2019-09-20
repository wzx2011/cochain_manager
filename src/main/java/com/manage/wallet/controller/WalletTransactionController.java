package com.manage.wallet.controller;

import com.manage.wallet.entity.dto.WalletTransactionDTO;
import com.manage.wallet.entity.vo.WalletTransactionVO;
import com.manage.wallet.service.IWalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 钱包交易信息 Controller控制层
 * @author wzx
 * @create 2019年06月12日 13:49:34
 **/
@Controller
public class WalletTransactionController extends BaseController{
	
	@Autowired
	private IWalletTransactionService walletTransactionService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入钱包交易信息 首页
	 * @Date 2019年06月12日 13:49:34
	 * @return java.lang.String
	 **/
	@RequestMapping("goWalletTransactionPage")
	public String goWalletTransactionPage() {
		return "financial/walletinfo/transaction_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年06月12日 13:49:34
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddWalletTransactionPage")
	public String goAddWalletTransactionPage() {
		return "financial/interface/transaction_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询钱包交易信息 列表
	 * @Date 2019年06月12日 13:49:34
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getWalletTransactionList")
	@ResponseBody
	public Map<String, Object> getWalletTransactionList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		WalletTransactionDTO walletTransactionDTO=new WalletTransactionDTO();
		walletTransactionDTO.setStart((page - 1) * rows);
		walletTransactionDTO.setEnd(rows);
		jsonMap = walletTransactionService.getWalletTransactionAllList(walletTransactionDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:49:34
	 * @Param [walletTransactionDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddWalletTransaction")
	@ResponseBody
	public Map<String, Object> doAddWalletTransaction(WalletTransactionDTO walletTransactionDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = walletTransactionService.saveWalletTransaction(walletTransactionDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年06月12日 13:49:34
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdWalletTransactionPage")
	public String goUpdWalletTransactionPage(int id, Model model) {
		WalletTransactionVO walletTransactionVO = walletTransactionService.getWalletTransactionById(id);
		model.addAttribute("walletTransactionVO", walletTransactionVO);
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年06月12日 13:49:34
	 * @Param [WalletTransactionDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdWalletTransaction")
	@ResponseBody
	public Map<String, Object> doUpdWalletTransaction(WalletTransactionDTO walletTransactionDTO) {
		Map<String, Object> jsonMap = walletTransactionService.updateWalletTransaction(walletTransactionDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年06月12日 13:49:34
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delWalletTransactionById")
	@ResponseBody
	public Map<String, Object> delWalletTransactionById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = walletTransactionService.deleteWalletTransactionById(id);
		return jsonMap;
	}
	
}
