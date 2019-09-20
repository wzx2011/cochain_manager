package com.manage.wallet.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.manage.wallet.entity.vo.WalletTransactionVO;
import com.manage.wallet.entity.dto.WalletTransactionDTO;

/**
 * 钱包交易信息 Mappeer 接口层
 * @author wzx
 * @create 2019年06月12日 13:49:34
**/
public interface WalletTransactionMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return List<WalletTransactionVO>
	 **/
	List<WalletTransactionVO> getWalletTransactionAllList(WalletTransactionDTO walletTransactionDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return Integer
	 **/
	Integer findWalletTransactionTotal(WalletTransactionDTO walletTransactionDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return int
	 **/
	int saveWalletTransaction(WalletTransactionDTO walletTransactionDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return int
	 **/
	int updateWalletTransaction(WalletTransactionDTO walletTransactionDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return int
	 **/
	int deleteWalletTransactionById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年06月12日 13:49:34
	 * @Param id
	 * @return WalletTransactionVO
	 **/
	WalletTransactionVO getWalletTransactionById(@Param("id")Integer id);

	/**
	 * 根据当前用户id和tokenId连表查询获取当前用户的钱包交易数据
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> getWalletTransactionByUserId(Map<String, Object> paraMap);
}