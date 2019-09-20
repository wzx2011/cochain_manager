package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.ConstantsVO;
import com.manage.cochain.entity.dto.ConstantsDTO;

/**
 * 常量信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 11:42:11
**/
public interface ConstantsMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return List<ConstantsVO>
	 **/
	List<ConstantsVO> getConstantsAllList(ConstantsDTO constantsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return Integer
	 **/
	Integer findConstantsTotal(ConstantsDTO constantsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return int
	 **/
	int saveConstants(ConstantsDTO constantsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return int
	 **/
	int updateConstants(ConstantsDTO constantsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return int
	 **/
	int deleteConstantsById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:42:11
	 * @Param id
	 * @return ConstantsVO
	 **/
	ConstantsVO getConstantsById(@Param("id")Integer id);

}