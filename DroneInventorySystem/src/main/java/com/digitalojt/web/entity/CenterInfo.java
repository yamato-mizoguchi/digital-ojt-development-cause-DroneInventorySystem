package com.digitalojt.web.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int centerId;
	
	/**
	 * センター名
	 */
	private String centerName;

	/**
	 * 郵便番号
	 */
	private String postCode;
	
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
	private Integer maxStorageCapacity;
	
	/**
	 * 現在容量
	 */
	private Integer currentStorageCapacity;
	
	/**
	 * 備考
	 */
	private String notes;
	
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
	
	@PrePersist
    public void prePersist() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        this.createDate = currentTimestamp;  // 新規作成時に作成日を設定
        this.updateDate = currentTimestamp;  // 新規作成時に更新日も設定
    }
	
    @PreUpdate
    public void preUpdate() {
        // 更新時に、updatedAtを現在の時刻に設定
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }
}