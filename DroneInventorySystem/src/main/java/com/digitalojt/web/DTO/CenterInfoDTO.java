package com.digitalojt.web.DTO;

import java.util.List;

import com.digitalojt.web.consts.Region;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;

import lombok.Data;

@Data
public class CenterInfoDTO {
	private List<CenterInfo> centerInfoList;
	private List<Region> regions;
	private CenterInfoForm form;
}
