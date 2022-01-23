package com.tenniscourts.guests;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Guest map(GuestDTO guest);

    @InheritInverseConfiguration
    GuestDTO map(Guest guest);

}
