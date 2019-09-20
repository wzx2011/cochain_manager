package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.DemoUrlVO;
import com.manage.cochain.entity.dto.DemoUrlDTO;

/**
 * 远程接口上链路径信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月17日 09:34:37
**/
public interface DemoUrlMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return List<DemoUrlVO>
	 **/
	List<DemoUrlVO> getDemoUrlAllList(DemoUrlDTO demoUrlDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return Integer
	 **/
	Integer findDemoUrlTotal(DemoUrlDTO demoUrlDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return int
	 **/
	int saveDemoUrl(DemoUrlDTO demoUrlDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return int
	 **/
	int updateDemoUrl(DemoUrlDTO demoUrlDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return int
	 **/
	int deleteDemoUrlById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月17日 09:34:37
	 * @Param id
	 * @return DemoUrlVO
	 **/
	DemoUrlVO getDemoUrlById(@Param("id")Integer id);

	/**
	 * 获取远程路径集合
	 * @param demoUrlDTO
	 * @return
	 */
	List<DemoUrlVO> getDemoUrlVOList(DemoUrlDTO demoUrlDTO);
}