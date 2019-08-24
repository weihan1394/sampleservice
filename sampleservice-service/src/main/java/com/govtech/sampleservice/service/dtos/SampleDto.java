package com.govtech.sampleservice.service.dtos;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleDto extends AbstractDto {

  @NotNull private int id;

  @NotNull private String remarks;
}
