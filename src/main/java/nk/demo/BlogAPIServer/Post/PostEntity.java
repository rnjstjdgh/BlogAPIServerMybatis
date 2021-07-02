package nk.demo.BlogAPIServer.Post;


import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostEntity {

	private int 			postId;			//개시글 넘버(primary key)
	private String 			title;			//제목
	private int 			userId;			//작성자의 아이디(foreign key)
	private String 			contents;		//내용
	private LocalDateTime 	regDate;		//등록일자
	
	@Builder
	public PostEntity(int postId, String title, int userId, String contents, LocalDateTime regDate) {
		this.postId = postId;
		this.title = title;
		this.userId = userId;
		this.contents = contents;
		this.regDate = regDate;
	}
}
