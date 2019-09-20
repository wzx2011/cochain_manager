package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.ContractDTO;
import com.manage.cochain.entity.vo.ContractVO;
import com.manage.cochain.service.IContractService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合约信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 11:43:20
 **/
@Controller
public class ContractController extends BaseController{
	
	@Autowired
	private IContractService contractService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入合约信息 首页
	 * @Date 2019年05月07日 11:43:20
	 * @return java.lang.String
	 **/
	@RequestMapping("goContractPage")
	public String goContractPage() {
		return "financial/contractinfo/contract_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 11:43:20
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddContractPage")
	public String goAddContractPage() {
		return "financial/contractinfo/contract_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询合约信息 列表
	 * @Date 2019年05月07日 11:43:20
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getContractList")
	@ResponseBody
	public Map<String, Object> getContractList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ContractDTO contractDTO=new ContractDTO();
		contractDTO.setStart((page - 1) * rows);
		contractDTO.setEnd(rows);
		jsonMap = contractService.getContractAllList(contractDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:43:20
	 * @Param [contractDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddContract")
	@ResponseBody
	public Map<String, Object> doAddContract(ContractDTO contractDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = contractService.saveContract(contractDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 11:43:20
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdContractPage")
	public String goUpdContractPage(int id, Model model) {
		ContractVO contractVO = contractService.getContractById(id);
		model.addAttribute("contractVO", contractVO);
		return "financial/contractinfo/contract_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 11:43:20
	 * @Param [ContractDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdContract")
	@ResponseBody
	public Map<String, Object> doUpdContract(ContractDTO contractDTO) {
		Map<String, Object> jsonMap = contractService.updateContract(contractDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 11:43:20
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delContractById")
	@ResponseBody
	public Map<String, Object> delContractById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = contractService.deleteContractById(id);
		return jsonMap;
	}

	@RequestMapping("getContractVOList")
	@ResponseBody
	public Map<String, Object> getContractVOList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ContractDTO contractDTO=new ContractDTO();
		List<ContractVO> contractVOList = contractService.getContractVOList(contractDTO);
		resultMap.put("clist",contractVOList);
		return resultMap;
	}
}
