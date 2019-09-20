package com.manage.cochain.service;


import com.manage.cochain.entity.vo.BasePoolVO;
import com.manage.cochain.entity.dto.BasePoolDTO;
import java.util.Map;
/**
 * 基础数据池 Service接口层
 * @author wzx
 * @create 2019年08月22日 10:21:07
 **/
public interface IBasePoolService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return List<BasePoolVO>
	 **/
	Map<String,Object> getBasePoolAllList(BasePoolDTO basePoolDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年08月22日 10:21:07
	* @Param BasePoolDTO
	* @return Map
	**/
	Map<String, Object> saveBasePool(BasePoolDTO basePoolDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年08月22日 10:21:07
	* @Param BasePoolDTO
	* @return Map
	**/
	Map<String, Object> updateBasePool(BasePoolDTO basePoolDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年08月22日 10:21:07
	* @Param BasePoolDTO
	* @return Map
	**/
	Map<String, Object> deleteBasePoolById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年08月22日 10:21:07
	* @Param id
	* @return BasePoolVO
	**/
	BasePoolVO getBasePoolById(Integer id);
}
