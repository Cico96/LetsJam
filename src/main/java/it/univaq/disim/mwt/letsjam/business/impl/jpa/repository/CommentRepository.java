package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.univaq.disim.mwt.letsjam.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	Comment findCommentById(Long id);

	@Query(value="SELECT c FROM Comment c WHERE c.parentComment.id IS NULL AND c.musicSheet.id = :musicSheetId")           
	List<Comment> getMusicSheetComments(@Param("musicSheetId")Long id);

	@Query(value="SELECT c FROM Comment c WHERE c.parentComment.id = :parentId")
	List<Comment> getReplies(@Param("parentId")Long id);
}
