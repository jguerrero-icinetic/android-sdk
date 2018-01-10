package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Customer;
import com.aplazame.sdk.network.model.CustomerDto;
import com.aplazame.sdk.network.model.builder.CustomerDtoBuilder;
import com.aplazame.sdk.network.utils.MapperUtils;

public class CustomerToCustomerDtoMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public CustomerDto transformDomainToDto(Customer customer) {

        AddressToAddressDtoMapper addressToAddressDtoMapper = new AddressToAddressDtoMapper();
        MapperUtils mapperUtils = new MapperUtils();

        CustomerDto customerDto = new CustomerDtoBuilder()
                .withId(customer.getId())
                .withEmail(customer.getEmail())
                .withType(customer.getType())
                .withGender(customer.getGender())
                .withFirstName(customer.getFirstName())
                .withLastName(customer.getLastName())
                .withDateJoined(mapperUtils.dateToString(customer.getDateJoined()))
                .withLastLogin(mapperUtils.dateToString(customer.getLastLogin()))
                .withBirthday(mapperUtils.dateBirthdayToString(customer.getBirthday()))
                .withLanguage(mapperUtils.localLanguageToString(customer.getLanguage()))
                .withAddress(addressToAddressDtoMapper.transformDomainToDto(customer.getAddress()))
                .create();

        return customerDto;
    }
}
