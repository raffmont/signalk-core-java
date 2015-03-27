package nz.co.fortytwo.signalk.handler;

import static nz.co.fortytwo.signalk.util.SignalKConstants.nav;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_position_latitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_position_longitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.self;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels_dot_self_dot;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nz.co.fortytwo.signalk.model.SignalKModel;
import nz.co.fortytwo.signalk.model.impl.SignalKModelFactory;
import nz.co.fortytwo.signalk.util.JsonConstants;
import nz.co.fortytwo.signalk.util.Util;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RestApiHandlerTest {

	private static Logger logger = Logger.getLogger(RestApiHandlerTest.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldGetSelfPosition() throws IOException {
		RestApiHandler api = new RestApiHandler();
		SignalKModel model = SignalKModelFactory.getInstance();
		model.getData().clear();
		model = Util.populateModel(model, new File("src/test/resources/samples/basicModel.txt"));
		model = Util.populateModel(model, new File("src/test/resources/samples/otherModel.txt"));
		
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		when(mockedRequest.getPathInfo()).thenReturn(JsonConstants.SIGNALK_API+vessels+"/"+self+"/"+nav+"/position");

		HttpServletResponse  mockedResponse = Mockito.mock(HttpServletResponse.class);
		SignalKModel reply = api.processGet(mockedRequest, mockedResponse, model);
		logger.debug("Repy="+reply);
		assertEquals(-41.2936935424,reply.get(vessels_dot_self_dot+nav_position_latitude));
		assertEquals(173.2470855712,reply.get(vessels_dot_self_dot+nav_position_longitude));
		assertTrue(reply.getData().size()==3); 
		verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
		verify(mockedResponse).setContentType("application/json");
		
		//assertEquals(200, mockedResponse.getStatus());
	}
	
	@Test
	public void shouldGetOtherPosition() throws IOException {
		RestApiHandler api = new RestApiHandler();
		SignalKModel model = SignalKModelFactory.getInstance();
		model.getData().clear();
		model = Util.populateModel(model, new File("src/test/resources/samples/basicModel.txt"));
		model = Util.populateModel(model, new File("src/test/resources/samples/otherModel.txt"));
		
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		when(mockedRequest.getPathInfo()).thenReturn(JsonConstants.SIGNALK_API+vessels+"/other/"+nav+"/position");

		HttpServletResponse  mockedResponse = Mockito.mock(HttpServletResponse.class);
		SignalKModel reply = api.processGet(mockedRequest, mockedResponse, model);
		logger.debug("Repy="+reply);
		assertEquals(-41.2936935424,reply.get(vessels+".other."+nav_position_latitude));
		assertEquals(173.2470855712,reply.get(vessels+".other."+nav_position_longitude));
		assertTrue(reply.getData().size()==3); 
		verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
		verify(mockedResponse).setContentType("application/json");
	}
	
	@Test
	public void shouldGetNotFound() throws IOException {
		RestApiHandler api = new RestApiHandler();
		SignalKModel model = SignalKModelFactory.getInstance();
		model.getData().clear();
		model = Util.populateModel(model, new File("src/test/resources/samples/basicModel.txt"));
		model = Util.populateModel(model, new File("src/test/resources/samples/otherModel.txt"));
		
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		when(mockedRequest.getPathInfo()).thenReturn(JsonConstants.SIGNALK_API+vessels+"/"+self+"/"+nav+"/not_here");

		HttpServletResponse  mockedResponse = Mockito.mock(HttpServletResponse.class);
		SignalKModel reply = api.processGet(mockedRequest, mockedResponse, model);
		logger.debug("Repy="+reply);
		//assertEquals(-41.2936935424,reply.get(vessels_dot_self_dot+nav_position_latitude));
		//assertEquals(173.2470855712,reply.get(vessels_dot_self_dot+nav_position_longitude));
		assertNull(reply); 
		verify(mockedResponse).setStatus(HttpServletResponse.SC_NOT_FOUND);
		//assertEquals(200, mockedResponse.getStatus());
	}
	@Test
	public void shouldGetNotFound1() throws IOException {
		RestApiHandler api = new RestApiHandler();
		SignalKModel model = SignalKModelFactory.getInstance();
		model.getData().clear();
		model = Util.populateModel(model, new File("src/test/resources/samples/basicModel.txt"));
		model = Util.populateModel(model, new File("src/test/resources/samples/otherModel.txt"));
		
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		when(mockedRequest.getPathInfo()).thenReturn(JsonConstants.SIGNALK_API+"vess/"+self+"/"+nav+"/");

		HttpServletResponse  mockedResponse = Mockito.mock(HttpServletResponse.class);
		SignalKModel reply = api.processGet(mockedRequest, mockedResponse, model);
		logger.debug("Repy="+reply);
		//assertEquals(-41.2936935424,reply.get(vessels_dot_self_dot+nav_position_latitude));
		//assertEquals(173.2470855712,reply.get(vessels_dot_self_dot+nav_position_longitude));
		assertNull(reply); 
		verify(mockedResponse).setStatus(HttpServletResponse.SC_NOT_FOUND);
		//assertEquals(200, mockedResponse.getStatus());
	}
	
	@Test
	public void shouldGetBadRequest1() throws IOException {
		RestApiHandler api = new RestApiHandler();
		SignalKModel model = SignalKModelFactory.getInstance();
		model.getData().clear();
		model = Util.populateModel(model, new File("src/test/resources/samples/basicModel.txt"));
		model = Util.populateModel(model, new File("src/test/resources/samples/otherModel.txt"));
		
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		when(mockedRequest.getPathInfo()).thenReturn(JsonConstants.SIGNALK_API.substring(0,11));

		HttpServletResponse  mockedResponse = Mockito.mock(HttpServletResponse.class);
		SignalKModel reply = api.processGet(mockedRequest, mockedResponse, model);
		logger.debug("Repy="+reply);
		//assertEquals(-41.2936935424,reply.get(vessels_dot_self_dot+nav_position_latitude));
		//assertEquals(173.2470855712,reply.get(vessels_dot_self_dot+nav_position_longitude));
		assertNull(reply); 
		verify(mockedResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
		//assertEquals(200, mockedResponse.getStatus());
	}

}