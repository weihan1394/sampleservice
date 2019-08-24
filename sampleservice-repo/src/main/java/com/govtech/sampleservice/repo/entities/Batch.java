package com.govtech.sampleservice.repo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "batch")
public class Batch implements EntityInterface {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonIgnore
  @Column(columnDefinition = "DATETIME")
  @NotNull
  private LocalDateTime entered;

  @Column(columnDefinition = "text")
  private String remarks;

  @Override
  public String toString() {
    return String.format("%s, %s", entered, remarks);
  }
}
