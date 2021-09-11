package it.univaq.disim.mwt.letsjam.presentation.viewModels;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MusicSheetSearchViewModel {
    
    private String search;

    private List<String> genres;

    private List<String> instruments;

    private String sortBy;

    private String sortDirection = "DESC";

    private Boolean verified;

    private Boolean rearranged;

}
