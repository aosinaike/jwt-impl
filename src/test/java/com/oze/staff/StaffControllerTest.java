package com.oze.staff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oze.OzeTestApiApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = OzeTestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaffControllerTest {

    @InjectMocks
    private StaffController staffController;

    private MockMvc mockMvc;

    @MockBean
    StaffService staffService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(staffController).build();
    }

    @Test
    public void testAddStaffProfile() throws Exception{
        CreateStaffRequestDTO requestDTO = new CreateStaffRequestDTO();
        requestDTO.setName("Abiodun");

        assertThat(staffService).isNotNull();

        Staff staff = new Staff();
        staff.setId(1L);
        staff.setName("Abiodun");
        staff.setUuid(UUID.randomUUID().toString());
        staff.setRegistration_date(LocalDateTime.now());

        when(staffService.addStaffProfile(any(CreateStaffRequestDTO.class))).thenReturn(staff);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/staff/new")
                        .content(asJsonString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
