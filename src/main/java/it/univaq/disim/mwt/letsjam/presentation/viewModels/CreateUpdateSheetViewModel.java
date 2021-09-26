package it.univaq.disim.mwt.letsjam.presentation.viewModels;

import it.univaq.disim.mwt.letsjam.domain.Song;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateUpdateSheetViewModel {

    String author;

    String brano;

    String title;

    List<Song> songs;

    //content
}
