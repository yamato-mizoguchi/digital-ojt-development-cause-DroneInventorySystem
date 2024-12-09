package com.digitalojt.web.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalojt.web.DTO.CenterInfoDTO;
import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.Region;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.exception.CenterInfoException;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.form.CenterInfoRegisterForm;
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
		
		try {
		// 在庫センター情報作成
		List<CenterInfo> centerInfoList = repository.findAllOperationalStatus0DeleteFlag0();

		return centerInfoList;
		}catch(Exception e) {
			throw new CenterInfoException(ErrorMessage.CENTER_DB_EXCEPTION);
		}
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
	public List<CenterInfo> getCenterInfoData(String centerName, String region, Integer storageCapacityFrom,
			Integer storageCapacityTo) {
		
		try {
		// センター検索処理開始のログ
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.SEARCH_START);

		List<CenterInfo> centerInfoList = repository.findByCenterNameAndRegionAndStorageCapacity(centerName, region,
				storageCapacityFrom, storageCapacityTo);

		// センター検索処理正常終了のログ
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS
				+ LogMessage.SearchResult(centerInfoList));
		
		return centerInfoList;
		}catch(Exception e) {
			throw new CenterInfoException(ErrorMessage.CENTER_DB_EXCEPTION);
		}
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
				((form.getStorageCapacityFrom() == null) ? null : Integer.parseInt(form.getStorageCapacityFrom())),
				(form.getStorageCapacityTo() == null) ? null : Integer.parseInt(form.getStorageCapacityTo())));
		centerInfoDTO.setRegions(Arrays.asList(Region.values()));

		return centerInfoDTO;
	}

	/**
	 * 在庫センター情報新規登録
	 * 
	 * @param form
	 */
	@Transactional
	public void registerCenterInfo(CenterInfoRegisterForm form) {

		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.REGISTER_START);

		try {
			CenterInfo centerInfo = new CenterInfo();
			centerInfo.setCenterName(form.getCenterName());
			centerInfo.setPostCode(form.getPostCode());
			centerInfo.setAddress(form.getAddress());
			centerInfo.setPhoneNumber(form.getPhoneNumber());
			centerInfo.setManagerName(form.getManagerName());
			centerInfo.setOperationalStatus(form.getOperationalStatus());
			centerInfo.setMaxStorageCapacity(form.getMaxStorageCapacity());
			centerInfo.setCurrentStorageCapacity(form.getCurrentStorageCapacity());
			centerInfo.setNotes(form.getNotes());
			centerInfo.setDeleteFlag("0");

			repository.save(centerInfo); // 保存
		} catch (Exception e) {
			throw new CenterInfoException(ErrorMessage.CENTER_DB_EXCEPTION);
		}
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.REGISTER_END);
	}
}