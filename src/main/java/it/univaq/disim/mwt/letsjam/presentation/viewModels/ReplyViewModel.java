package it.univaq.disim.mwt.letsjam.presentation.viewModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyViewModel {
   
    private String content;

    private Long id;

    private String firstName;

    private String lastName;

    private String userAvatar;
}
