package com.digitalojt.web.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * センター情報Entity
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@Getter
@Setter
@Entity
public class CenterInfo {

	/**
	 * センターID
	 */
	@Id
	private int centerId;
	
	/**
	 * センター名
	 */
	private String centerName;
	
	/**
	 * 住所
	 */
	private String address;
	
	/**
	 * 電話番号
	 */
	private String phoneNumber;
	
	/**
	 * 管理者名
	 */
	private String managerName;
	
	/**
	 * 未使用フラグ
	 */
	private int operationalStatus;
	
	/**
	 * 最大容量
	 */
	private String maxStorageCapacity;
	
	/**
	 * 現在容量
	 */
	private String currentStorageCapacity;
	
	/**
	 * 論理削除フラグ
	 */
	private String deleteFlag;

	/**
	 * 更新日
	 */
	private Timestamp updateDate;

	/**
	 * 登録日
	 */
	private Timestamp createDate;
	
	/**
	 * 在庫情報との一対多のリレーション
	 */
	@OneToMany(mappedBy = "centerInfo")
    private List<StockInfo> stockInfo;
}