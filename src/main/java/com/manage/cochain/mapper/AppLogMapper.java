package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.AppLogVO;
import com.manage.cochain.entity.dto.AppLogDTO;

/**
 * 应用日志信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 14:20:29
**/
public interface AppLogMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return List<AppLogVO>
	 **/
	List<AppLogVO> getAppLogAllList(AppLogDTO appLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return Integer
	 **/
	Integer findAppLogTotal(AppLogDTO appLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return int
	 **/
	int saveAppLog(AppLogDTO appLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return int
	 **/
	int updateAppLog(AppLogDTO appLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return int
	 **/
	int deleteAppLogById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 14:20:29
	 * @Param id
	 * @return AppLogVO
	 **/
	AppLogVO getAppLogById(@Param("id")Integer id);

}