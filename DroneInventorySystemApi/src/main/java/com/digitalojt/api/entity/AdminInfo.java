package com.digitalojt.api.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 管理者情報Entity
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@Getter
@Setter
@Entity
public class AdminInfo {

	/**
	 * 管理者ID
	 */
	@Id
	private String adminId;
	
	/**
	 * 管理者名
	 */
	private String adminName;
	
	/**
	 * メール
	 */
	private String mail;
	
	/**
	 * 電話番号
	 */
	private String phoneNumber;
	
	/**
	 * パスワード
	 */
	private String password;
	
	/**
	 * 削除フラグ
	 */
	private String deleteFlag;
	
	/**
	 * 作成日
	 */
	private Timestamp create_date;
	
	/**
	 * 更新日
	 */
	private Timestamp update_date;
}
