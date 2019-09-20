package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.AppVO;
import com.manage.cochain.entity.dto.AppDTO;

/**
 * 应用信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 14:19:49
**/
public interface AppMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return List<AppVO>
	 **/
	List<AppVO> getAppAllList(AppDTO appDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return Integer
	 **/
	Integer findAppTotal(AppDTO appDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return int
	 **/
	int saveApp(AppDTO appDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return int
	 **/
	int updateApp(AppDTO appDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return int
	 **/
	int deleteAppById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 14:19:49
	 * @Param id
	 * @return AppVO
	 **/
	AppVO getAppById(@Param("id")Integer id);

	/**
	 * 获取应用的所用集合信息
	 * @param appDTO
	 * @return
	 */
	List<AppVO> getAppVOList(AppDTO appDTO);

}