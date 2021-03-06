package nz.co.fortytwo.signalk.util;

import static nz.co.fortytwo.signalk.util.SignalKConstants.UNKNOWN;
import static nz.co.fortytwo.signalk.util.SignalKConstants.dot;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_outside_pressure;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_water_temperature;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_wind_angleApparent;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_wind_directionChangeAlarm;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_wind_directionTrue;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_wind_speedApparent;
import static nz.co.fortytwo.signalk.util.SignalKConstants.env_wind_speedTrue;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_anchor_maxRadius;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_anchor_position_altitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_anchor_position_latitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_anchor_position_longitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_attitude_pitch;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_attitude_roll;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_courseOverGroundMagnetic;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_courseOverGroundTrue;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_headingMagnetic;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_headingTrue;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_magneticVariation;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_position_altitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_position_latitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_position_longitude;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_rateOfTurn;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_speedOverGround;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_speedThroughWater;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_state;
import static nz.co.fortytwo.signalk.util.SignalKConstants.source;
import static nz.co.fortytwo.signalk.util.SignalKConstants.sourceRef;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_backlash;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_deadZone;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_gain;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_headingSource;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_maxDriveCurrent;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_maxDriveRate;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_mode;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_portLock;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_starboardLock;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_state;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_targetHeadingMagnetic;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_autopilot_targetHeadingNorth;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_rudderAngle;
import static nz.co.fortytwo.signalk.util.SignalKConstants.steering_rudderAngleTarget;
import static nz.co.fortytwo.signalk.util.SignalKConstants.timestamp;
import static nz.co.fortytwo.signalk.util.SignalKConstants.type;
import static nz.co.fortytwo.signalk.util.SignalKConstants.value;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels_dot_self_dot;

import nz.co.fortytwo.signalk.model.SignalKModel;
import nz.co.fortytwo.signalk.model.impl.SignalKModelFactory;

public class TestHelper {

	public static SignalKModel getOtherModel(){
		SignalKModel other = SignalKModelFactory.getCleanInstance();
		other.getFullData().put(vessels+dot+"other"+dot+env_outside_pressure+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_outside_pressure+dot+timestamp , "2015-03-16T03:31:22.110Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_outside_pressure+dot+value , 1024);
		
		 other.getFullData().put(vessels+dot+"other"+dot+env_water_temperature+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_water_temperature+dot+timestamp , "2015-03-16T03:31:22.324Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_water_temperature+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_angleApparent+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_angleApparent+dot+timestamp , "2015-03-16T03:31:22.325Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_angleApparent+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_directionChangeAlarm+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_directionChangeAlarm+dot+timestamp , "2015-03-16T03:31:22.326Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_directionChangeAlarm+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_directionTrue+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_directionTrue+dot+timestamp , "2015-03-16T03:31:22.327Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_directionTrue+dot+value ,  Math.toRadians(0d));
		 
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_speedApparent+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_speedApparent+dot+timestamp , "2015-03-16T03:31:22.328Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_speedApparent+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_speedTrue+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_speedTrue+dot+timestamp , "2015-03-16T03:31:22.329Z");
		 other.getFullData().put(vessels+dot+"other"+dot+env_wind_speedTrue+dot+value , 7.68d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_maxRadius+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_maxRadius+dot+timestamp , "2015-03-16T03:31:22.330Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_maxRadius+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_maxRadius+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_maxRadius+dot+timestamp , "2015-03-16T03:31:22.330Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_maxRadius+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_position_altitude , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_position_latitude , -41.2936935424d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_anchor_position_longitude , 173.2470855712d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_courseOverGroundMagnetic+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_courseOverGroundMagnetic+dot+timestamp , "2015-03-16T03:31:22.331Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_courseOverGroundMagnetic+dot+value , Math.toRadians(93d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_courseOverGroundTrue+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_courseOverGroundTrue+dot+timestamp , "2015-03-16T03:31:22.332Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_courseOverGroundTrue+dot+value , Math.toRadians(11.9600000381d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_headingMagnetic+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_headingMagnetic+dot+timestamp , "2015-03-16T03:31:22.333Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_headingMagnetic+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_headingTrue+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_headingTrue+dot+timestamp , "2015-03-16T03:31:22.334Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_headingTrue+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_magneticVariation+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_magneticVariation+dot+timestamp , "2015-03-16T03:31:22.335Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_magneticVariation+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_attitude_pitch+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_attitude_pitch+dot+timestamp , "2015-03-16T03:31:22.335Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_attitude_pitch+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_position_altitude , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_position_latitude , -41.2936935424d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_position_longitude , 173.2470855712d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_rateOfTurn+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_rateOfTurn+dot+timestamp , "2015-03-16T03:31:22.336Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_rateOfTurn+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_attitude_roll+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_attitude_roll+dot+timestamp , "2015-03-16T03:31:22.341Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_attitude_roll+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+nav_speedOverGround+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_speedOverGround+dot+timestamp , "2015-03-16T03:31:22.342Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_speedOverGround+dot+value , 0.039999998d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_speedThroughWater+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_speedThroughWater+dot+timestamp , "2015-03-16T03:31:22.343Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_speedThroughWater+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_state+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+nav_state+dot+timestamp , "2015-03-16T03:31:22.343Z");
		 other.getFullData().put(vessels+dot+"other"+dot+nav_state+dot+value , "Notdefined(example)");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_backlash+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_backlash+dot+timestamp , "2015-03-16T03:31:22.345Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_backlash+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_deadZone+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_deadZone+dot+timestamp , "2015-03-16T03:31:22.346Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_deadZone+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_gain+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_gain+dot+timestamp , "2015-03-16T03:31:22.347Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_gain+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_headingSource+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_headingSource+dot+timestamp , "2015-03-16T03:31:22.348Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_headingSource+dot+value , "compass");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_maxDriveCurrent+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_maxDriveCurrent+dot+timestamp , "2015-03-16T03:31:22.349Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_maxDriveCurrent+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_maxDriveRate+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_maxDriveRate+dot+timestamp , "2015-03-16T03:31:22.356Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_maxDriveRate+dot+value , 0d);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_mode+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_mode+dot+timestamp , "2015-03-16T03:31:22.359Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_mode+dot+value , "powersave");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_portLock+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_portLock+dot+timestamp , "2015-03-16T03:31:22.360Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_portLock+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_starboardLock+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_starboardLock+dot+timestamp , "2015-03-16T03:31:22.363Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_starboardLock+dot+value ,  Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_state+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_state+dot+timestamp , "2015-03-16T03:31:22.366Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_state+dot+value , "off");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_targetHeadingMagnetic+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_targetHeadingMagnetic+dot+timestamp , "2015-03-16T03:31:22.369Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_targetHeadingMagnetic+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_targetHeadingNorth+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_targetHeadingNorth+dot+timestamp , "2015-03-16T03:31:22.371Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_autopilot_targetHeadingNorth+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_rudderAngle+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_rudderAngle+dot+timestamp , "2015-03-16T03:31:22.374Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_rudderAngle+dot+value , Math.toRadians(0d));
		 other.getFullData().put(vessels+dot+"other"+dot+steering_rudderAngleTarget+dot+sourceRef , UNKNOWN);
		 other.getFullData().put(vessels+dot+"other"+dot+steering_rudderAngleTarget+dot+timestamp , "2015-03-16T03:31:22.376Z");
		 other.getFullData().put(vessels+dot+"other"+dot+steering_rudderAngleTarget+dot+value , Math.toRadians(0d));
		 return other;
	}

	public static SignalKModel getBasicModel() {
		SignalKModel basic = SignalKModelFactory.getCleanInstance();
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+timestamp,"2015-03-16T03:31:22.110Z");
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+value,1024);
		
		basic.getFullData().put(vessels_dot_self_dot+env_water_temperature+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_water_temperature+dot+timestamp,"2015-03-16T03:31:22.324Z");
		basic.getFullData().put(vessels_dot_self_dot+env_water_temperature+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_angleApparent+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_angleApparent+dot+timestamp,"2015-03-16T03:31:22.325Z");
		basic.getFullData().put(vessels_dot_self_dot+env_wind_angleApparent+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+env_wind_directionChangeAlarm+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_directionChangeAlarm+dot+timestamp,"2015-03-16T03:31:22.326Z");
		basic.getFullData().put(vessels_dot_self_dot+env_wind_directionChangeAlarm+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+env_wind_directionTrue+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_directionTrue+dot+timestamp,"2015-03-16T03:31:22.327Z");
		basic.getFullData().put(vessels_dot_self_dot+env_wind_directionTrue+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+env_wind_speedApparent+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_speedApparent+dot+timestamp,"2015-03-16T03:31:22.328Z");
		basic.getFullData().put(vessels_dot_self_dot+env_wind_speedApparent+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_speedTrue+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_wind_speedTrue+dot+timestamp,"2015-03-16T03:31:22.329Z");
		basic.getFullData().put(vessels_dot_self_dot+env_wind_speedTrue+dot+value,7.68d);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_maxRadius+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_maxRadius+dot+timestamp,"2015-03-16T03:31:22.330Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_maxRadius+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_maxRadius+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_maxRadius+dot+timestamp,"2015-03-16T03:31:22.330Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_maxRadius+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_position_altitude,0d);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_position_latitude,-41.2936935424d);
		basic.getFullData().put(vessels_dot_self_dot+nav_anchor_position_longitude,173.2470855712d);
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+timestamp,"2015-03-16T03:31:22.331Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+value,Math.toRadians(93d));
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+timestamp,"2015-03-16T03:31:22.332Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+value,Math.toRadians(11.9600000381d));
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+timestamp,"2015-03-16T03:31:22.333Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+timestamp,"2015-03-16T03:31:22.334Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_magneticVariation+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_magneticVariation+dot+timestamp,"2015-03-16T03:31:22.335Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_magneticVariation+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_attitude_pitch+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_attitude_pitch+dot+timestamp,"2015-03-16T03:31:22.335Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_attitude_pitch+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_position_altitude,0d);
		basic.getFullData().put(vessels_dot_self_dot+nav_position_latitude,-41.2936935424d);
		basic.getFullData().put(vessels_dot_self_dot+nav_position_longitude,173.2470855712d);
		basic.getFullData().put(vessels_dot_self_dot+nav_rateOfTurn+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_rateOfTurn+dot+timestamp,"2015-03-16T03:31:22.336Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_rateOfTurn+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_attitude_roll+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_attitude_roll+dot+timestamp,"2015-03-16T03:31:22.341Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_attitude_roll+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_speedOverGround+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_speedOverGround+dot+timestamp,"2015-03-16T03:31:22.342Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_speedOverGround+dot+value,0.039999998d);
		basic.getFullData().put(vessels_dot_self_dot+nav_speedThroughWater+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_speedThroughWater+dot+timestamp,"2015-03-16T03:31:22.343Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_speedThroughWater+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+nav_state+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_state+dot+timestamp,"2015-03-16T03:31:22.343Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_state+dot+value,"Notdefined(example)");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_backlash+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_backlash+dot+timestamp,"2015-03-16T03:31:22.345Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_backlash+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_deadZone+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_deadZone+dot+timestamp,"2015-03-16T03:31:22.346Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_deadZone+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_gain+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_gain+dot+timestamp,"2015-03-16T03:31:22.347Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_gain+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_headingSource+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_headingSource+dot+timestamp,"2015-03-16T03:31:22.348Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_headingSource+dot+value,"compass");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_maxDriveCurrent+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_maxDriveCurrent+dot+timestamp,"2015-03-16T03:31:22.349Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_maxDriveCurrent+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_maxDriveRate+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_maxDriveRate+dot+timestamp,"2015-03-16T03:31:22.356Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_maxDriveRate+dot+value,0d);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_mode+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_mode+dot+timestamp,"2015-03-16T03:31:22.359Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_mode+dot+value,"powersave");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_portLock+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_portLock+dot+timestamp,"2015-03-16T03:31:22.360Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_portLock+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_starboardLock+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_starboardLock+dot+timestamp,"2015-03-16T03:31:22.363Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_starboardLock+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_state+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_state+dot+timestamp,"2015-03-16T03:31:22.366Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_state+dot+value,"off");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_targetHeadingMagnetic+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_targetHeadingMagnetic+dot+timestamp,"2015-03-16T03:31:22.369Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_targetHeadingMagnetic+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_targetHeadingNorth+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_targetHeadingNorth+dot+timestamp,"2015-03-16T03:31:22.371Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_autopilot_targetHeadingNorth+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_rudderAngle+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_rudderAngle+dot+timestamp,"2015-03-16T03:31:22.374Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_rudderAngle+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+steering_rudderAngleTarget+dot+sourceRef,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+steering_rudderAngleTarget+dot+timestamp,"2015-03-16T03:31:22.376Z");
		basic.getFullData().put(vessels_dot_self_dot+steering_rudderAngleTarget+dot+value, Math.toRadians(0d));
		return basic;
	}
	
	/**
	 * Returns a model with .source.* entries
	 * @return
	 */
	public static SignalKModel getSourcesModel() {
		SignalKModel basic = SignalKModelFactory.getCleanInstance();
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+source+dot+type,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+source+dot+"label","testAirP");
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+timestamp,"2015-03-16T03:31:22.110Z");
		basic.getFullData().put(vessels_dot_self_dot+env_outside_pressure+dot+value,1024);
		
		
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+source+dot+type,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+source+dot+"label","testCogM");
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+timestamp,"2015-03-16T03:31:22.331Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundMagnetic+dot+value,Math.toRadians(93d));
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+source+dot+type,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+source+dot+"label","testCogT");
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+timestamp,"2015-03-16T03:31:22.332Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_courseOverGroundTrue+dot+value,Math.toRadians(11.9600000381d));
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+source+dot+type,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+source+dot+"label","testHdtM");
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+timestamp,"2015-03-16T03:31:22.333Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_headingMagnetic+dot+value, Math.toRadians(0d));
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+source+dot+type,UNKNOWN);
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+source+dot+"label","testHdgT");
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+timestamp,"2015-03-16T03:31:22.334Z");
		basic.getFullData().put(vessels_dot_self_dot+nav_headingTrue+dot+value, Math.toRadians(0d));
		
		return basic;
	}
	
}
