//package com.rmacnc.demo.file;
//
//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import com.rmacnc.demo.topics.Topic;
//import com.thoughtworks.xstream.mapper.DefaultMapper;
//
//
//
//
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfiguration {
//	
//	@Autowired
//	public JobBuilderFactory jobBuilderFactory;
//	
//	@Autowired
//	public StepBuilderFactory stepBuilderFactory;
//	
//	@Autowired
//	public DataSource dataSource;
//	
//	@Bean
//	public DataSource dataSource() {
//	
//		final DriverManagerDataSource dataSource= new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost/test123");
//		dataSource.setUsername("root");
//		dataSource.setPassword("password");
//		return dataSource;
//	}
//	
//	@Bean
//	public FlatFileItemReader<Topic> reader(){
//		
//		FlatFileItemReader<Topic> reader =new FlatFileItemReader<Topic>();
//		reader.setResource(new ClassPathResource("/home/asitha/Documents/name.csv"));
//		reader.setLineMapper(new DefaultLineMapper<Topic>() {{
//			setLineTokenizer(new DelimitedLineTokenizer() {{
//				setNames(new String[] {"id"/*,"name","description"*/});
//			}});
//			setFieldSetMapper(new BeanWrapperFieldSetMapper<Topic>() {{
//				setTargetType(Topic.class);
//			}});
//			
//		}});
//		return reader;
//	}
//	
//	@Bean
//	public TopicItemProcessor processor() {
//		
//		return new TopicItemProcessor();
//	}
//	
//	@Bean
//	public JdbcBatchItemWriter<Topic> writer(){
//	 JdbcBatchItemWriter<Topic>	writer =new JdbcBatchItemWriter<Topic>();
//	 writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Topic>());
//	 writer.setSql("INSERT INTO topic (id) VALUES (:id)");
//	 writer.setDataSource(dataSource);
//	 return writer;
//	}
//	@Bean
//	public Step step1() {
//		return stepBuilderFactory.get("step1").<Topic , Topic> chunk(3).reader(reader())
//				.processor(processor())
//				.writer(writer()).build();
//	}
//	@Bean
//	public Job importTopicJob() {
//		return jobBuilderFactory.get("importTopicJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
//	}
//}
