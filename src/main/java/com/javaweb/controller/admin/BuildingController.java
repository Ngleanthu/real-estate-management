package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.request.BuildingSearchRequestDTO;
import com.javaweb.dto.response.BuildingSearchResponse;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    IUserService userService;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    IBuildingService buildingService;


    @Autowired
    private MessageUtils messageUtil;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequestDTO buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        DisplayTagUtils.of(request, buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponsePage = buildingService.getListBuilding(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        mav.addObject("districts", districtCode.district());
        buildingSearchRequest.setTotalItems(buildingService.countTotalItems(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems())));
        mav.addObject("typeCodes", typeCode.typeCode());
        mav.addObject("staffs", userService.getListStaff());
        buildingSearchRequest.setListResult(buildingSearchResponsePage);
        mav.addObject("buildings", buildingSearchResponsePage);
        initMessageResponse(mav, request);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }


    @GetMapping(value = "/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit")BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", districtCode.district());
        mav.addObject("typeCodes", typeCode.typeCode());
        return mav;
    }
    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView updateBuilding(@PathVariable("id")Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO model = buildingService.getBuildingById(id);
        mav.addObject("buildingEdit", model);
        mav.addObject("districts", districtCode.district());
        mav.addObject("typeCodes", typeCode.typeCode());
        return mav;
    }

    @GetMapping(value = "/admin/building-list-{id}")
    public ModelAndView deleteBuilding(@PathVariable("id")Long id){
        ModelAndView mav = new ModelAndView("admin/building/list");
        try {
            buildingService.deleteBuilding(id);
            mav.addObject("message", "Xóa toà nhà thành công");
        } catch (Exception e) {
            mav.addObject("error", "Lỗi khi xóa toà nhà: " + e.getMessage());
        }
        return mav;
    }

}
