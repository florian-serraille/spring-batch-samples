package com.labs.springbatchsamples.job.helloworld;

import com.labs.springbatchsamples.job.SimpleJobEnum;
import com.labs.springbatchsamples.job.StepEnum;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("helloWorldConfiguration")
public class JobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get(SimpleJobEnum.HELLO_WORLD.getJobName())
				.start(helloStep())
				.build();
	}

	private Step helloStep() {
		return stepBuilderFactory.get(StepEnum.HELLO_WORLD.getStepName())
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello World!");
					return RepeatStatus.FINISHED;
				}).build();
	}
}