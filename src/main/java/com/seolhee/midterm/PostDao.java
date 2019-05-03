package com.seolhee.midterm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {

	// 글쓰기
	static final String ADD_POST = "insert post(userid, name, content) values(?,?,?)";

	// 목록보기
	static final String LIST_POSTS = "select postId,userId,name,content,sweet,cdate from post order by postId desc limit ?,?";

	// 좋아요
	static final String LIKE_POST = "update post set sweet = sweet+1 where postId=?";

	// 글조회
	static final String GET_POST = "select postId,userId,name,content,sweet,cdate from post where postId=?";
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Post> postRowMapper = new BeanPropertyRowMapper<>(Post.class);

	public int addPost(Post post) {
		return jdbcTemplate.update(ADD_POST, post.getUserId(), post.getName(),
				post.getContent());
	}

	public List<Post> listPosts(int offset, int count) {
		return jdbcTemplate.query(LIST_POSTS, postRowMapper, offset, count);
	}

	public int likePost(String postId) {
		return jdbcTemplate.update(LIKE_POST, postId);
	}
	
	public Post getPost(String postId) {
		return jdbcTemplate.queryForObject(GET_POST, postRowMapper, postId);
	}
}