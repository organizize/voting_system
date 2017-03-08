package voting.utils;

import voting.dto.candidate.CandidateDTO;
import voting.dto.county.CountyDTO;
import voting.model.Candidate;
import voting.model.Party;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 2/27/17.
 */

public class Extractor {

    public static String[] extractIdsFromCandidates(List<CandidateDTO> cands) {
        return cands.stream()
                    .map(c -> c.getId().toString())
                    .collect(Collectors.toList())
                    .toArray(new String[cands.size()]);
    }

    public static String[] extractIdsFromCounties(List<CountyDTO> counties) {
        return counties.stream()
                       .map(c -> c.getId().toString())
                       .collect(Collectors.toList())
                       .toArray(new String[counties.size()]);
    }

    public static List<Candidate> getOrphanCandidates(List<Candidate> cands) {
        return cands.stream()
                    .filter(c -> c.getDistrict() == null)
                    .collect(Collectors.toList());
    }

    public static String[] extractIdsFromOrphanCandidates(Party party) {
        List<Candidate> cands = getOrphanCandidates(party.getCandidates());
        return cands.stream()
                    .map(c -> c.getId().toString())
                    .collect(Collectors.toList())
                    .toArray(new String[cands.size()]);
    }
}
