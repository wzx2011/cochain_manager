package com.manage.cochain.service;


import com.manage.cochain.entity.vo.ConstantsVO;
import com.manage.cochain.entity.dto.ConstantsDTO;
import java.util.Map;
/**
 * 常量信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 11:42:11
 **/
public interface IConstantsService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return List<ConstantsVO>
	 **/
	Map<String,Object> getConstantsAllList(ConstantsDTO constantsDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 11:42:11
	* @Param ConstantsDTO
	* @return Map
	**/
	Map<String, Object> saveConstants(ConstantsDTO constantsDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 11:42:11
	* @Param ConstantsDTO
	* @return Map
	**/
	Map<String, Object> updateConstants(ConstantsDTO constantsDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 11:42:11
	* @Param ConstantsDTO
	* @return Map
	**/
	Map<String, Object> deleteConstantsById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 11:42:11
	* @Param id
	* @return ConstantsVO
	**/
	ConstantsVO getConstantsById(Integer id);
}
