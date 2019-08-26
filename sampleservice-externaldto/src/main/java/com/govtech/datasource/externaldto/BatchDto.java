package com.govtech.datasource.externaldto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class BatchDto extends AbstractCditDto {
  private static final long serialVersionUID = 1L;

  private String nric;

  private String name;
}
