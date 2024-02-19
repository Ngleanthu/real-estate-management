package com.javaweb.converter;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.request.BuildingSearchRequestDTO;
import com.javaweb.dto.response.BuildingSearchResponse;
import com.javaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convertToDto(BuildingEntity buildingEntity){
        BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
        return result;
    }
    public BuildingSearchResponse convertToBuildingSearchResponse(BuildingEntity buildingEntity){
        BuildingSearchResponse result = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        return result;
    }
    public BuildingEntity convertToEntityFromBuildingSearchResponse(BuildingSearchResponse buildingSearchResponse){
        BuildingEntity result = modelMapper.map(buildingSearchResponse, BuildingEntity.class);
        return result;
    }
    public BuildingEntity convertToEntity(BuildingDTO buildingDTO){
        BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
        return result;
    }

    public BuildingDTO convertToBuildingDTO(BuildingSearchRequestDTO buildingSearchRequestDTO){
        BuildingDTO result = modelMapper.map(buildingSearchRequestDTO, BuildingDTO.class);
        return result;
    }

    public BuildingSearchResponse covertToBuildingSearchResponse(BuildingDTO buildingDTO){
        BuildingSearchResponse result = modelMapper.map(buildingDTO, BuildingSearchResponse.class);
        return result;
    }
    private void setFieldValue(BuildingSearchRequestDTO buildingSearchRequest, String fieldName, Object value) {
        try {
            Field field = BuildingSearchRequestDTO.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            if (value != null) {
                if (String.class.isAssignableFrom(fieldType)) {
                    field.set(buildingSearchRequest,(value).toString());
                } else if (Long.class.isAssignableFrom(fieldType)) {
                    field.set(buildingSearchRequest, Long.parseLong(value.toString()));
                } else if (Integer.class.isAssignableFrom(fieldType)) {
                    field.set(buildingSearchRequest, Integer.parseInt(value.toString()));
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public BuildingSearchRequestDTO covertToBuildingSearchRequest(Map<String, Object> searchConditions, List<String> typeCode){
        BuildingSearchRequestDTO buildingSearchRequest = new BuildingSearchRequestDTO();
        for(Map.Entry<String,Object> item : searchConditions.entrySet()){
            String fieldName = item.getKey();
            Object fieldValue = item.getValue();
            setFieldValue(buildingSearchRequest, fieldName, fieldValue);
        }
        String type = typeCode.stream().map(it -> it.toString()).collect(Collectors.joining(","));
        buildingSearchRequest.setType(type);
        return buildingSearchRequest;
    }

}
