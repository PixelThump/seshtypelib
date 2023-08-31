package com.pixelthump.seshtypelib.config;
import com.pixelthump.seshtypelib.repository.CommandRespository;
import com.pixelthump.seshtypelib.rest.PingResource;
import com.pixelthump.seshtypelib.rest.SeshResource;
import com.pixelthump.seshtypelib.service.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "pixelthump.seshtype")
public class SpringBootSeshtypelibAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Bean
    @ConditionalOnMissingBean
    public BroadcastService broadcastService(RestTemplate restTemplate, StateService stateService){

        return new BroadcastServiceRestImpl(restTemplate,stateService);
    }

    @Bean
    @ConditionalOnMissingBean
    public SeshService seshService(StateFactory stateFactory, StateService stateService, CommandRespository commandRespository, PlayerService playerService, GameLogicService gameLogicService, BroadcastService broadcastService){

        return new SeshServiceImpl(stateFactory,stateService,commandRespository,playerService, gameLogicService, broadcastService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PingResource pingResource(){

        return new PingResource();
    }

    @Bean
    @ConditionalOnMissingBean
    public SeshResource seshResource(SeshService seshService, ModelMapper modelMapper){

        return new SeshResource(seshService, modelMapper);
    }

    @Value("${server.servlet.context-path}")
    private String url;

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(url));
    }
}
