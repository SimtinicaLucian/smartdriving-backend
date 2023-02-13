//package ro.softwarelabz.smartdriving;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ro.softwarelabz.smartdriving.car.domain.Car;
//import ro.softwarelabz.smartdriving.setup.BaseIntegrationTest;
//import ro.softwarelabz.smartdriving.setup.JsonUtil;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static ro.softwarelabz.smartdriving.setup.JsonUtil.asJsonString;
//
//
//public class CarIntegrationTest extends BaseIntegrationTest {
//
//    @Test
//    public void create_car() throws Exception {
//
//        var car = Car.builder()
//                .carNumber("12BSD")
//                .insurance(true)
//                .vignette(true)
//                .build();
//
//        var result = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/car")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(asJsonString(car)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car createdCar = JsonUtil.getMvcResult(result, Car.class);
//        assertEquals(car.getCarNumber(), createdCar.getCarNumber());
//        assertNotNull(car.getId());
//    }
//
//    @Test
//    public void create_and_find_by_car_number() throws Exception {
//
//        var car = Car.builder()
//                .carNumber("AG9888STT")
//                .insurance(true)
//                .vignette(false)
//                .build();
//
//        var result = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/car")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(asJsonString(car)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car createdCar = JsonUtil.getMvcResult(result, Car.class);
//
//        assertEquals(car.getCarNumber(), createdCar.getCarNumber());
//        assertEquals(car.isInsurance(), createdCar.isInsurance());
//        assertEquals(car.isVignette(), createdCar.isVignette());
//        assertNotNull(createdCar.getId());
//
//        var secoundResult = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.get("/car/number/{carNumber}", createdCar.getCarNumber())
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car carAfterFinded = JsonUtil.getMvcResult(secoundResult, Car.class);
//
//        assertEquals(createdCar.getCarNumber(), carAfterFinded.getCarNumber());
//        assertEquals(createdCar.isInsurance(), carAfterFinded.isInsurance());
//        assertEquals(createdCar.isVignette(), carAfterFinded.isVignette());
//        assertNotNull(carAfterFinded.getId());
//    }
//
//
//    @Test
//    public void create_and_find_by_id() throws Exception {
//
//        var car = Car.builder()
//                .carNumber("AG98888STT")
//                .insurance(true)
//                .vignette(false)
//                .build();
//
//        var result = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/car")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(asJsonString(car)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car createdCar = JsonUtil.getMvcResult(result, Car.class);
//
//        assertEquals(car.getCarNumber(), createdCar.getCarNumber());
//        assertEquals(car.isInsurance(), createdCar.isInsurance());
//        assertEquals(car.isVignette(), createdCar.isVignette());
//        assertNotNull(createdCar.getId());
//
//        var secoundResult = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.get("/car/id/{id}", createdCar.getId())
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car carAfterFindedId = JsonUtil.getMvcResult(secoundResult, Car.class);
//
//        assertEquals(createdCar.getCarNumber(), carAfterFindedId.getCarNumber());
//        assertEquals(createdCar.isInsurance(), carAfterFindedId.isInsurance());
//        assertEquals(createdCar.isVignette(), carAfterFindedId.isVignette());
//        assertNotNull(carAfterFindedId.getId());
//    }
//
//
//    @Test
//    public void create_and_find_by_insurance() throws Exception {
//
//        var car = Car.builder()
//                .carNumber("AG98888STT")
//                .insurance(true)
//                .vignette(false)
//                .build();
//
//        var result = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/car")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(asJsonString(car)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car createdCar = JsonUtil.getMvcResult(result, Car.class);
//
//        assertEquals(car.getCarNumber(), createdCar.getCarNumber());
//        assertEquals(car.isInsurance(), createdCar.isInsurance());
//        assertEquals(car.isVignette(), createdCar.isVignette());
//        assertNotNull(createdCar.getId());
//
//        var secoundResult = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.get("/car/insurance")
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car carAfterFindedInsurance = JsonUtil.getMvcResult(secoundResult, Car.class);
//
//        assertEquals(createdCar.isInsurance(), carAfterFindedInsurance.isInsurance());
//
//
//
//
//
//
//
//
//
//    }
//
//    @Test
//    public void create_and_update_car() throws Exception {
//        var car = Car.builder()
//                .carNumber("12BSD")
//                .insurance(true)
//                .vignette(true)
//                .build();
//
//        var result = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/car")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(asJsonString(car)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car createdCar = JsonUtil.getMvcResult(result, Car.class);
//
//        Car updatedCar = Car.builder()
//                .id(10L)
//                .carNumber("00AAA")
//                .insurance(false)
//                .build();
//        var updateResult = mockMvc
//                .perform(
//                        MockMvcRequestBuilders.put("/car/{id}", createdCar.getId())
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(asJsonString(updatedCar)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Car carAfterUpdate = JsonUtil.getMvcResult(updateResult, Car.class);
//
//        assertEquals(createdCar.getId(), carAfterUpdate.getId());
//        assertEquals(updatedCar.getCarNumber(), carAfterUpdate.getCarNumber());
//    }
//
//}
