package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.ContractDTO;
import com.manage.cochain.entity.vo.ContractVO;
import com.manage.cochain.mapper.ContractMapper;
import com.manage.cochain.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 合约信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 11:43:20
 **/
@Service
public class ContractServiceImpl implements IContractService  {

	@Autowired
	private ContractMapper contractMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return List<ContractVO>
	 **/
	@Override
	public Map<String,Object> getContractAllList(ContractDTO contractDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<ContractVO> contractList = new ArrayList<>();
		Integer contractTotal = contractMapper.findContractTotal(contractDTO);
		if(contractTotal>0){
			contractList = contractMapper.getContractAllList(contractDTO);
		}
		resultMap.put("rows", contractList);
		resultMap.put("total", contractTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveContract(ContractDTO contractDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(contractMapper.saveContract(contractDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "添加成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "添加失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateContract(ContractDTO contractDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(contractMapper.updateContract(contractDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteContractById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(contractMapper.deleteContractById(id)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:43:20
	 * @Param id
	 * @return ContractVO
	 **/
	@Override
	public ContractVO getContractById(Integer id){
		return contractMapper.getContractById(id);
	}

	/**
	 * 获取返回集合信息
	 *
	 * @param contractDTO
	 * @return
	 */
	@Override
	public List<ContractVO> getContractVOList(ContractDTO contractDTO) {
		return contractMapper.getContractVOList(contractDTO);
	}
}
