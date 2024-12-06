package com.digitalojt.web.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.digitalojt.web.DTO.CenterInfoDTO;
import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.Region;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.repository.CenterInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のサービスクラス
 *
 * @author yamato mizoguchi
 * 
 */
@Service
@RequiredArgsConstructor
public class CenterInfoService {

	/** センター情報テーブル リポジトリー */
	private final CenterInfoRepository repository;

	/** ログのカテゴリ　画面名の取得*/
	private static Logger logger = LoggerFactory.getLogger(LogMessage.CENTER_INFO);
	
	/**
	 * 在庫センター情報を全件検索で取得
	 * 
	 * @return
	 */
	public List<CenterInfo> getCenterInfoData() {

		// 在庫センター情報作成
		List<CenterInfo> centerInfoList = repository.findAllOperationalStatus0DeleteFlag0();

		return centerInfoList;
	}

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region 
	 * @param storageCapacityFrom 
	 * @param storageCapacityTo
	 * @return 
	 */
	public List<CenterInfo> getCenterInfoData(String centerName, String region, Integer storageCapacityFrom, Integer storageCapacityTo) {

		// センター検索処理開始のログ
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.SEARCH_START);
		
		List<CenterInfo> centerInfoList = repository.findByCenterNameAndRegionAndStorageCapacity(centerName, region, storageCapacityFrom, storageCapacityTo);
		
		// センター検索処理正常終了のログ
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS
				+ LogMessage.SearchResult(centerInfoList));
		
		return centerInfoList;
	}
	
	/**
	 * 未検索時とバリデーションエラー時、画面表示用にデータを取得
	 * 
	 * @param form
	 * @return 在庫センター情報画面DTO
	 */
	public CenterInfoDTO setCenterInfoDTO() {
		CenterInfoDTO centerInfoDTO = new CenterInfoDTO();
		
		centerInfoDTO.setCenterInfoList(getCenterInfoData());
		centerInfoDTO.setRegions(Arrays.asList(Region.values()));
		
		return centerInfoDTO;
	}
	
	/**
	 * バリデーション突破時、画面表示用にデータを取得
	 * 
	 * @param form
	 * @return 在庫センター情報画面DTO
	 */
	public CenterInfoDTO setCenterInfoDTO(CenterInfoForm form) {
		CenterInfoDTO centerInfoDTO = new CenterInfoDTO();
		
		centerInfoDTO.setCenterInfoList(getCenterInfoData(form.getCenterName(), form.getRegion(),
				((form.getStorageCapacityFrom() == null) ? null : Integer.parseInt(form.getStorageCapacityFrom())), (form.getStorageCapacityTo() == null) ? null : Integer.parseInt(form.getStorageCapacityTo())));
		centerInfoDTO.setRegions(Arrays.asList(Region.values()));
		
		return centerInfoDTO;
	}
}