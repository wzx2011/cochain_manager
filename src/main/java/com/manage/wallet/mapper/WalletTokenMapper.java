package com.manage.wallet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.wallet.entity.vo.WalletTokenVO;
import com.manage.wallet.entity.dto.WalletTokenDTO;

/**
 * 钱包积分信息 Mappeer 接口层
 * @author wzx
 * @create 2019年06月12日 13:50:56
**/
public interface WalletTokenMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return List<WalletTokenVO>
	 **/
	List<WalletTokenVO> getWalletTokenAllList(WalletTokenDTO walletTokenDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return Integer
	 **/
	Integer findWalletTokenTotal(WalletTokenDTO walletTokenDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return int
	 **/
	int saveWalletToken(WalletTokenDTO walletTokenDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return int
	 **/
	int updateWalletToken(WalletTokenDTO walletTokenDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return int
	 **/
	int deleteWalletTokenById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年06月12日 13:50:56
	 * @Param id
	 * @return WalletTokenVO
	 **/
	WalletTokenVO getWalletTokenById(@Param("id")Integer id);

	/**
	 * 根据当前用户id连表查询获取当前用户的钱包积分数据
	 * @param id
	 * @return
	 */
	List<WalletTokenVO> getWalletTokenByUserId(Integer id);
}