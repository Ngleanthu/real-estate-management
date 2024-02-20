package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.request.BuildingSearchRequestDTO;
import com.javaweb.dto.response.BuildingSearchResponse;
import com.javaweb.dto.response.ResponseDTO;
import com.javaweb.dto.response.StaffResponseDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<BuildingSearchResponse> getListBuilding(BuildingSearchRequestDTO buildingSearchRequest, Pageable pageable) {
        Page<BuildingEntity> buildings = buildingRepository.findAll(buildingSearchRequest, pageable);
        List<BuildingEntity> buildingEntities = buildings.getContent();
        List<BuildingSearchResponse> result = new ArrayList<>();
        for(BuildingEntity item : buildingEntities){
            BuildingSearchResponse buildingSearchResponse = modelMapper.map(item, BuildingSearchResponse.class);
            List<RentAreaEntity> rentArea = item.getAreas();
            String areas = rentArea.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
            buildingSearchResponse.setRentArea(areas);
            buildingSearchResponse.setAddress(item.getStreet() + "," + item.getWard() + "," + districtCode.getDistrictNameByCode(item.getDistrict()));
            result.add(buildingSearchResponse);
        }
        return result;
    }


    @Override
    public Long saveBuildingHasRentArea(BuildingEntity buildingEntity) {
        buildingRepository.save(buildingEntity);
        buildingRepository.flush();
        return buildingEntity.getId();
    }

    @Override
    public void save(BuildingDTO buildingDTO){
        if(buildingDTO.getBrokerageFee().equals("")){
            buildingDTO.setBrokerageFee(null);
        }
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        if(buildingDTO.getId() != null){
            Long buildingId = buildingDTO.getId();
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId).get();
            buildingEntity.setImage(foundBuilding.getImage());
        }
        saveThumbnail(buildingDTO,buildingEntity);
        if(buildingDTO.getRentAreas() != ""){
            Long buildingId = saveBuildingHasRentArea(buildingEntity);
            rentAreaService.saveRentArea(buildingDTO, buildingId);
        }
        else{
            saveBuilding(buildingEntity);
        }

    }

    @Override
    public int countTotalItems(BuildingSearchRequestDTO buildingSearchRequest, Pageable pageable) {
        return buildingRepository.countTotalItems(buildingSearchRequest, pageable);
    }

    @Override
    public void saveBuilding(BuildingEntity buildingEntity) {
        buildingRepository.save(buildingEntity);
    }

    @Override
    public BuildingDTO getBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getAreas();
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO = buildingConverter.convertToDto(buildingEntity);
        if (rentAreaEntities != null) {
            String areas = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
            buildingDTO.setRentAreas(areas);
        }
        return buildingDTO;
    }

    @Override
    public void deleteBuilding(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        rentAreaService.deleteRentArea(id);
        List<UserEntity> staffs = building.getStaffs();
        building.getStaffs().clear();
        buildingRepository.save(building);
        for(UserEntity item: staffs){
            item.getBuildings().remove(building);
            userRepository.save(item);
        }
        buildingRepository.delete(building);
    }

    @Override
    public StaffResponseDTO listType(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();

        List<ResponseDTO> list = new ArrayList<>();
        StaffResponseDTO results = new StaffResponseDTO();
        Map<String, String> typeList = typeCode.typeCode();
        List<String> listType = new ArrayList<>();
        for (Map.Entry<String, String> entry : typeList.entrySet()){
            listType.add(entry.getKey());
        }
        if(building.getType() == null){
            for(String it : listType){
                ResponseDTO typeDTO = new ResponseDTO();
                typeDTO.setTypeCode(it);
                typeDTO.setFullName(typeList.get(it));
                typeDTO.setChecked("");
                list.add(typeDTO);
            }
        }
        else{
            List<String> type = Arrays.asList(building.getType().split(","));
            for(String it : listType){
                ResponseDTO typeDTO = new ResponseDTO();
                typeDTO.setTypeCode(it);
                typeDTO.setFullName(typeList.get(it));
                if(type.contains(it)){
                    typeDTO.setChecked("checked");
                }
                else{
                    typeDTO.setChecked("");
                }
                list.add(typeDTO);
            }
        }
        results.setData(list);
        results.setMessage("success");
        return results;
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("/Users/qmacstore/Downloads/Học thêm/JavaWeb/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

}
