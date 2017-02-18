package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.DistrictData;
import voting.dto.DistrictRepresentation;
import voting.exception.MultiErrorException;
import voting.model.District;
import voting.service.DistrictService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/10/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/district")
public class DistrictController {

    private DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }


    @GetMapping
    public List<DistrictRepresentation> getDistricts() {
        return districtService.getDistricts().stream().map(d -> new DistrictRepresentation(d)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DistrictRepresentation getDistrict(@PathVariable Long id) {
        District district = districtService.getDistrict(id);
        return new DistrictRepresentation();
    }

    @PostMapping
    public DistrictRepresentation addNewDistrict(@Valid @RequestBody DistrictData districtData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apygardą " + districtData.getName(), result.getAllErrors());
        }
        return new DistrictRepresentation(districtService.addNewDistrict(districtData));
    }

    @DeleteMapping("/{id}")
    public void deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
    }

    @DeleteMapping("/{id}/candidates")
    public void deleteCandidateList(@PathVariable Long id) {
        districtService.deleteCandidateList(id);
    }

    @PostMapping(value = "/{id}/candidates", consumes = "multipart/form-data")
    public DistrictRepresentation setCandidateList(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file)
            throws IOException, CsvException {
        return new DistrictRepresentation(districtService.setCandidateList(id, file));
    }
    
}