package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.DemoProjectVO;
import com.manage.cochain.entity.dto.DemoProjectDTO;

/**
 * 远程接口项目信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月17日 09:33:36
**/
public interface DemoProjectMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return List<DemoProjectVO>
	 **/
	List<DemoProjectVO> getDemoProjectAllList(DemoProjectDTO demoProjectDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return Integer
	 **/
	Integer findDemoProjectTotal(DemoProjectDTO demoProjectDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return int
	 **/
	int saveDemoProject(DemoProjectDTO demoProjectDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return int
	 **/
	int updateDemoProject(DemoProjectDTO demoProjectDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return int
	 **/
	int deleteDemoProjectById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月17日 09:33:36
	 * @Param id
	 * @return DemoProjectVO
	 **/
	DemoProjectVO getDemoProjectById(@Param("id")Integer id);

	/**
	 * 获取project集合
	 * @param demoProjectDTO
	 * @return
	 */
	List<DemoProjectVO> getDemoProjectVOList(DemoProjectDTO demoProjectDTO);
}