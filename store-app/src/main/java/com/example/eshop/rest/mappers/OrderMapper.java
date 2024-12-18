package com.example.eshop.rest.mappers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.db.entities.Address;
import com.example.eshop.db.entities.Order;
import com.example.eshop.rest.model.AddressDTO;
import com.example.eshop.rest.model.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends AbstractMapper<Order, OrderDTO> {
    // Map Order entity to OrderDTO
//    @Mapping(source = "user.id", target = "userId") // Map User ID
//    @Mapping(source = "deliveryAddress", target = "deliveryAddress", qualifiedByName = "mapAddress")
//    OrderDTO toDTO(Order order);

    // Map OrderDTO back to Order entity
//    @Mapping(target = "user", ignore = true) // Avoid circular reference
//    @Mapping(target = "deliveryAddress", source = "deliveryAddress", qualifiedByName = "mapToAddressEntity")
//    Order toEntity(OrderDTO orderDTO);

    // Helper method to map Address to AddressDTO
    @Named("mapAddress")
    default AddressDTO mapAddress(Address address) {
        if (address == null) return null;

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

    // Helper method to map AddressDTO back to Address entity
    @Named("mapToAddressEntity")
    default Address mapToAddressEntity(AddressDTO addressDTO) {
        if (addressDTO == null) return null;

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

}