package com.javaweb.service;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.request.BuildingSearchRequestDTO;
import com.javaweb.dto.response.BuildingSearchResponse;
import com.javaweb.dto.response.StaffResponseDTO;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {

    List<BuildingSearchResponse> getListBuilding(BuildingSearchRequestDTO buildingSearchRequestDTO, Pageable pageable);
    Long saveBuildingHasRentArea(BuildingEntity buildingEntity);
    void saveBuilding(BuildingEntity buildingEntity);
    BuildingDTO getBuildingById(Long id);
    void deleteBuilding(Long id);
    StaffResponseDTO listType(Long buildingId);
    void save(BuildingDTO buildingDTO);
    int countTotalItems(BuildingSearchRequestDTO buildingSearchRequest, Pageable pageable);
}
