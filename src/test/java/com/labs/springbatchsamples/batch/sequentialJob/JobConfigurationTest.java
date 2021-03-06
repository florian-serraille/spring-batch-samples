package com.labs.springbatchsamples.batch.sequentialJob;

import com.labs.springbatchsamples.batch.BatchTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import(JobConfiguration.class)
@ContextConfiguration(classes = {BatchTestConfiguration.class})
public class JobConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testInjections() {
        assertThat(jobLauncherTestUtils).isNotNull();
    }

    @Test
    public void sequentialJob() throws Exception {
        assertThat(jobLauncherTestUtils.launchJob().getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
    }
}