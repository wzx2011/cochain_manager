package com.manage.wallet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.wallet.entity.vo.WalletUserVO;
import com.manage.wallet.entity.dto.WalletUserDTO;

/**
 * 钱包用户信息 Mappeer 接口层
 * @author wzx
 * @create 2019年06月12日 13:50:10
**/
public interface WalletUserMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return List<WalletUserVO>
	 **/
	List<WalletUserVO> getWalletUserAllList(WalletUserDTO walletUserDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return Integer
	 **/
	Integer findWalletUserTotal(WalletUserDTO walletUserDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return int
	 **/
	int saveWalletUser(WalletUserDTO walletUserDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return int
	 **/
	int updateWalletUser(WalletUserDTO walletUserDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return int
	 **/
	int deleteWalletUserById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年06月12日 13:50:10
	 * @Param id
	 * @return WalletUserVO
	 **/
	WalletUserVO getWalletUserById(@Param("id")Integer id);

	/**
	 * 根据登录用户名和密码查询钱包用户信息
	 * @param walletUserDTO
	 * @return
	 */
	WalletUserVO getWalletUserByLoginNameAndPassword(WalletUserDTO walletUserDTO);
}