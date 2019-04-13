package be.swsb.effit.challenge

import be.swsb.effit.WebMvcTestConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest
@ContextConfiguration(classes = [WebMvcTestConfiguration::class])
class ChallengeControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var challengeRepositoryMock: ChallengeRepository

    @Test
    fun `GET api challenge should return all Challenges`() {
        val expectedChallenges = listOf(Challenge(name = "Playboy", points = 7, description = "ride down a slope with exposed torso"))

        Mockito.`when`(challengeRepositoryMock.findAll()).thenReturn(expectedChallenges)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/challenge")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(expectedChallenges), true))
    }

    fun toJson(jsonObject: Any): String {
        return objectMapper.writeValueAsString(jsonObject)
    }
}