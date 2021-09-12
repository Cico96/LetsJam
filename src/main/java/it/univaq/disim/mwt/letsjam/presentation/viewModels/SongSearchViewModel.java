package it.univaq.disim.mwt.letsjam.presentation.viewModels;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SongSearchViewModel{

    private String search;

    private List<String> genres;

    private String sortBy;

    private String sortDirection = "DESC";

    private String albumType;

    private Boolean explicit;

    private Boolean lyrics;

    private int totalPages;

    private int pageNumber;

}