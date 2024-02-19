package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentAreaService implements IRentAreaService {

    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public void deleteRentArea(Long buildingId) {
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findAllByBuildingId(buildingId);
        if (!rentAreaEntities.isEmpty()) {
            for (RentAreaEntity item : rentAreaEntities) {
                rentAreaRepository.delete(item);
            }
        }
    }
    @Override
    public void saveRentArea(BuildingDTO buildingDTO, Long buildingId) {
        deleteRentArea(buildingId);
        String listAreas = buildingDTO.getRentAreas();
        String[] areas = listAreas.split(",");
        for(String item: areas){
            RentAreaEntity rentArea = new RentAreaEntity();
            rentArea.setValue(Long.parseLong(item));
            rentArea.setBuilding(buildingRepository.findById(buildingId).get());
            rentAreaRepository.save(rentArea);
        }
    }


}
