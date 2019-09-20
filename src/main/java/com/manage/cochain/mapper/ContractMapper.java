package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.ContractVO;
import com.manage.cochain.entity.dto.ContractDTO;

/**
 * 合约信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 11:43:20
**/
public interface ContractMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return List<ContractVO>
	 **/
	List<ContractVO> getContractAllList(ContractDTO contractDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return Integer
	 **/
	Integer findContractTotal(ContractDTO contractDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return int
	 **/
	int saveContract(ContractDTO contractDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return int
	 **/
	int updateContract(ContractDTO contractDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:43:20
	 * @Param ContractDTO
	 * @return int
	 **/
	int deleteContractById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:43:20
	 * @Param id
	 * @return ContractVO
	 **/
	ContractVO getContractById(@Param("id")Integer id);

	/**
	 * 获取返回集合信息
	 * @param contractDTO
	 * @return
	 */
	List<ContractVO> getContractVOList(ContractDTO contractDTO);
}