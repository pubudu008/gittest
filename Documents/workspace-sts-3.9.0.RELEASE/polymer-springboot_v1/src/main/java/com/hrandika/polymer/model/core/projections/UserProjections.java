package com.hrandika.polymer.model.core.projections;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.hrandika.polymer.model.core.Module;
import com.hrandika.polymer.model.core.SubModule;
import com.hrandika.polymer.model.core.User;


@Projection(name = "basic", types = User.class)
public interface UserProjections {

	String getUserName();

	Boolean getActivated();

	String getEmail();

}

@Projection(name = "dataTable", types = User.class)
interface UserDataTableProjection {

	String getUserName();

	Boolean getActivated();

	String getEmail();

}


@Projection(name = "advance", types = User.class)
interface UserMinimalProjection extends UserProjections {

	String getFirstName();

	String getLastNmae();

	Set<Module> getApplications();

	Set<SubModule> getModules();
}
