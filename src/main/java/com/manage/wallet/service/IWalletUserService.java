package com.manage.wallet.service;


import com.manage.wallet.entity.vo.WalletUserVO;
import com.manage.wallet.entity.dto.WalletUserDTO;
import java.util.Map;
/**
 * 钱包用户信息 Service接口层
 * @author wzx
 * @create 2019年06月12日 13:50:10
 **/
public interface IWalletUserService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return List<WalletUserVO>
	 **/
	Map<String,Object> getWalletUserAllList(WalletUserDTO walletUserDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年06月12日 13:50:10
	* @Param WalletUserDTO
	* @return Map
	**/
	Map<String, Object> saveWalletUser(WalletUserDTO walletUserDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年06月12日 13:50:10
	* @Param WalletUserDTO
	* @return Map
	**/
	Map<String, Object> updateWalletUser(WalletUserDTO walletUserDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年06月12日 13:50:10
	* @Param WalletUserDTO
	* @return Map
	**/
	Map<String, Object> deleteWalletUserById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年06月12日 13:50:10
	* @Param id
	* @return WalletUserVO
	**/
	WalletUserVO getWalletUserById(Integer id);

	/**
	 * 根据登录用户名和密码查询钱包用户信息
	 * @param walletUserDTO
	 * @return
	 */
	WalletUserVO getWalletUserByLoginNameAndPassword(WalletUserDTO walletUserDTO);
}
