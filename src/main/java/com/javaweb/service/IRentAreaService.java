package com.javaweb.service;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;

public interface IRentAreaService {

    void saveRentArea(BuildingDTO buildingDTO, Long buildingId);
    void deleteRentArea(Long buildingId);
}
