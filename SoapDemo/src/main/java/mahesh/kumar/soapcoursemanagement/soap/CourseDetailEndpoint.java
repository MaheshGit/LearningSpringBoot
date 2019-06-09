package mahesh.kumar.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mahesh.kumar.courses.CourseDetails;
import mahesh.kumar.courses.DeleteCourseDetailsRequest;
import mahesh.kumar.courses.DeleteCourseDetailsResponse;
import mahesh.kumar.courses.GetAllCourseDetailsRequest;
import mahesh.kumar.courses.GetAllCourseDetailsResponse;
import mahesh.kumar.courses.GetCourseDetailsRequest;
import mahesh.kumar.courses.GetCourseDetailsResponse;
import mahesh.kumar.soapcoursemanagement.bean.Course;
import mahesh.kumar.soapcoursemanagement.soap.service.CourseDetailsService;
import mahesh.kumar.soapcoursemanagement.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailEndpoint {

	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://kumar.mahesh/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		if (course == null)
			throw new CourseNotFoundException("Invalid Course Id " + request.getId());
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace = "http://kumar.mahesh/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> course = service.findAll();
		return mapAllCourseDetails(course);
	}
	
	@PayloadRoot(namespace = "http://kumar.mahesh/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		Status status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}

	private mahesh.kumar.courses.Status mapStatus(Status status) {
		if(status == Status.FAILURE) {
			return mahesh.kumar.courses.Status.FAILURE;
		}
		return mahesh.kumar.courses.Status.SUCCESS;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mc = mapCourse(course);
			response.getCourseDetails().add(mc);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

}
