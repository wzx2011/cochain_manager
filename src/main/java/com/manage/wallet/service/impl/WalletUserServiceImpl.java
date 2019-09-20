package com.manage.wallet.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.wallet.mapper.WalletUserMapper;
import com.manage.wallet.entity.vo.WalletUserVO;
import com.manage.wallet.entity.dto.WalletUserDTO;
import com.manage.wallet.service.IWalletUserService;


/**
 * 钱包用户信息 Service 实现层
 * @author wzx
 * @create 2019年06月12日 13:50:10
 **/
@Service
public class WalletUserServiceImpl implements IWalletUserService  {

	@Autowired
	private WalletUserMapper walletUserMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return List<WalletUserVO>
	 **/
	@Override
	public Map<String,Object> getWalletUserAllList(WalletUserDTO walletUserDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<WalletUserVO> walletUserList = new ArrayList<>();
		Integer walletUserTotal = walletUserMapper.findWalletUserTotal(walletUserDTO);
		if(walletUserTotal>0){
			walletUserList = walletUserMapper.getWalletUserAllList(walletUserDTO);
		}
		resultMap.put("rows", walletUserList);
		resultMap.put("total", walletUserTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveWalletUser(WalletUserDTO walletUserDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletUserMapper.saveWalletUser(walletUserDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "添加成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "添加失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateWalletUser(WalletUserDTO walletUserDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletUserMapper.updateWalletUser(walletUserDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年06月12日 13:50:10
	 * @Param WalletUserDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteWalletUserById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletUserMapper.deleteWalletUserById(id)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年06月12日 13:50:10
	 * @Param id
	 * @return WalletUserVO
	 **/
	@Override
	public WalletUserVO getWalletUserById(Integer id){
		return walletUserMapper.getWalletUserById(id);
	}

	/**
	 * 根据登录用户名和密码查询钱包用户信息
	 *
	 * @param walletUserDTO
	 * @return
	 */
	@Override
	public WalletUserVO getWalletUserByLoginNameAndPassword(WalletUserDTO walletUserDTO) {
		return walletUserMapper.getWalletUserByLoginNameAndPassword(walletUserDTO);
	}
}
