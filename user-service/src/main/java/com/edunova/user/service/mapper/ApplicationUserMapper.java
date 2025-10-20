package com.edunova.user.service.mapper;

import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.ApplicationUserResponse;
import com.edunova.user.service.data.vo.ApplicationUserVO;
import com.edunova.user.service.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ApplicationUserMapper {
    
    public abstract ApplicationUser toEntity(CreateUserRequest request);
    public abstract ApplicationUserVO toVO(ApplicationUser entity);
    public abstract ApplicationUserResponse toResponse(ApplicationUser entity);
    public abstract ApplicationUserResponse toResponse(ApplicationUserVO vo);
    
    public  abstract List<ApplicationUserVO> toVOList(List<ApplicationUser> entities);
    public abstract List<ApplicationUserResponse> toResponseList(List<ApplicationUser> entities);
    public abstract List<ApplicationUserResponse> voListToResponseList(List<ApplicationUserVO> vos);

    public abstract void updateEntity(@MappingTarget ApplicationUser entity, UpdateUserRequest request);
}