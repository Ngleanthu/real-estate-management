package com.javaweb.api.admin;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.request.BuildingSearchRequestDTO;
import com.javaweb.dto.response.BuildingSearchResponse;
import com.javaweb.dto.response.StaffResponseDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IRentAreaService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    IUserService userService;
    @Autowired
    IBuildingService buildingService;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    IRentAreaService rentAreaService;


    @GetMapping("/{buildingId}/staffs")
    public StaffResponseDTO loadStaff(@PathVariable("buildingId") Long id) {
        StaffResponseDTO result = new StaffResponseDTO();
        result = userService.listStaff(id);
        return result;
    }


    @GetMapping("/{buildingId}/typeCodes")
    public StaffResponseDTO loadTypeCode(@PathVariable("buildingId") Long id) {
        StaffResponseDTO result = new StaffResponseDTO();
        result = buildingService.listType(id);
        return result;
    }

    @PostMapping
    public void createAndUpdate(@RequestBody BuildingDTO buildingDTO){
        buildingService.save(buildingDTO);
    }


    @DeleteMapping(value = "/elimination/{id}")
    public void deleteBuilding(@PathVariable("id") Long id){
        rentAreaService.deleteRentArea(id);
        buildingService.deleteBuilding(id);
    }


    @DeleteMapping(value = "/eliminationAll")
    public void deleteBuilding(@RequestBody Long[] ids){
        for(Long id : ids){
            buildingService.deleteBuilding(id);
        }
    }
}
