package com.rmacnc.demo.file;

import org.springframework.batch.item.ItemProcessor;

import com.rmacnc.demo.topics.Topic;

public class TopicItemProcessor implements ItemProcessor<Topic, Topic> {

	@Override
	public Topic process(Topic topic) throws Exception {
		
		return topic;
	}

}
