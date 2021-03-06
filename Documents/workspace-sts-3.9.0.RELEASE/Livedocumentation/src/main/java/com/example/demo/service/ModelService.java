package com.example.demo.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectionDto;
import com.example.demo.model.Model;
import com.example.demo.model.Property;
import com.example.demo.models.InputType;
import com.example.demo.utils.ProjectionAnnotationComponentProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModelService {
	
	@Value("${spring.data.rest.base-path}")
	private String basePath;

	private @Inject RepositoryRestMvcConfiguration configuration;

	private static HashMap<String, Model> modelsMap;
	private static HashMap<String, ArrayList<ProjectionDto>> projectionsMap;

	public void searchIn(String pakageName) {
		getAllProjections(pakageName);
		HashMap<String,Model> ss=getModels();
		
		System.out.println("okkkkkkkkkkkkkkkkkkkkkk"+ss);
	}// End searchAllEntites()

	private void getAllProjections(String pakageName) {
		projectionsMap = new HashMap<>();

		ClassPathScanningCandidateComponentProvider projectionScanner = new ProjectionAnnotationComponentProvider();
		projectionScanner.findCandidateComponents(pakageName).forEach(bd -> {
			try {
				ProjectionDto projectionDTO = new ProjectionDto();

				Class<?> entityClass = Class.forName(bd.getBeanClassName());

				Projection projection = entityClass.getAnnotation(Projection.class);

				projectionDTO.setName(projection.name());

				Method[] methods = entityClass.getMethods();
				Property[] properties = new Property[entityClass.getMethods().length];

				for (int i = 0; i < properties.length; i++) {
					Property property = new Property();

					String name = methods[i].getName().substring(3);

					name = name.substring(0, 1).toLowerCase() + name.substring(1);
					property.setName(name);
					
					properties[i] = property;
				}
				projectionDTO.setProperties(properties);
				String key = projection.types()[0].getName();
				ArrayList<ProjectionDto> projectionDTOs = projectionsMap.get(key);

				if (projectionDTOs == null) {
					ArrayList<ProjectionDto> list = new ArrayList<>();
					list.add(projectionDTO);
					projectionsMap.put(key, list);
				} else {
					projectionDTOs.add(projectionDTO);
					projectionsMap.put(key, projectionDTOs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}// End getAllProjections ()

	public HashMap<String, Model> getModels() {
		if (modelsMap == null)
			modelsMap = new HashMap<>();
		else
			return modelsMap;

		configuration.resourceMappings().forEach(c -> {
			String url = basePath.substring(0, basePath.length() - 1) + c.getPath().toString();
			String className = c.getDomainType().getName();

			if (className != null && c.isExported()) {

				Model m = new Model();
				try {
					Class<?> entityClass = Class.forName(className);
					m.setName(entityClass.getSimpleName());
					m.setPackageName(entityClass.getPackage().getName());
					m.setEndPoint(url);
					m.setProperties(getPropertiesByClass(entityClass, m));
					m.setProjections(getProjectionsByClass(entityClass));

				} catch (ClassNotFoundException e) {
					log.warn("Error getting entity class");
				}
				modelsMap.put(m.getName(), m);
			}
		});
		return modelsMap;
	}// End getRestEndPoints()

	private List<ProjectionDto> getProjectionsByClass(Class<?> entityClass) {
		return projectionsMap.get(entityClass.getName());
	}// End getProjectionsByClass ()

	private List<Property> getPropertiesByClass(Class<?> entityClass, Model m) {
		ArrayList<Property> properties = new ArrayList<>();
		// Check for inheritance
		Inheritance inheritance = entityClass.getAnnotation(Inheritance.class);
		if (inheritance != null) {
			for (Field field : entityClass.getSuperclass().getDeclaredFields()) {
				Property p = covertFieldToProperty(field, m);
				if (p != null) {
					properties.add(p);
				}
			}
		}
		// Check for local properties
		for (Field field : entityClass.getDeclaredFields()) {
			Property p = covertFieldToProperty(field, m);
			if (p != null) {
				properties.add(p);
			}
		}
		return properties;
	}// End getPropertiesByClass

	private Property covertFieldToProperty(Field field, Model m) {
		// Ignore static field serialVersionUID
		if (!field.getName().equals("serialVersionUID")) {

			// if Transient ignore the property
			// Transient transiented =
			// field.getAnnotation(Transient.class);
			JsonIgnore jsonIgnore = field.getAnnotation(JsonIgnore.class);
			if (jsonIgnore != null)
				return null;

			// Create new Property
			Property property = new Property();

			// Set Name
			property.setName(field.getName());
			// Set Type this will be changed latter on some fields

			// Set Default type to text
			property.setType(InputType.TEXT.toString());

			property.setReference(field.getType().getSimpleName());

			// Long,Integer and double to be type numbwe
			switch (field.getType().getSimpleName().toLowerCase()) {
			case "integer":
			case "double":
			case "long":
				property.setType("number");
				break;
			case "boolean":
				property.setType("boolean");
			}

			// Read annotations
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				if (column.unique() == true)
					property.setUnique(true);
			}

			// Enum
			Enumerated enumerated = field.getAnnotation(Enumerated.class);
			if (enumerated != null) {
				property.setType("enum");
				String[] enumList = new String[field.getType().getEnumConstants().length];
				for (int i = 0; i < field.getType().getEnumConstants().length; i++) {
					enumList[i] = field.getType().getEnumConstants()[i].toString();
				}
				property.setEnums(enumList);
			}

			// Relations
			// One to One
			OneToOne oneToOne = field.getAnnotation(OneToOne.class);
			if (oneToOne != null) {
				property.setType(Object.class.getSimpleName().toLowerCase());
				property.setRelation(OneToOne.class.getSimpleName().toLowerCase());
			}

			ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
			if (manyToOne != null) {
				property.setType(Object.class.getSimpleName().toLowerCase());
				property.setRelation(ManyToOne.class.getSimpleName().toLowerCase());
				property.setReference(field.getType().getSimpleName());
			}

			OneToMany oneToMany = field.getAnnotation(OneToMany.class);
			if (oneToMany != null) {
				property.setRelation(OneToMany.class.getSimpleName().toLowerCase());
				property.setType(field.getType().getSimpleName().toLowerCase());
				ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
				String className = parameterizedType.getActualTypeArguments()[0].getTypeName();
				try {
					property.setReference(Class.forName(className).getSimpleName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				property.setIggnoreOnRead(true);
			}

			ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
			if (manyToMany != null) {
				property.setRelation(ManyToMany.class.getSimpleName().toLowerCase());
				property.setType(field.getType().getSimpleName().toLowerCase());
				ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
				String className = parameterizedType.getActualTypeArguments()[0].getTypeName();
				try {
					property.setReference(Class.forName(className).getSimpleName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				property.setIggnoreOnRead(true);
			}

			// Date/Time related
			Temporal temporal = field.getAnnotation(Temporal.class);
			if (temporal != null) {
				property.setType(temporal.value().toString().toLowerCase());
			}

			// Size validation
			Size size = field.getAnnotation(Size.class);
			if (size != null) {
				property.setMax(size.max());
				property.setMin(size.min());
			}

			// Email
			Email email = field.getAnnotation(Email.class);
			if (email != null) {
				property.setType(InputType.EMAIL.toString());
			}

			// Check if required
			NotNull notNull = field.getAnnotation(NotNull.class);
			if (notNull != null) {
				property.setRequired(true);
			}

			
			return property;
		} else {
			return null;
		}
	}// End ()

	public Model getModelByName(String name) {
		if (modelsMap == null) {
			getModels();
		}
		return modelsMap.get(name);
	}// End getModelByName()
}
