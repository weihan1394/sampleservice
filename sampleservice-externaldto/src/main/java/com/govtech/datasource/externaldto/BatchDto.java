package com.govtech.datasource.externaldto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
public class BatchDto extends AbstractPLPDto {
  private static final long serialVersionUID = 1L;

  private String nric;

  private String name;
}
