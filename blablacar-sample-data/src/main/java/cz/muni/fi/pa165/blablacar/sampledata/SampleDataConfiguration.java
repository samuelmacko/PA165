package cz.muni.fi.pa165.blablacar.sampledata;

import cz.muni.fi.pa165.blablacar.service.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SampleDataConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(SampleDataConfiguration.class);

    @Autowired
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        logger.debug("sample-data dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
