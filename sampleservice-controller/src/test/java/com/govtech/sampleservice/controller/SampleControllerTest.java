package com.govtech.sampleservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.govtech.sampleservice.SampleserviceApplication;
import com.govtech.sampleservice.service.dtos.SampleDto;
import com.govtech.sampleservice.service.services.SampleService;
import com.govtech.sampleservice.service.services.SampleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {
      SampleserviceApplication.class,
      SampleServiceImpl.class,
    })
@EnableWebMvc
@WebMvcTest(SampleController.class)
public class SampleControllerTest {
  @Autowired private MockMvc mvc;
  @MockBean private SampleService sampleService;

  @Before
  public void setUp() {
    SampleDto sampleDto = new SampleDto();
    sampleDto.setId(1);
    sampleDto.setRemarks("This is a sample remarks");
    when(sampleService.getSampleDto()).thenReturn(sampleDto);
  }

  @Test
  public void sampleApiTest() throws Exception {
    mvc.perform(get("/sample/sampleApi")).andExpect(status().isOk());
  }
}
