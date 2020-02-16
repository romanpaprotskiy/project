package com.unfu.project.service.users.payload.request;

import com.unfu.project.service.validation.NoneSpecialSymbols;
import com.unfu.project.service.validation.Phone;
import lombok.Data;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EditProfileRequest implements Serializable {

    private static final long serialVersionUID = -4479816999571078749L;

    @Past(message = "Birth date can not be in the future")
    private LocalDate birthDate;

    @Phone
    private String phone;

    @NoneSpecialSymbols
    private String skypeId;
}
