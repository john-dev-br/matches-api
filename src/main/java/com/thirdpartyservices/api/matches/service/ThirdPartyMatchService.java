package com.thirdpartyservices.api.matches.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thirdpartyservices.api.matches.dao.ThirdPartyMatchDao;
import com.thirdpartyservices.api.matches.model.ThirdPartyMatchWrapper;

@Path("/third-party-matches")
public class ThirdPartyMatchService {

	ThirdPartyMatchDao dao = new ThirdPartyMatchDao();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ThirdPartyMatchWrapper getAll() {
		return new ThirdPartyMatchWrapper("match", dao.getAll());
	}
}
