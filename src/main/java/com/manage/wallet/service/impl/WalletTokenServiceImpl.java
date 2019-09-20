package com.manage.wallet.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.wallet.mapper.WalletTokenMapper;
import com.manage.wallet.entity.vo.WalletTokenVO;
import com.manage.wallet.entity.dto.WalletTokenDTO;
import com.manage.wallet.service.IWalletTokenService;


/**
 * 钱包积分信息 Service 实现层
 * @author wzx
 * @create 2019年06月12日 13:50:56
 **/
@Service
public class WalletTokenServiceImpl implements IWalletTokenService  {

	@Autowired
	private WalletTokenMapper walletTokenMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return List<WalletTokenVO>
	 **/
	@Override
	public Map<String,Object> getWalletTokenAllList(WalletTokenDTO walletTokenDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<WalletTokenVO> walletTokenList = new ArrayList<>();
		Integer walletTokenTotal = walletTokenMapper.findWalletTokenTotal(walletTokenDTO);
		if(walletTokenTotal>0){
			walletTokenList = walletTokenMapper.getWalletTokenAllList(walletTokenDTO);
		}
		resultMap.put("rows", walletTokenList);
		resultMap.put("total", walletTokenTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveWalletToken(WalletTokenDTO walletTokenDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletTokenMapper.saveWalletToken(walletTokenDTO)>0){
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
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateWalletToken(WalletTokenDTO walletTokenDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletTokenMapper.updateWalletToken(walletTokenDTO)>0){
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
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteWalletTokenById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletTokenMapper.deleteWalletTokenById(id)>0){
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
	 * @Date 2019年06月12日 13:50:56
	 * @Param id
	 * @return WalletTokenVO
	 **/
	@Override
	public WalletTokenVO getWalletTokenById(Integer id){
		return walletTokenMapper.getWalletTokenById(id);
	}

	/**
	 * 根据当前用户id连表查询获取当前用户的钱包积分数据
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<WalletTokenVO> getWalletTokenByUserId(Integer id) {
		return walletTokenMapper.getWalletTokenByUserId(id);
	}
}
