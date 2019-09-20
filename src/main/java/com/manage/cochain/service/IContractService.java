package com.manage.cochain.service;


import com.manage.cochain.entity.dto.ContractDTO;
import com.manage.cochain.entity.vo.ContractVO;

import java.util.List;
import java.util.Map;
/**
 * 合约信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 11:43:20
 **/
public interface IContractService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return List<ContractVO>
	 **/
	Map<String,Object> getContractAllList(ContractDTO contractDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 11:43:20
	* @Param ContractDTO
	* @return Map
	**/
	Map<String, Object> saveContract(ContractDTO contractDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 11:43:20
	* @Param ContractDTO
	* @return Map
	**/
	Map<String, Object> updateContract(ContractDTO contractDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 11:43:20
	* @Param ContractDTO
	* @return Map
	**/
	Map<String, Object> deleteContractById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 11:43:20
	* @Param id
	* @return ContractVO
	**/
	ContractVO getContractById(Integer id);

	/**
	 * 获取返回集合信息
	 * @param contractDTO
	 * @return
	 */
	List<ContractVO> getContractVOList(ContractDTO contractDTO);
}
