package me.soungho.BlogAPIServer.mvc.domain;


import java.util.Date;

import lombok.Data;

@Data
public class Post {

	private int postSeq;		//개시글 넘버
	private String title;		//제목
	private String user;		//작성자
	private String contents;	//내용
	private Date regDate;		//등록일자
	

}
