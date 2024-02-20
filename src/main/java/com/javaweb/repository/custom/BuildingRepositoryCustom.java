package com.javaweb.repository.custom;

import com.javaweb.dto.request.BuildingSearchRequestDTO;
//import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    Page<BuildingEntity> findAll(BuildingSearchRequestDTO buildingDTO, Pageable pageable);
    void saveBuilding(BuildingEntity buildingEntity);

    int countTotalItems(BuildingSearchRequestDTO buildingDTO, Pageable pageable);
}
