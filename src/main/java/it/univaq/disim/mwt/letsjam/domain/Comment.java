package it.univaq.disim.mwt.letsjam.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Autowired;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.CommentRepository;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commenti")
@NoArgsConstructor
@Getter
@Setter
public class Comment extends AbstractPersistableEntity{

	@Size(min = 3, max = 500, groups = {OnCreate.class, OnUpdate.class, Default.class})
	@NotNull
	private String content;
	
	@OneToOne
	private Comment parentComment;
	
	@OneToOne
	private MusicSheet musicSheet;
	
	@OneToOne
	private User user;
	
	@Formula(value="(SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = id)")
	private int replies;
}
