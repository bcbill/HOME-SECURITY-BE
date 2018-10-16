package com.enrico.dg.home.security.rest.web.controller;

import com.enrico.dg.home.security.entity.constant.ApiPath;
import com.enrico.dg.home.security.entity.constant.enums.ResponseCode;
import com.enrico.dg.home.security.libraries.utility.BaseResponseHelper;
import com.enrico.dg.home.security.rest.web.model.request.MandatoryRequest;
import com.enrico.dg.home.security.rest.web.model.response.BaseResponse;
import com.enrico.dg.home.security.service.api.AuthService;
import com.enrico.dg.home.security.service.api.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPath.BASE_PATH + ApiPath.IMAGE)
public class ImageController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

  @Autowired
  private AuthService authService;

  @Autowired
  private ImageService imageService;

  @GetMapping
  private BaseResponse<List<String>> getImage(
          @ApiIgnore @Valid @ModelAttribute MandatoryRequest mandatoryRequest,
          @ApiParam(value = "yyyy/MM") @RequestParam @DateTimeFormat(pattern="yyyy/MM") Date date
  ) {

    authService.isTokenValid(mandatoryRequest.getAccessToken());

    List<String> url = imageService.getImages(date);

    return BaseResponseHelper.constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
            null, url);
  }

//  @DeleteMapping(ApiPath.DELETE_IMAGE_CLOUDINARY + ApiPath.ID)
//  private BaseResponse<String> deleteImage(
//          @ApiIgnore @Valid @ModelAttribute MandatoryRequest mandatoryRequest,
//          @PathVariable String id
//  ) {
//
//    authService.isTokenValid(mandatoryRequest.getAccessToken());
//
//    imageService.deleteImage(id);
//
//    return BaseResponseHelper.constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
//            null, "Successfully Delete Image");
//  }

  @PostMapping
  public BaseResponse<Map<String, String>> uploadImageToCloudinary(
          @RequestParam(value = "capturedImage") MultipartFile aFile
  ) {

    Map<String, String> uploadResult = imageService.uploadImage(aFile);

    return BaseResponseHelper.constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
            null, uploadResult);
  }

  @ModelAttribute
  public MandatoryRequest getMandatoryParameter(HttpServletRequest request) {
    return (MandatoryRequest) request.getAttribute("mandatory");
  }
}