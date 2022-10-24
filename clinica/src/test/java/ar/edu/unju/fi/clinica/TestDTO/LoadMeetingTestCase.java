/**
 * 
 */
package ar.edu.unju.fi.clinica.TestDTO;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.clinica.DTO.DoctorDTO;
import ar.edu.unju.fi.clinica.DTO.PatientDTO;
import ar.edu.unju.fi.clinica.Entity.Meeting;

import ar.edu.unju.fi.clinica.Mapper.Mapper;
import ar.edu.unju.fi.clinica.Repository.MeetingRepository;
import ar.edu.unju.fi.clinica.Service.DoctorService;
import ar.edu.unju.fi.clinica.Service.PatientService;
import ar.edu.unju.fi.clinica.Util.Constante;

/**
 * @author Diaz, Sebastián Darío
 *
 */
@SpringBootTest
class LoadMeetingTestCase {

	//private static final Type LOCAL_DATE_TIME_TYPE = new TypeToken<LocalDateTime>() {}.getType();
	private static Logger log = Logger.getLogger(LoadMeetingTestCase.class);
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private MeetingRepository meetingRepository;
	
	private PatientDTO patientDefault;
	private DoctorDTO doctorDefault;
	
	private Meeting meeting;
	private Meeting meeting2;
	private Meeting meeting3;
	private Meeting meeting4;
	private Meeting meeting5;
	private Meeting meeting6;
	private Meeting meeting7;
	private Meeting meeting8;
	private Meeting meeting9;
	private Meeting meeting10;
	private Meeting meeting11;
	private Meeting meeting12;
	private Meeting meeting13;
	private Meeting meeting14;
	private Meeting meeting15;
	private Meeting meeting16;
	
	private Long quantity = 0L;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		patientDefault = patientService.findPatientByDNI(11111111);
		doctorDefault = doctorService.findDoctorByLicenseNumber(1212);
		
		quantity = meetingRepository.count();

		
		meeting = new Meeting(Constante.TIME_START_MORNING, Constante.TIME_START_MORNING.plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting2 = new Meeting(meeting.getEndMeeting(), meeting.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting3 = new Meeting(meeting2.getEndMeeting(), meeting2.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting4 = new Meeting(meeting3.getEndMeeting(), meeting3.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting5 = new Meeting(meeting4.getEndMeeting(), meeting4.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting6 = new Meeting(meeting5.getEndMeeting(), meeting5.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting7 = new Meeting(meeting6.getEndMeeting(), meeting6.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting8 = new Meeting(meeting7.getEndMeeting(), Constante.TIME_END_MORNING, Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		
		meeting9 = new Meeting(Constante.TIME_START_AFTERNOON, Constante.TIME_START_AFTERNOON.plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting10 = new Meeting(meeting9.getEndMeeting(), meeting9.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting11 = new Meeting(meeting10.getEndMeeting(), meeting10.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting12 = new Meeting(meeting11.getEndMeeting(), meeting11.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting13 = new Meeting(meeting12.getEndMeeting(), meeting12.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting14 = new Meeting(meeting13.getEndMeeting(), meeting13.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting15 = new Meeting(meeting14.getEndMeeting(), meeting14.getEndMeeting().plusMinutes(30), Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		meeting16 = new Meeting(meeting15.getEndMeeting(), Constante.TIME_END_AFTERNOON, Mapper.mapToDoctor(doctorDefault), Mapper.mapToPatient(patientDefault),false);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		try {
			meetingRepository.save(meeting);
			meetingRepository.save(meeting2);
			meetingRepository.save(meeting3);
			meetingRepository.save(meeting4);
			meetingRepository.save(meeting5);
			meetingRepository.save(meeting6);
			meetingRepository.save(meeting7);
			meetingRepository.save(meeting8);
			
			meetingRepository.save(meeting9);
			meetingRepository.save(meeting10);
			meetingRepository.save(meeting11);
			meetingRepository.save(meeting12);
			meetingRepository.save(meeting13);
			meetingRepository.save(meeting14);
			meetingRepository.save(meeting15);
			meetingRepository.save(meeting16);
			
			assertTrue(meetingRepository.findAll().size() == quantity+1);
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
	}


}
