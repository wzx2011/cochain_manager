package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.TransactionPoolDTO;
import com.manage.cochain.entity.vo.TransactionPoolVO;
import com.manage.cochain.service.ITransactionPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易数据池 Controller控制层
 * @author wzx
 * @create 2019年08月22日 10:22:10
 **/
@Controller
public class TransactionPoolController extends BaseController{
	
	@Autowired
	private ITransactionPoolService transactionPoolService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入交易数据池 首页
	 * @Date 2019年08月22日 10:22:10
	 * @return java.lang.String
	 **/
	@RequestMapping("goTransactionPoolPage")
	public String goTransactionPoolPage() {
		return "financial/poolinfo/transactionpool_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年08月22日 10:22:10
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddTransactionPoolPage")
	public String goAddTransactionPoolPage() {
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询交易数据池 列表
	 * @Date 2019年08月22日 10:22:10
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getTransactionPoolList")
	@ResponseBody
	public Map<String, Object> getTransactionPoolList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		TransactionPoolDTO transactionPoolDTO=new TransactionPoolDTO();
		transactionPoolDTO.setStart((page - 1) * rows);
		transactionPoolDTO.setEnd(rows);
		jsonMap = transactionPoolService.getTransactionPoolAllList(transactionPoolDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年08月22日 10:22:10
	 * @Param [transactionPoolDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddTransactionPool")
	@ResponseBody
	public Map<String, Object> doAddTransactionPool(TransactionPoolDTO transactionPoolDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = transactionPoolService.saveTransactionPool(transactionPoolDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年08月22日 10:22:10
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdTransactionPoolPage")
	public String goUpdTransactionPoolPage(int id, Model model) {
		TransactionPoolVO transactionPoolVO = transactionPoolService.getTransactionPoolById(id);
		model.addAttribute("transactionPoolVO", transactionPoolVO);
		return "financial/poolinfo/transactionpool_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年08月22日 10:22:10
	 * @Param [TransactionPoolDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdTransactionPool")
	@ResponseBody
	public Map<String, Object> doUpdTransactionPool(TransactionPoolDTO transactionPoolDTO) {
		Map<String, Object> jsonMap = transactionPoolService.updateTransactionPool(transactionPoolDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年08月22日 10:22:10
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delTransactionPoolById")
	@ResponseBody
	public Map<String, Object> delTransactionPoolById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = transactionPoolService.deleteTransactionPoolById(id);
		return jsonMap;
	}
	
}
