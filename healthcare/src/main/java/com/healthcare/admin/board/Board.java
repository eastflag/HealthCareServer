package com.healthcare.admin.board;

import java.io.Serializable;

public class Board implements Serializable{

	private static final long serialVersionUID = 2147193102426195881L;
	private int board_id;
	private String board_type;
	private String title;
	private String content;
	private String name;
	private String email;
	private String tel;
	private String use_yn;
	private String end_date;
	private String remark;
	private String reg_date;
	private String reg_id;
	private String upd_date;
	private String upd_id;
	
	public Board(){}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}

	public String getUpd_id() {
		return upd_id;
	}

	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
	
	private String convertContent;

	public String getConvertContent() {
		return convertContent;
	}

	public void setConvertContent(String convertContent) {
		this.convertContent = convertContent;
	}
	
	
	@Override
	public String toString() {
		return "BoardInfo [board_id=" + board_id + ", board_type=" + board_type
				+ ", title=" + title + ", content=" + content + ", name=" + name
				+ ", email=" + email + ", tel=" + tel + ", use_yn=" + use_yn
				+ ", end_date=" + end_date + ", remark=" + remark 
				+ ", reg_date=" + reg_date + ", reg_id=" + reg_id 
				+ ", upd_date=" + upd_date + ", upd_id=" + upd_id + "]";
	}
	
}
